/**
* This file handles sending of error response
* @author  Enokela Acheme Paul
* @email    achemepaulenokela@gmail.com
* @version 1.0
*/
package org.byteworks.javatest;

import java.util.HashMap;
public class ErrorResponse {
    private static final String SUCCESS = "success";
    private static final String MSG = "msg";

public static HashMap<String,Object> respond(String msg) {
        HashMap<String, Object> res = new HashMap<>();
        res.put(SUCCESS, false);
        res.put(MSG, msg);
        return res;
    }
}