package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

// TODO: Models 1 **done**
//  In addition to the fields inherited from AbstractEntity,
//  Employer should have a string field for location.
//  Add the field for location with validation that ensures it is not empty and
//  has a reasonable length. In addition, add public accessor methods to Employer.

    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Please enter location in 200 or fewer characters")
    private String location;

    // TODO: Relationships 1.1 & 1.2  **done**
    //  Within Employer, add a private property jobs of type List<Job> and initialize it to an empty ArrayList.
    //  After we set up the Job class to work with Employer objects, this list will represent the list of all items in a given job.
    //  Use the @OneToMany and @JoinColumn annotations on the jobs list in Employer to declare the relationship between data tables.
    //  Recall that this annotation needs a name parameter.

    @OneToMany(mappedBy = "employer")
    private final List<Job> jobs = new ArrayList<>();

// TODO: Models 2 **done**
//  Make sure the class has the @Entity annotation,
//  as well as the no-arg constructor required for Hibernate to create an object.

    public Employer(String location) {
        this.location = location;
    }

    public Employer() {}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
