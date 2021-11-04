package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
}
