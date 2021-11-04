package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Skill extends AbstractEntity {

    // TODO: Models 3 & 4 **done**
    //  Add a field for a longer description of the skill, named description, with public accessor methods.
    //  As with Employer, give this class the @Entity annotation and be sure it contains a no-arg constructor.

    @NotBlank(message = "Please enter description of up to 500 characters")
    @Size(max = 500, message = "Please enter description of up to 500 characters")
    private String description;


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
}