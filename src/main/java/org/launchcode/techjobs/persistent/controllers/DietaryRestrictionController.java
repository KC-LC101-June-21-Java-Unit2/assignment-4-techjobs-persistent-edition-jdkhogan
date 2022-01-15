package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.DietaryRestriction;
import org.launchcode.techjobs.persistent.models.data.DietaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("dietaryrestrictions")
public class DietaryRestrictionController {

    @Autowired
    private DietaryRepository dietaryRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title","DietaryRestriction Restrictions");
        model.addAttribute("dietaryrestrictions", dietaryRepository.findAll());
        return "dietaryrestrictions/index";
    }

    @GetMapping("add")
    public String displayAddDietaryForm(Model model) {
        model.addAttribute(new DietaryRestriction());
        return "dietaryrestrictions/add";
    }

    @PostMapping("add")
    public String processAddDietaryForm(@ModelAttribute @Valid DietaryRestriction newDietaryRestriction, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title","DietaryRestriction Restrictions");
            model.addAttribute(newDietaryRestriction);
            return "dietaryrestrictions/add";
        }

        newDietaryRestriction = dietaryRepository.save(newDietaryRestriction);
        return "redirect:";
    }

    @GetMapping("view/{dietaryId}")
    public String displayViewDietary(Model model, @PathVariable int dietaryId) {
        Optional optDietary = dietaryRepository.findById(dietaryId);

        if (optDietary.isPresent()) {
            DietaryRestriction dietaryRestriction = (DietaryRestriction) optDietary.get();
            model.addAttribute("dietaryRestriction", dietaryRestriction);
            return "dietaryRestriction/view";
        } else {
            return "redirect:../";
        }
    }

}