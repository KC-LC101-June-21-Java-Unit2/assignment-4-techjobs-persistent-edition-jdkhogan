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
    private List<Dietary> dietaries = new ArrayList<>();

    public Recipe(Employer employer, List<Dietary> dietaries) {
        this.employer = employer;
        this.dietaries = dietaries;
    }

    public Recipe() {}

    // Getters and setters.
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Dietary> getDietaries() {
        return dietaries;
    }

    public void setDietaries(List<Dietary> dietaries) {
        this.dietaries = dietaries;
    }

    public void addDietary(Dietary dietary) {
        this.dietaries.add(dietary);
    }

}
