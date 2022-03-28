/**
 * MESSAGE CONTROLLER
 * A Controller class for Message objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.controller;

import com.btewebquest.business.MessageBusinessService;
import com.btewebquest.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/BTE")
public class MessageController
{
    @Autowired
    private MessageBusinessService service;

    /**
     * Gets a message from the database and returns a response
     * to the requesting HTML page with the Message object
     * @param id The ID number of the Message object
     * @return A ResponseEntity and Message object
     */
    @GetMapping("/message/json/{id}")
    public ResponseEntity<?> getMessageJson(@PathVariable("id") int id)
    {
        try
        {
            // Get the message from the database
            MessageModel message = service.findById(id);
            if (message == null)
                // Message not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                // Message found. Return OK response and message
                return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e)
        {
            // Server error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}