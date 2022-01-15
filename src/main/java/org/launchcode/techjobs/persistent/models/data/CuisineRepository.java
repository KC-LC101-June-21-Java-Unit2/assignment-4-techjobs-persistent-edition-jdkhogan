package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Cuisine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends CrudRepository<Cuisine,Integer> {
}

