package com.btewebquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/BTE")
public class ManagementController {

    @GetMapping("/")
    public String adminHome(Model model)
    {
        model.addAttribute("username", "Hiram");
        return "admin/admin-home";
    }

    @GetMapping("/booking-management")
    public String BookingManagement()
    {
        return "admin/booking-management";
    }
}
