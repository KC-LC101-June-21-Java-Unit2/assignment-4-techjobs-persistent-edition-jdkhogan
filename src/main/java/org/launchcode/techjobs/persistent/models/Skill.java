package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

// TODO: Models 3 & 4 **done**
//  Add a field for a longer description of the skill, named description, with public accessor methods.
//  As with Employer, give this class the @Entity annotation and be sure it contains a no-arg constructor.

    @NotBlank(message = "Please enter description of up to 500 characters")
    @Size(max = 500, message = "Please enter description of up to 500 characters")
    private String description;

// TODO: Relationships 4.1 **done**
//  In your Skill class, add a jobs field.
//  Add a getter and setter for the field.
//  This field has a many-to-many type relationship with skills.
//  You’ll need to add the @ManyToMany annotation with an argument mappedBy="skills" to configure this mapping.

    @ManyToMany(mappedBy = "skills")
    private final List<Job> jobs = new ArrayList<>();

    public Skill(String description) {
        this.description = description;
    }

    public Skill() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void addJob(Job job) {
        this.jobs.add(job);
    }
}