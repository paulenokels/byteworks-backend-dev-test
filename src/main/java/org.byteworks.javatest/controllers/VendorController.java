/**
* This controller is responsible for vendor login, registration,
*viewing of orders, adding meal 
* It handles the following routes
* 1. /api/vendor/login
* 2. /api/vendor/register
* 3. /api/vendor/view-meals
* 4. /api/vendor/view-all-orders
* @author  Enokela Acheme Paul
* @email    achemepaulenokela@gmail.com
* @version 1.0
*/

package org.byteworks.javatest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.mindrot.jbcrypt.BCrypt;






@RestController
public class VendorController {

    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private OrderRepository orderRepository;

    private HashMap<String, Object> res = new HashMap<>();

     /*
    * method for vendor login
    */


    @RequestMapping(value="/api/vendor/login", method=RequestMethod.POST)
    public HashMap<String, Object> vendorLogin(
            //vendor email
            @RequestParam(value = "email", defaultValue = "") String email,
            //vendor password
            @RequestParam(value = "password", defaultValue = "") String rawPassword) {
        //initilialize response        
        res = new HashMap<>();
        //get vendor
        Optional<Vendor> vendor = vendorRepository.findByVendorEmail(email.toLowerCase());

        //vendor not found
        if (!vendor.isPresent()) return ErrorResponse.respond(Constants.ERROR_INCORRECT_CREDENTIALS);

     //   BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //compare password
        if (!BCrypt.checkpw(rawPassword, vendor.get().getVendorPassword())) return ErrorResponse.respond(Constants.ERROR_INCORRECT_CREDENTIALS);

        res.put("success", true);
        res.put("vendor", vendor);
        return res;
    }

     /*
    * method for vendor registration
    */

    @RequestMapping(value="/api/vendor/register", method=RequestMethod.POST)
    public HashMap<String, Object> vendorRegister(
            //vendor kitchen name
            @RequestParam(value = "name", required = true) String name,
            //vendor email
            @RequestParam(value = "email", required = true) String email,
            //vendor GPS latitude
            @RequestParam(value = "lat", required = true) double lat,
            //vendor GPS longitude
            @RequestParam(value = "lng", required = true) double lng,
            //vendor password
            @RequestParam(value = "password", required = true) String password,
            //confirm password
            @RequestParam(value = "repeatPassword", required = true) String repeatPassword) {
        res = new HashMap<>();

        //password check
        if (!password.equals(repeatPassword)) {
            return ErrorResponse.respond(Constants.ERROR_PASS_MISMATCH);

        }

        //email check
        if (!EmailValidator.validateEmail(email)) {
            return ErrorResponse.respond(Constants.ERROR_INVALID_EMAIL);

        }

        Optional<Vendor> existingMail = vendorRepository.findByVendorEmail(email);
        //check for existing mail
        if (existingMail.isPresent()) {
            return ErrorResponse.respond(Constants.ERROR_EXISTING_MAIL);

        }

        //encode password
       // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Vendor vendor = new Vendor(name, email, encodedPassword, Constants.VENDOR_STATUS_ACTIVE, lat, lng);
        vendorRepository.save(vendor);
        res.put("success", true);
        res.put("msg", Constants.SUCCESS_SIGNUP);
        return res;
    }

     /*
    * method for vendors to add meal to the menu
    */

    @RequestMapping(value="/api/vendor/add-meal", method=RequestMethod.POST)
    public HashMap<String, Object> addMeal(
            @RequestParam(value = "vendorId", required = true) int vendorId,
            @RequestParam(value = "mealName", required = true) String mealName,
            //additional meal information
            @RequestParam(value = "mealInfo", required = false) String mealInfo,
            //meal status can be AVAILABLE or UNAVAILABLE
            @RequestParam(value = "mealStatus", required = true) String mealStatus,
            @RequestParam(value = "price", required = true) double price) {
        res = new HashMap<>();

        Optional<Vendor> vendor = vendorRepository.findById(vendorId);

        if (!vendor.isPresent()) {
            return ErrorResponse.respond(Constants.ERROR_VENDOR_NOT_FOUND);
        }

        mealStatus = mealStatus.toUpperCase();

        Meal meal = new Meal(vendorId, price, mealName, mealStatus, mealInfo);
        mealRepository.save(meal);
        res.put("success", true);
        res.put("msg", Constants.MEAL_ADDED_SUCCESS);
        return res;
    }

    /*
    * method for vendors to view the meals they offer
    */
    @RequestMapping(value="/api/vendor/view-meals", method=RequestMethod.GET)
    public HashMap<String, Object> getVendorMeals(@RequestParam(value = "vendorId", required = true) int vendorId) {
        res = new HashMap<>();
        res.put("meals", mealRepository.findByVendorId(vendorId));
        return res;
    }

    /* 
    * method for vendor to view orders placed on their meals
    * they can filter order by paymentMethod and/or deliveryOption
    */
    @RequestMapping(value="/api/vendor/view-all-orders", method=RequestMethod.GET)
    public HashMap<String, Object> getVendorOrders(
            @RequestParam(value = "vendorId", required = true) int vendorId,
            //if filtering by payment method (card or cash)
            @RequestParam(value = "paymentMethod", defaultValue = "none", required = false) String paymentMethod,
            //if filtering by delivery option (office or pickup)
            @RequestParam(value = "deliveryOption", defaultValue = "none", required = false) String deliveryOption

    ) {
        res = new HashMap<>();

        List<Order_> orders;

        // filter by payment option and delivery option
        if (!paymentMethod.equals("none") && !deliveryOption.equals("none")) {
            orders = orderRepository.findByVendorIdAndDeliveryOptionAndPaymentMethod(vendorId, deliveryOption,
                    paymentMethod);

        }
        // filter by payment option only
        else if (!paymentMethod.equals("none")) {
            orders = orderRepository.findByVendorIdAndPaymentMethod(vendorId, paymentMethod);
        }
        // filter by delivery option only;
        else if (!deliveryOption.equals("none")) {
            orders = orderRepository.findByVendorIdAndDeliveryOption(vendorId, deliveryOption);
        }

        // no filter, get all orders
        else {
            orders = orderRepository.findByVendorId(vendorId);
        }

        List<Object> vendorOrders = new ArrayList();

        // loop through orders to add the meal info to thashmap
        for (int i = 0; i < orders.size(); i++) {
            Order_ order = orders.get(i);
            Optional<Meal> meal = mealRepository.findById(order.getMealId());
            HashMap<String, Object> orderObj = new HashMap<>();
            orderObj.put("order", order);
            orderObj.put("meal", meal);
            vendorOrders.add(orderObj);
        }

        res.put("success", true);
        res.put("orders", vendorOrders);
        return res;
    }

}