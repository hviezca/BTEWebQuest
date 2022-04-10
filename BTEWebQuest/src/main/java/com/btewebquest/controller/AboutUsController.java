package com.btewebquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {

    @GetMapping("/aboutUs")
    public String getAboutUs(Model model){

        model.addAttribute("title", "About Us");

        return "about-us";
    }
}
