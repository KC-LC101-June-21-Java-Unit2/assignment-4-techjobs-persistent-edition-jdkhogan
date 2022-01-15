package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Recipe;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.RecipeRepository;
import org.launchcode.techjobs.persistent.models.RecipeData;
import org.launchcode.techjobs.persistent.models.data.DietaryRestrictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private DietaryRestrictionRepository dietaryRestrictionRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("employer", "Employer");
        columnChoices.put("dietaryrestrictions", "DietaryRestriction Restrictions");

    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("dietaries", dietaryRestrictionRepository.findAll());
        return "list";
    }

    @RequestMapping(value = "recipes")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Recipe> recipes;
        if (column.toLowerCase().equals("all")){
            recipes = recipeRepository.findAll();
            model.addAttribute("title", "All Recipes");
        } else {
            recipes = RecipeData.findByColumnAndValue(column, value, recipeRepository.findAll());
            model.addAttribute("title", "Recipes that match " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("recipes", recipes);

        return "list-recipes";
    }

}