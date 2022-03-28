/**
 * VENUE BUSINESS SERVICE
 * A business service class for Venue objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.business;

import com.btewebquest.data.entity.VenueEntity;
import com.btewebquest.data.service.VenueDataService;
import com.btewebquest.model.VenueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueBusinessService {

    @Autowired
    private VenueDataService service;

    @Autowired
    private ContactBusinessService contactBusinessService;

    /**
     * Add a Venue to the database
     * @param venue A VenueModel object
     * @return A VenueEntity object
     */
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

    /**
     * Find a VenueModel object by its ID number
     * @param id A VenueModel object ID number
     * @return A VenueModel object
     */
    public VenueModel findVenueById(int id)
    {
        VenueEntity venueEntity = service.findById(id);
        VenueModel venueModel = new VenueModel();

        venueModel.setVenue_id(venueEntity.getId());
        venueModel.setVenue_name(venueEntity.getVenue_name());
        venueModel.setVenue_address(venueEntity.getVenue_address());
        venueModel.setVenue_city(venueEntity.getVenue_city());
        venueModel.setVenue_state(venueEntity.getVenue_state());
        venueModel.setVenue_zip(venueEntity.getVenue_zip());
        venueModel.setContact(contactBusinessService.findById(venueEntity.getContact_id()));

        return venueModel;
    }
}
