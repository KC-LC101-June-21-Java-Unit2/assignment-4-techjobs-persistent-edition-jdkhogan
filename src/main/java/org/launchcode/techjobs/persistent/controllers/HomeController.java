package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Recipe;
import org.launchcode.techjobs.persistent.models.Dietary;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.RecipeRepository;
import org.launchcode.techjobs.persistent.models.data.DietaryRepository;
import org.launchcode.techjobs.persistent.models.dto.RecipeDietaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private DietaryRepository dietaryRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddRecipeForm(Model model) {
        model.addAttribute("title", "Add Recipe");
        model.addAttribute(new Recipe());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("dietaries", dietaryRepository.findAll());
        model.addAttribute("recipeDietary", new RecipeDietaryDTO());

        return "add";
    }

    @PostMapping("add")
    public String processAddRecipeForm(@ModelAttribute Recipe newRecipe, Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> dietaries) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("dietaries", dietaryRepository.findAll());
            return "add";
        }

        // verify that employerId is in DB. Add to newJob if so, return to Add Job page if not.
        Optional<Employer> maybeEmployer = employerRepository.findById(employerId);
        if (maybeEmployer.isPresent()) {
            Employer employer = maybeEmployer.get();
            newRecipe.setEmployer(employer);
        } else {
            model.addAttribute("title", "Invalid Employer ID: " + employerId);
            return "add";
        }

        // Loop through all dietaryIds in DB. Return to Add Recipe page if any of them are not in DB.
        for (Integer dietaryId : dietaries) {
            Optional<Dietary> maybeDietary = dietaryRepository.findById(dietaryId);
            if (maybeDietary.isEmpty()) {
                model.addAttribute("title", "Invalid Dietary Restriction ID: " + dietaryId);
                return "add";
            }
        }

        List<Dietary> dietaryObjs = (List<Dietary>) dietaryRepository.findAllById(dietaries);
        newRecipe.setDietaries(dietaryObjs);

        newRecipe = recipeRepository.save(newRecipe);

        return "redirect:/view/" + newRecipe.getId();
    }

    @GetMapping("view/{recipeId}")
    public String displayViewRecipe(Model model, @PathVariable int recipeId) {
        Optional<Recipe> result = recipeRepository.findById(recipeId);
        if (result.isPresent()) {
            Recipe recipe = result.get();
            model.addAttribute("title", recipe.getName() + " Details");
            model.addAttribute("recipe", recipe);
            model.addAttribute("employer", recipe.getEmployer());
            model.addAttribute("dietaries", recipe.getDietaries());
        } else {
            model.addAttribute("title", "Invalid Recipe ID: " + recipeId);
        }
        return "view";
    }

}
