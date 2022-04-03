/**
 * MANAGEMENT CONTROLLER
 * A Controller class for the Booking Management
 * Author @ Hiram Viezca
 */

package com.btewebquest.controller;

import com.btewebquest.business.BookingBusinessService;
import com.btewebquest.business.UserBusinessService;
import com.btewebquest.model.BookingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/BTE")
public class ManagementController {

    @Autowired
    BookingBusinessService bookingService;

    @Autowired
    UserBusinessService userService;

    @Autowired
    HttpSession session;

    /**
     * Displays the Admin Home page
     * @param model A Model object for transferring data to the View
     * @return admin-home.html
     */
    @GetMapping("/")
    public String adminHome(Model model)
    {
        // If user securely logged in, add to session data.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            int userID = userService.findUserByUsername(currentUserName).getId();
            if (null == session.getAttribute("user")) {
                session.setAttribute("user", currentUserName);
                session.setAttribute("userID", userID);
            }
        }

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
        List<BookingModel> bookings = bookingService.getBooking();

        model.addAttribute("title", "Booking Management");
        model.addAttribute("bookings", bookings);
        return "admin/booking/booking-management";
    }
}
