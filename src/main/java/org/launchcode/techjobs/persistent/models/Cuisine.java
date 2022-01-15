package org.launchcode.techjobs.persistent.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cuisine extends AbstractEntity{

    @Id
    @GeneratedValue
    private int id;


    @OneToMany()
    @JoinColumn(name = "cuisine_id")
    private final List<Recipe> recipes = new ArrayList<>();

    public Cuisine() {}

    public List<Recipe> getRecipes() {
        return recipes;
    }



    @Override
    public String toString() {
        return "Cuisine{" +
                "} " + super.toString();
    }
}
