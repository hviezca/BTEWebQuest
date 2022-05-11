package com.btewebquest.controller;

import com.btewebquest.business.ContactBusinessService;
import com.btewebquest.business.ContactRequestBusinessService;
import com.btewebquest.business.MessageBusinessService;
import com.btewebquest.model.ContactModel;
import com.btewebquest.model.ContactRequestModel;
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
public class ContactUsManagementController
{
    @Autowired
    ContactRequestBusinessService service;

    @Autowired
    MessageBusinessService messageBusinessService;

    @Autowired
    ContactBusinessService contactBusinessService;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/contact-us-management")
    public String contactUsManagement(Model model)
    {
        // Get all contact request records from database.
        List<ContactRequestModel> contactRequests = service.getContactRequests();

        model.addAttribute("title", "Contact Us Management");
        model.addAttribute("contactRequests", contactRequests);
        return "admin/contact-us/contact-us-management";
    }

    @GetMapping("/contactRequest/json/{id}")
    public ResponseEntity<?> getContactRequestJson(@PathVariable("id") int id)
    {
        try
        {
            // Get the ContactRequest from the database
            ContactRequestModel contactRequest = service.findById(id);
            if (contactRequest == null)
            {
                // ContactRequest not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                // ContactRequest found. Return OK response and ContactRequest
                return new ResponseEntity<>(contactRequest, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            // Server error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/contactRequest/reply")
    public String replyToBookingMessageJson(@RequestBody ContactRequestModel request, Model model) {

        // Get the message and contact from the booking table for setting up the email
        MessageModel message = request.getMessage();
        ContactModel contact = request.getContact();

        // Check if the subject or message of the Message are empty. If so, can't send the email.
        if (message.getMessage() == null || message.getSubject() == null)
            // Message or Subject null. Cannot send email.
            return null;
        else if (contact.getContact_email() == null)
        {
            // Email null. Cannot send email.
            return null;
        }
        else
        {
            // Set up email to send
            SimpleMailMessage msg = new SimpleMailMessage();
            /////////// FOR TESTING ONLY
            //msg.setTo("btecontactservice@gmail.com");

            msg.setTo(contact.getContact_email());

            msg.setSubject(message.getSubject());
            msg.setText(message.getMessage());

            javaMailSender.send(msg);

            // Set the reply of the message to True to indicate the message has been replied to.
            message.setHas_reply(true);

            // Update the Message in the db
            messageBusinessService.replyToMessage(message);

            // Get all ContactRequest records from database to update the booking table
            List<ContactRequestModel> contactRequests = service.getContactRequests();

            model.addAttribute("title", "Contact Us Management");
            model.addAttribute("contactRequests", contactRequests);

            return "admin/contact-us/contact-us-management :: contactUsTableFragment";
        }
    }

    /**
     * Delete a ContactRequestEntity from the database.
     *
     * @param id - The contact_request_id of the ContactRequestEntity to be deleted
     * @param model - A Model Object to transfer data to the View
     * @return - The View fragment to be replaced in the View.
     */
    @DeleteMapping("/contactRequest/delete/{id}")
    public String deleteBooking(@PathVariable int id, Model model) {
        // Get Booking from database
        ContactRequestModel request = service.findById(id);

        /*
         * Since tables in DB are set to CASCADE ON DELETE, the deletion
         * has to begin with the last element in the chain. We begin deleting
         * a ContactRequestEntity by deleting the ContactEntity and the MessageEntity
         * associated with the ContactRequestEntity.
         * This way, the cascading deletions can work as intended all the way up to the
         * BookingEntity.
         */
        contactBusinessService.deleteContact(request.getContact());
        messageBusinessService.deleteMessage(request.getMessage());

        // Get all ContactRequest records from database to update the booking table
        List<ContactRequestModel> contactRequests = service.getContactRequests();

        model.addAttribute("title", "Contact Us Management");
        model.addAttribute("contactRequests", contactRequests);

        return "admin/contact-us/contact-us-management :: contactUsTableFragment";
    }
}
