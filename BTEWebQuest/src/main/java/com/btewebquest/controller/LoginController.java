package com.btewebquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String display(Model model)
    {
        //Display Login view
        model.addAttribute("title", "Login Form");

        return "admin/loginTest";
    }

}
