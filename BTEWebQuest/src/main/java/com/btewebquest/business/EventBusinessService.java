package com.btewebquest.business;

import com.btewebquest.data.entity.EventEntity;
import com.btewebquest.data.service.EventDataService;
import com.btewebquest.model.BookingModel;
import com.btewebquest.model.EventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventBusinessService {

    @Autowired
    private EventDataService service;

    public EventEntity addEvent(EventModel event)
    {
        EventEntity eventEntity = new EventEntity(event.getEvent_id(),
                                                  event.getEvent_date(),
                                                  event.getEvent_price(),
                                                  event.isAll_ages(),
                                                  event.getVenue().getVenue_id());

        return service.createEvent(eventEntity);

    }
}
