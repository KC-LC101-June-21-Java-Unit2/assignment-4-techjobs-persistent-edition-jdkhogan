package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
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

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob, @RequestParam int employerId, Errors errors, Model model) {
        if (!errors.hasErrors()) {
            Optional<Employer> maybeEmployer = employerRepository.findById(employerId);
            if (maybeEmployer.isPresent()) {
                newJob = jobRepository.save(newJob);
                return "/jobs/view/" + newJob.getId();
            } else {
                model.addAttribute("title", "Invalid Employer ID: " + employerId);
            }
        }

        return "add";
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
