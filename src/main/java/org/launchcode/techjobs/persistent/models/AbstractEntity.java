package org.launchcode.techjobs.persistent.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

// TODO: AbstractEntity 1 **done**
@MappedSuperclass
public abstract class AbstractEntity {

    // TODO: AbstractEntity 2 **done**
    @Id
    @GeneratedValue
    private int id;

    // TODO: AbstractEntity 3 **done**
    //  A user cannot leave this field blank when creating an object.
    //  There are reasonable limitations on the size of the name string.
    //  Keep in mind that the name field will be shared across Job, Employer, and Skill classes.
    //  Some employer names might be longer than 50 characters.

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}