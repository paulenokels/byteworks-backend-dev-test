package org.byteworks.javatest;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Vendor {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    

    private int id;
    
    private String vendorName;

    private String vendorEmail;

    private String vendorPassword;

    private String vendorStatus;

    private double vendorLat;

    private double vendorLng;

    
        public Vendor() {}
    
   
		public Vendor(String vendorName, String vendorEmail, String vendorPassword, String vendorStatus, double vendorLat, double vendorLng) {
           this.vendorName = vendorName;
           this.vendorEmail = vendorEmail;
           this.vendorPassword = vendorPassword;
           this.vendorStatus = vendorStatus;
           this.vendorLat = vendorLat;
           this.vendorLng = vendorLng;
        } 
        

        public Integer getId() {
			return id;
		}

	public String getVendorName() {
        return vendorName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public String getVendorPassword() {
        return vendorPassword;
    }

    public String getVendorStatus() {
        return vendorStatus;
    }
    public double getVendorLat() {
        return vendorLat;
    }

    public double getVendorLng() {
        return vendorLng;
    }

  
	
	
	


}