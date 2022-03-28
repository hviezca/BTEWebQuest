package com.btewebquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for Logging in User
 *
 * @author sfradet
 * @version 1.0
 */
@Controller
public class LoginController {

    /**
     * Display login page
     *
     * @param model View Model
     * @return Login page
     */
    @GetMapping("/login")
    public String display(Model model)
    {
        //Display Login view
        model.addAttribute("title", "Login Form");

        return "admin/loginTest";
    }

}
