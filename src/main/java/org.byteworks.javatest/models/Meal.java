package org.byteworks.javatest;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Meal {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    

    private int id;

    private int vendorId;

    private double price;
    
    private String mealName;

    private String mealStatus;

    private String mealInfo;

	
        public Meal() {}
   
		public Meal(int vendorId, double price, String mealName, String mealStatus, String mealInfo) {
           this.vendorId = vendorId;
           this.price = price;
           this.mealName = mealName;
           this.mealInfo = mealInfo;
           this.mealStatus = mealStatus;
        } 
        

        public Integer getId() {
			return id;
		}

	public int getVendorId() {
		return vendorId;
	}

	public double getPrice() {
		return price;
	}

	public String getMealName() {
		return mealName;
    }
    
    public String getMealInfo() {
        return mealInfo;
    }
	public String getMealStatus() {
		return mealStatus;
    }
	
	
	


}