package org.launchcode.techjobs.persistent.models.dto;

import org.launchcode.techjobs.persistent.models.Recipe;
import org.launchcode.techjobs.persistent.models.Dietary;

import javax.validation.constraints.NotNull;

public class RecipeDietaryDTO {

    @NotNull
    private Recipe recipe;

    @NotNull
    private Dietary dietary;

    public RecipeDietaryDTO() {}

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Dietary getDietary() {
        return dietary;
    }

    public void setDietary(Dietary dietary) {
        this.dietary = dietary;
    }
}
