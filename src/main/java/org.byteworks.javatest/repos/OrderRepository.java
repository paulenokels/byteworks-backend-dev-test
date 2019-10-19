package org.byteworks.javatest;

import java.util.List;

import org.springframework.data.repository.CrudRepository;




// This will be AUTO IMPLEMENTED by Spring into a Bean called orderRepository
// CRUD refers Create, Read, Update, Delete

public interface OrderRepository extends CrudRepository<Order_, Integer> {

    List<Order_> findByVendorId(int vendorId);

    //filter by delivery option
    List<Order_> findByVendorIdAndDeliveryOption(int vendorId, String deliveryOption);
    //filter by payment method
    List<Order_> findByVendorIdAndPaymentMethod(int vendorId, String paymentMethod);
    //filter by delivery option and payment method
    List<Order_> findByVendorIdAndDeliveryOptionAndPaymentMethod(int vendorId, String deliveryOption, String paymentMethod);

   
}