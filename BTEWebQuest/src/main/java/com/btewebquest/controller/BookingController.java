/*Controller file for Book Us functions
* Author @ Hiram Viezca
* */

package com.btewebquest.controller;

import com.btewebquest.business.*;
import com.btewebquest.data.entity.*;
import com.btewebquest.data.service.MessageDataService;
import com.btewebquest.model.BookingModel;
import com.btewebquest.model.EventModel;
import com.btewebquest.model.MessageModel;
import com.btewebquest.model.VenueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class BookingController {

    @Autowired
    private BookingBusinessService bookingService;

    @GetMapping("/booking")
    public String bookingForm(Model model)
    {
        model.addAttribute("title", "Booking Form");
        model.addAttribute("booking", new BookingModel());
        return "booking-form";
    }

    @PostMapping("/bookingSubmit")
    public String  bookingSubmit(@ModelAttribute BookingModel booking, Model model)
    {
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

        // Save Booking
        if(bookingService.addBooking(booking))
            return "redirect:/booking";
            //return "admin/success";

        return "admin/error";
        //return null;
    }
}
