package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.launchcode.techjobs.persistent.models.dto.JobSkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("jobSkill", new JobSkillDTO());

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute Job newJob, Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }

        // verify that employerId is in DB. Add to newJob if so, return to Add Job page if not.
        Optional<Employer> maybeEmployer = employerRepository.findById(employerId);
        if (maybeEmployer.isPresent()) {
            Employer employer = maybeEmployer.get();
            newJob.setEmployer(employer);
        } else {
            model.addAttribute("title", "Invalid Employer ID: " + employerId);
            return "add";
        }

        // Loop through all skillIds in DB. Return to Add Job page if any of them are not in DB.
        for (Integer skillId : skills) {
            Optional<Skill> maybeSkill = skillRepository.findById(skillId);
            if (maybeSkill.isEmpty()) {
                model.addAttribute("title", "Invalid Skill ID: " + skillId);
                return "add";
            }
        }

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);

        newJob = jobRepository.save(newJob);

        return "redirect:/view/" + newJob.getId();

    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> result = jobRepository.findById(jobId);
        if (result.isPresent()) {
            Job job = result.get();
            model.addAttribute("title", job.getName() + " Details");
            model.addAttribute("job", job);
            model.addAttribute("employer", job.getEmployer());
            model.addAttribute("skills", job.getSkills());
        } else {
            model.addAttribute("title", "Invalid Job ID: " + jobId);
        }

        return "view";
    }

}
