package org.byteworks.javatest;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Order_ {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private Integer userId;

    private Integer mealId;

    private Integer vendorId;

    private double amount;
    
    private String paymentMethod;

    private String deliveryOption;



    private String orderStatus;

	
    public Order_() {}

		public Order_(int userId, int mealId, int vendorId, double amount, String paymentMethod,String deliveryOption) {
            this.userId = userId;
            this.mealId = mealId;
            this.vendorId = vendorId;
            this.amount = amount;
            this.paymentMethod = paymentMethod;
            this.deliveryOption = deliveryOption;
            this.orderStatus = Constants.ORDER_STATUS_PLACED;
        } 
        
        public int getId() {
			return id;
		}

	public int getUserId() {
		return userId;
	}

	public int getMealId() {
		return mealId;
	}

	public int getVendorId() {
		return vendorId;
	}
	public double getAmount() {
		return amount;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }
   
	
	


}