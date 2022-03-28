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

@Service
public class EventBusinessService {

    @Autowired
    private EventDataService service;

    @Autowired
    private VenueBusinessService venueBusinessService;

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
                                                  event.getVenue().getVenue_id());

        return service.createEvent(eventEntity);

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

        eventModel.setVenue(venueBusinessService.findVenueById(eventEntity.getVenue_id()));

        return eventModel;
    }
}
