package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Dietary;
import org.launchcode.techjobs.persistent.models.data.DietaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("dietary")
public class DietaryController {

    @Autowired
    private DietaryRepository dietaryRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title","Dietary Restrictions");
        model.addAttribute("dietary", dietaryRepository.findAll());
        return "dietary/index";
    }

    @GetMapping("add")
    public String displayAddDietaryForm(Model model) {
        model.addAttribute(new Dietary());
        return "dietary/add";
    }

    @PostMapping("add")
    public String processAddDietaryForm(@ModelAttribute @Valid Dietary newDietary, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title","Dietary Restrictions");
            model.addAttribute(newDietary);
            return "dietary/add";
        }

        newDietary = dietaryRepository.save(newDietary);
        return "redirect:";
    }

    @GetMapping("view/{dietaryId}")
    public String displayViewDietary(Model model, @PathVariable int dietaryId) {
        Optional optDietary = dietaryRepository.findById(dietaryId);

        if (optDietary.isPresent()) {
            Dietary dietary = (Dietary) optDietary.get();
            model.addAttribute("dietary", dietary);
            return "dietary/view";
        } else {
            return "redirect:../";
        }
    }

}