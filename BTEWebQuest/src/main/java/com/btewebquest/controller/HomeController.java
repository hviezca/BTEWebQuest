/**
 * HOME CONTROLLER
 * A Controller class for the Application Main page
 * Author @ Hiram Viezca
 */

package com.btewebquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Displays the Website Main page
     * @return index.html
     */
    @GetMapping("/")
    public String home()
    {
        return "home/index";
    }
}
