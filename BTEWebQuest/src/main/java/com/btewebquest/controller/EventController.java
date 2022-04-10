package com.btewebquest.controller;

import com.btewebquest.business.ContactBusinessService;
import com.btewebquest.business.EventBusinessService;
import com.btewebquest.business.VenueBusinessService;
import com.btewebquest.model.ContactModel;
import com.btewebquest.model.EventModel;
import com.btewebquest.model.VenueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/BTE")
public class EventController {

    @Autowired
    private EventBusinessService eventService;

    @Autowired
    private VenueBusinessService venueService;

    @Autowired
    private ContactBusinessService contactService;


    /**
     * Get Event Management view populated with all available Events
     *
     * @param model View Model for passing data to view
     * @return events.html page
     */
    @GetMapping("/event")
    public String events(Model model)
    {
        // Get List of all Users from database
        List<EventModel> events = eventService.getEvents();

        // Populate Venue data
        for(EventModel event : events)
        {
            event.setVenue(venueService.findVenueById(event.getVenue().getVenue_id()));
        }

        List<VenueModel> venues = venueService.getVenues();

        // Setup information for View Model
        model.addAttribute("title", "Event Management");
        model.addAttribute("eventList", events);
        model.addAttribute("venueList", venues);


        return "admin/event-management/events";
    }

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
            // Get the Event from the database
            EventModel event = eventService.findById(id);

            if (event == null)
            {
                // Event not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                // Event found. Return OK response and EVent
                return new ResponseEntity<>(event, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            // Server error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get new EventModel returned by JSON
     *
     * @return EventModel as JSON
     */
    @GetMapping("/event/neweventjson")
    public ResponseEntity<?> getNewEventJson()
    {
        // Unpopulated EventModel
        EventModel newEvent = new EventModel();

        try
        {
            // Event found. Return OK response and Event
            return new ResponseEntity<>(newEvent, HttpStatus.OK);
        }
        catch (Exception e)
        {
            // Server error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update Event in database
     *
     * @param updatedEvent updated EventModel
     * @param result BindingResult model holding validation results
     * @param model View model for passing data to the view
     * @return updated HTML fragment
     */
    @RequestMapping("/event")
    public String updateEvent(@RequestBody EventModel updatedEvent, BindingResult result, Model model) {

        // Update Contact
        contactService.updateContact(updatedEvent.getVenue().getContact());

        // Update Venue
        venueService.updateVenue(updatedEvent.getVenue());

        // Update Event
        eventService.updateEvent(updatedEvent);

        // Get List of all Events from database
        List<EventModel> events = eventService.getEvents();

        for(EventModel event : events)
        {
            event.setVenue(venueService.findVenueById(event.getVenue().getVenue_id()));
        }

        List<VenueModel> venues = venueService.getVenues();

        // Setup information for View Model
        model.addAttribute("title", "Event Management");
        model.addAttribute("eventList", events);
        model.addAttribute("venueList", venues);

        return "admin/event-management/event-Fragment :: #eventFrag";
    }

    /**
     * Add new Event to database
     *
     * @param newEvent new EventModel to add to database
     * @param result BindingResult with validation data
     * @param model View model for passing information to view
     * @return HTML fragment
     */
    @RequestMapping("/event/addevent")
    public String addNewEvent(@RequestBody EventModel newEvent, BindingResult result, Model model) {

        // Verify if new Event uses existing Venue or new Venue
        if (newEvent.getVenue().getVenue_id() != 0)
        {
            // Get existing Venue
            newEvent.setVenue(venueService.findVenueById(newEvent.getVenue().getVenue_id()));
        }else {
            // Create new Contact for Event
            ContactModel newContact = contactService.createContact(newEvent.getVenue().getContact());
            newEvent.getVenue().getContact().setContact_id(newContact.getContact_id());
            // Create new Venue for Event
            VenueModel newVenue = venueService.createVenue(newEvent.getVenue());
            newEvent.getVenue().setVenue_id(newVenue.getVenue_id());
        }

        // Add new event
        eventService.addEvent(newEvent);

        // Get List of all Events from database
        List<EventModel> events = eventService.getEvents();

        for(EventModel event : events)
        {
            event.setVenue(venueService.findVenueById(event.getVenue().getVenue_id()));
        }

        List<VenueModel> venues = venueService.getVenues();

        // Setup information for View Model
        model.addAttribute("title", "Event Management");
        model.addAttribute("eventList", events);
        model.addAttribute("venueList", venues);

        return "admin/event-management/event-Fragment :: #eventFrag";
    }

    /**
     * Delete Event by ID
     *
     * @param id ID of Event to be deleted
     * @param model View model for passing data to the view
     * @return updated HTML Fragment
     */
    @DeleteMapping("/event/{id}")
    public String deleteEvent(@PathVariable int id, Model model) {

        // Get Event from database
        EventModel oldEvent = eventService.findById(id);

        // Delete Event from database
        eventService.deleteEvent(oldEvent);

        // Get List of all Events from database
        List<EventModel> events = eventService.getEvents();

        for(EventModel event : events)
        {
            event.setVenue(venueService.findVenueById(event.getVenue().getVenue_id()));
        }

        List<VenueModel> venues = venueService.getVenues();

        // Setup information for View Model
        model.addAttribute("title", "Event Management");
        model.addAttribute("eventList", events);
        model.addAttribute("venueList", venues);

        return "admin/event-management/event-Fragment :: #eventFrag";
    }
}
