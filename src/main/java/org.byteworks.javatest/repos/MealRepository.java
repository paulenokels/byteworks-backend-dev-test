package org.byteworks.javatest;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MealRepository extends CrudRepository<Meal, Integer> {

    //get list of all meals offered by a vendor
 List<Meal> findByVendorId(int vendorId);
   
}