package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dietary extends AbstractEntity {

    @NotBlank(message = "Please enter description of up to 500 characters")
    @Size(max = 500, message = "Please enter description of up to 500 characters")
    private String description;


    @ManyToMany(mappedBy = "dietaries")
    private final List<Recipe> recipes = new ArrayList<>();

    public Dietary(String description) {
        this.description = description;
    }

    public Dietary() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }
}