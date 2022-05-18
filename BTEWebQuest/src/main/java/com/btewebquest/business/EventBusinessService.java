/**
 * EVENT BUSINESS SERVICE
 * A business service class for Event objects
 * Author @ Hiram Viezca
 */


package com.btewebquest.business;

import com.btewebquest.data.entity.EventEntity;
import com.btewebquest.data.service.EventDataService;
import com.btewebquest.model.EventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventBusinessService {

    @Autowired
    private EventDataService service;

    @Autowired
    private VenueBusinessService venueBusinessService;

    /**
     * Get all Events from the database
     *
     *
     * @return List of EventModels
     */
    public List<EventModel> getEvents()
    {
        //List<EventEntity> eventEntities = service.findAll();
        List<EventEntity> eventEntities = service.findAllOrderByDate();

        // Empty List of EventModel to hold results
        List<EventModel> events = new ArrayList<EventModel>();

        // For each EventEntity in eventEntities List, add EventModel to events List
        for(EventEntity entity : eventEntities)
        {
            events.add(new EventModel(entity.getId(), entity.getDate(), entity.getPrice(), entity.isAll_ages(), entity.isBooked(), entity.getVenue_id()));
        }

        // Return list of EventModels
        return events;
    }

    public List<EventModel> getBookedEvents()
    {
        //List<EventEntity> eventEntities = service.findAll();
        List<EventEntity> eventEntities = service.findAllBooked();

        // Empty List of EventModel to hold results
        List<EventModel> events = new ArrayList<EventModel>();

        // For each EventEntity in eventEntities List, add EventModel to events List
        for(EventEntity entity : eventEntities)
        {
            events.add(new EventModel(entity.getId(), entity.getDate(), entity.getPrice(), entity.isAll_ages(), entity.isBooked(), entity.getVenue_id()));
        }

        // Return list of EventModels
        return events;
    }

    /**
     * Delete Event from database
     *
     * @param event EventModel to be deleted
     * @return boolean indicating operation success
     */
    public boolean deleteEvent(EventModel event)
    {
        EventEntity eventEntity = new EventEntity(event.getEvent_id(),
                event.getEvent_date(),
                event.getEvent_price(),
                event.isAll_ages(),
                event.isBooked(),
                event.getVenue().getVenue_id());

        return service.delete(eventEntity);
    }

    /**
     * Add an Event to the database
     * @param event An EventModel object
     * @return An EventEntity object
     */
    public EventEntity addEvent(EventModel event)
    {
        EventEntity eventEntity = new EventEntity(event.getEvent_id(),
                                                  event.getEvent_date(),
                                                  event.getEvent_price(),
                                                  event.isAll_ages(),
                                                  event.isBooked(),
                                                  event.getVenue().getVenue_id());

        return service.createEvent(eventEntity);

    }

    /**
     * Update Event in database
     *
     * @param event EventModel to be updated
     * @return boolean indicating operation success
     */
    public boolean updateEvent(EventModel event)
    {
        EventEntity eventEntity = new EventEntity(event.getEvent_id(),
                event.getEvent_date(),
                event.getEvent_price(),
                event.isAll_ages(),
                event.isBooked(),
                event.getVenue().getVenue_id());

        return service.update(eventEntity);
    }

    /**
     * Find an EventModel by its ID number
     * @param id - int - An EventModel ID number
     * @return An EventModel object
     */
    public EventModel findById(int id)
    {
        EventModel eventModel = new EventModel();
        EventEntity eventEntity = service.findById(id);
        eventModel.setEvent_id(eventEntity.getId());
        eventModel.setEvent_date(eventEntity.getDate());
        eventModel.setEvent_price(eventEntity.getPrice());
        eventModel.setAll_ages(eventEntity.isAll_ages());
        eventModel.setBooked(eventEntity.isBooked());

        eventModel.setVenue(venueBusinessService.findVenueById(eventEntity.getVenue_id()));

        return eventModel;
    }
}
