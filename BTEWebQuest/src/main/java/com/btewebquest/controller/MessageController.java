/**
 * MESSAGE CONTROLLER
 * A Controller class for Message objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.controller;

import com.btewebquest.business.BookingBusinessService;
import com.btewebquest.business.MessageBusinessService;
import com.btewebquest.model.BookingModel;
import com.btewebquest.model.ContactModel;
import com.btewebquest.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/BTE")
public class MessageController {
    @Autowired
    private MessageBusinessService service;

    @Autowired
    private BookingBusinessService bookingBusinessService;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Gets a message from the database and returns a response
     * to the requesting HTML page with the Message object
     *
     * @param id The ID number of the Message object
     * @return A ResponseEntity and Message object
     */
    @GetMapping("/message/json/{id}")
    public ResponseEntity<?> getMessageJson(@PathVariable("id") int id) {
        try {
            // Get the message from the database
            MessageModel message = service.findById(id);
            if (message == null)
                // Message not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                // Message found. Return OK response and message
                return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            // Server error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Sends a reply to the selected Message via Spring Email and returns a
     * fragment of the booking table so the whole page does not refresh.
     * @param booking - A BookingModel object.
     * @param model - A Model object for data transfer to the View.
     * @return - A booking table fragment for updating the booking table.
     */
    @PostMapping("/message/reply")
    public String replyToBookingMessageJson(@RequestBody BookingModel booking, Model model) {

        // Get the message and contact from the booking table for setting up the email
        MessageModel message = booking.getMessage();
        ContactModel contact = booking.getEvent().getVenue().getContact();

        // Check if the subject or message of the Message are empty. If so, can't send the email.
        if (message.getMessage() == null || message.getSubject() == null)
            // Message or Subject null. Cannot send email.
            return null;
        else if (contact.getContact_email() == null)
        {
            // Email nul. Cannot send email.
            return null;
        }
        else
        {
            // Set up email to send
            SimpleMailMessage msg = new SimpleMailMessage();
            /////////// FOR TESTING ONLY
            msg.setTo("hiramramirez3@gmail.com");

            //msg.setTo(contact.getContact_email());

            msg.setSubject(message.getSubject());
            msg.setText(message.getMessage());

            javaMailSender.send(msg);

            // Set the reply of the message to True to indicate the message has been replied to.
            message.setHas_reply(true);

            // Update the Message in the db
            service.replyToMessage(message);

            // Get all booking records from database to update the booking table
            List<BookingModel> bookings = bookingBusinessService.getBooking();

            model.addAttribute("title", "Booking Management");
            model.addAttribute("bookings", bookings);

            return "admin/booking/booking-management :: #bookingTable";
        }
    }
}