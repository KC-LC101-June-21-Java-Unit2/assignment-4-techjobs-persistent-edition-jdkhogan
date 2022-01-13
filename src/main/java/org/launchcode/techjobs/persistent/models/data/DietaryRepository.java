package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Dietary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DietaryRepository extends CrudRepository<Dietary, Integer> {
}
