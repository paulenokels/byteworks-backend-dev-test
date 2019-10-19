/**
* This class checks if email input is valid
* @author  Enokela Acheme Paul
* @email    achemepaulenokela@gmail.com
* @version 1.0
*/
package org.byteworks.javatest;
import java.util.regex.Pattern; 

public class EmailValidator {

    public static boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
         Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches();                     
    }
}