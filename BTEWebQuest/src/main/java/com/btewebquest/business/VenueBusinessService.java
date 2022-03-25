package com.btewebquest.business;

import com.btewebquest.data.entity.VenueEntity;
import com.btewebquest.data.service.VenueDataService;
import com.btewebquest.model.BookingModel;
import com.btewebquest.model.VenueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueBusinessService {

    @Autowired
    private VenueDataService service;

    public VenueEntity addVenue(VenueModel venue) {
        VenueEntity venueEntity = new VenueEntity(venue.getVenue_id(),
                venue.getVenue_name(),
                venue.getVenue_address(),
                venue.getVenue_city(),
                venue.getVenue_state(),
                venue.getVenue_zip(),
                venue.getContact().getContact_id());

        return service.createVenue(venueEntity);
    }
}
