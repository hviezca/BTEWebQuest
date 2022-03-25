package com.btewebquest.business;

import com.btewebquest.data.entity.BookingEntity;
import com.btewebquest.data.entity.EventEntity;
import com.btewebquest.data.repository.BookingRepository;
import com.btewebquest.data.service.BookingDataService;
import com.btewebquest.data.service.EventDataService;
import com.btewebquest.data.service.MessageDataService;
import com.btewebquest.data.service.VenueDataService;
import com.btewebquest.model.BookingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingBusinessService {

    @Autowired
    private BookingDataService bookingDataService;

    @Autowired
    private EventBusinessService eventBusinessService;

    @Autowired
    private MessageBusinessService messageBusinessService;

    @Autowired
    private VenueBusinessService venueBusinessService;

    @Autowired
    private ContactBusinessService contactBusinessService;

    public boolean addBooking(BookingModel booking) {

        return bookingDataService.createBooking(booking);
    }
}
