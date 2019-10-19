/**
* This controller handles any uncaught error
* @author  Enokela Acheme Paul
* @email    achemepaulenokela@gmail.com
* @version 1.0
*/
package org.byteworks.javatest;

import java.util.HashMap;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppErrorController implements ErrorController {
    private static final String PATH = "/error";

    private static final String UNCAUGHT_ERROR_MSG = "An unknown error has occured, make sure you have called"
            + " the correct route and passed the required parameters. "
            + "Check the api documentation for routes and the required parameters."
            + " If problem persists contact byteworks!";

    @RequestMapping(value = PATH)
    public HashMap<String, Object> handleError() {
        HashMap<String, Object> res = new HashMap<>();

        res.put("success", false);
        res.put("msg", UNCAUGHT_ERROR_MSG);
        return res;

    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}
