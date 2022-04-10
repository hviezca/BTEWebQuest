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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingBusinessService bookingService;

    @Autowired
    private JavaMailSender javaMailSender;

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
            {
                notifyOfBookingRequest(booking);
                return new ResponseEntity<>("/booking", HttpStatus.OK);
                //return "admin/success";
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //return null;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void notifyOfBookingRequest(BookingModel booking) throws MessagingException {

        String subject = "New Booking Request From: " + booking.getEvent().getVenue().getContact().getContact_name();
        String msgBody = "You have a new booking request. Please click " +
                "<html><a href='https://breaktheearth.herokuapp.com/BTE/booking-management'>here</a></html> " +
                "to access your booking requests";

        // Set up email to send
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        /////////// FOR TESTING ONLY
        helper.setTo("hiramramirez3@gmail.com");

        //helper.setTo(booking.getEvent().getVenue().getContact().getContact_email());

        helper.setSubject(subject);
        helper.setText(msgBody, msgBody);

        javaMailSender.send(message);
    }
}
