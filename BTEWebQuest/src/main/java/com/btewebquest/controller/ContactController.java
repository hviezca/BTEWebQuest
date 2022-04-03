package com.btewebquest.controller;

import com.btewebquest.business.ContactBusinessService;
import com.btewebquest.model.BookingModel;
import com.btewebquest.model.ContactModel;
import com.btewebquest.model.EventModel;
import com.btewebquest.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/BTE")
public class ContactController {

    @Autowired
    ContactBusinessService service;

    @GetMapping("/contact/json/{id}")
    public ResponseEntity<?> getContactJson(@PathVariable("id") int id)
    {
        try
        {
            // Get the message from the database
            ContactModel contact = service.findById(id);
            if (contact == null)
            {
                // Message not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                // Message found. Return OK response and message
                return new ResponseEntity<>(contact, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            // Server error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
