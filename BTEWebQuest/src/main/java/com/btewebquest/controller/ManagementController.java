/**
 * MANAGEMENT CONTROLLER
 * A Controller class for the Booking Management
 * Author @ Hiram Viezca
 */

package com.btewebquest.controller;

import com.btewebquest.business.BookingBusinessService;
import com.btewebquest.model.BookingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/BTE")
public class ManagementController {

    @Autowired
    BookingBusinessService service;

    /**
     * Displays the Admin Home page
     * @param model A Model object for transferring data to the View
     * @return admin-home.html
     */
    @GetMapping("/")
    public String adminHome(Model model)
    {
        model.addAttribute("username", "Hiram");
        return "admin/admin-home";
    }

    /**
     * Gets all Booking Records from database and display the Booking Management page
     * @param model A Model object for transferring data to the View
     * @return booking-management.html
     */
    @GetMapping("/booking-management")
    public String bookingManagement(Model model)
    {
        // Get all booking records from database.
        List<BookingModel> bookings = service.getBooking();

        model.addAttribute("title", "Booking Management");
        model.addAttribute("bookings", bookings);
        return "admin/booking/booking-management";
    }
}
