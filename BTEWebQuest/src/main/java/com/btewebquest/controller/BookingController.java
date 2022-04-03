/**
 * BOOKING CONTROLLER
 * A Controller class for Booking requests
 * Author @ Hiram Viezca
 */

package com.btewebquest.controller;

import com.btewebquest.business.BookingBusinessService;
import com.btewebquest.model.BookingModel;
import com.btewebquest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingBusinessService bookingService;

    /**
     * Display the Book Us form
     * @param model A Model object to collect form data
     * @return booking-form.html
     */
    @GetMapping("/booking")
    public String bookingForm(Model model)
    {
        model.addAttribute("title", "Booking Form");
        model.addAttribute("booking", new BookingModel());
        return "booking-form";
    }

    /**
     * Submits the booking form to persist form data in the database
     * @param booking A BookingModel object
     * @return booking-form.html
     */
    @PostMapping("/bookingSubmit")
    public ResponseEntity<?> bookingSubmit(@ModelAttribute BookingModel booking)
    {
        System.out.println("Entered BookingController.");
        ////////////// TESTING ////////////////
        /*System.out.println(booking.getEvent().getEvent_date());
        System.out.println(booking.getEvent().getVenue().getVenue_name());
        System.out.println(booking.getEvent().getVenue().getVenue_address());
        System.out.println(booking.getEvent().getVenue().getVenue_city());
        System.out.println(booking.getEvent().getVenue().getVenue_state());
        System.out.println(booking.getEvent().getVenue().getVenue_zip());
        System.out.println(booking.getMessage().getMessage());
        System.out.println(booking.getEvent().getVenue().getContact().getContact_name());
        System.out.println(booking.getEvent().getVenue().getContact().getContact_email());*/

        try {
            // Save Booking
            if(bookingService.addBooking(booking))
                return new ResponseEntity<>("/booking", HttpStatus.OK);
            //return "admin/success";
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //return null;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
