package com.btewebquest.controller;

import com.btewebquest.business.EventBusinessService;
import com.btewebquest.business.VenueBusinessService;
import com.btewebquest.model.EventModel;
import com.btewebquest.model.VenueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/BTE")
public class EventController {

    @Autowired
    private EventBusinessService service;

    /**
     * Gets a message from the database and returns a response
     * to the requesting HTML page with the Event object
     * @param id The ID number of the Event object
     * @return A ResponseEntity and Event object
     */
    @GetMapping("/event/json/{id}")
    public ResponseEntity<?> getEventJson(@PathVariable("id") int id)
    {
        try
        {
            // Get the message from the database
            EventModel event = service.findById(id);
            if (event == null)
            {
                // Message not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                // Message found. Return OK response and message
                return new ResponseEntity<>(event, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            // Server error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
