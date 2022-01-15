package org.launchcode.techjobs.persistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe extends AbstractEntity{

    @ManyToOne
    @NotNull
    private Employer employer;

    @ManyToMany
    private List<DietaryRestriction> dietaryRestrictions = new ArrayList<>();

    public Recipe(Employer employer, List<DietaryRestriction> dietaryRestrictions) {
        this.employer = employer;
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public Recipe() {}

    // Getters and setters.
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public void addDietaryRestriction(DietaryRestriction dietaryRestriction) {
        this.dietaryRestrictions.add(dietaryRestriction);
    }

}
