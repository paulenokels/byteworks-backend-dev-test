package org.byteworks.javatest;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;




// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VendorRepository extends CrudRepository<Vendor, Integer> {

    Optional<Vendor> findByVendorEmail (String email);

}