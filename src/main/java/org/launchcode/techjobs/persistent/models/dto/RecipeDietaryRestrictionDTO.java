package org.launchcode.techjobs.persistent.models.dto;

import org.launchcode.techjobs.persistent.models.Recipe;
import org.launchcode.techjobs.persistent.models.DietaryRestriction;

import javax.validation.constraints.NotNull;

public class RecipeDietaryRestrictionDTO {

    @NotNull
    private Recipe recipe;

    @NotNull
    private DietaryRestriction dietaryRestriction;

    public RecipeDietaryRestrictionDTO() {}

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public DietaryRestriction getDietary() {
        return dietaryRestriction;
    }

    public void setDietary(DietaryRestriction dietaryRestriction) {
        this.dietaryRestriction = dietaryRestriction;
    }
}
