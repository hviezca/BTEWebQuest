package com.btewebquest.controller;

import com.btewebquest.business.VenueBusinessService;
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
public class VenueController {

    @Autowired
    private VenueBusinessService service;

    /**
     * Gets a message from the database and returns a response
     * to the requesting HTML page with the Venue object
     * @param id The ID number of the Venue object
     * @return A ResponseEntity and venue object
     */
    @GetMapping("/venue/json/{id}")
    public ResponseEntity<?> getVenueJson(@PathVariable("id") int id)
    {
        try
        {
            // Get the message from the database
            VenueModel venue = service.findVenueById(id);
            if (venue == null)
                // Message not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                // Message found. Return OK response and message
                return new ResponseEntity<>(venue, HttpStatus.OK);
        }
        catch (Exception e)
        {
            // Server error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
