package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

// TODO: Controllers 1 **done**
//  Add a private field of EmployerRepository type called employerRepository to EmployerController.
//  Give this field an @Autowired annotation.

    @Autowired
    private EmployerRepository employerRepository;

// TODO: Controllers 2 **tbd**
//  Add an index method that responds at /employers with a list of all employers in the database.
//  This method should use the template employers/index.

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title","Employers");
        model.addAttribute("employers", employerRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

// TODO: Controllers 3 **done**
//  processAddEmployerForm already takes care of sending the form back if any of the
//  submitted employer object information is invalid. However, it doesn’t yet contain
//  the code to save a valid object.
//  Use employerRepository and the appropriate method to do so.

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }

        employerRepository.save(newEmployer);
        return "redirect:";
    }

// TODO: Controllers 4 **done**
//  displayViewEmployer will be in charge of rendering a page to view the contents
//  of an individual employer object. It will make use of that employer object’s
//  id field to grab the correct information from employerRepository.
//  optEmployer is currently initialized to null.
//  Replace this using the .findById() method with the right argument to look for the
//  given employer object from the data layer.

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }
}
