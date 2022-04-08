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

import java.util.ArrayList;
import java.util.List;

@Service
public class VenueBusinessService {

    @Autowired
    private VenueDataService service;

    @Autowired
    private ContactBusinessService contactBusinessService;

    /**
     * Return a List of Venues from database
     *
     * @return List<VenueModel> List of all Venues
     */
    public List<VenueModel> getVenues()
    {
        // Populate List of VenueEntity from database
        List<VenueEntity> venueEntities = service.findAll();

        // Empty List of VenueModel to hold results
        List<VenueModel> venues = new ArrayList<VenueModel>();

        // For each VenueEntity in venuesEntities List, add VenueModel to albums List
        for(VenueEntity entity : venueEntities)
        {
            venues.add(new VenueModel(entity.getId(), entity.getVenue_name(), entity.getVenue_address(), entity.getVenue_city(), entity.getVenue_state(), entity.getVenue_zip(), entity.getContact_id()));
        }

        // Return list of VenueModels
        return venues;
    }

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
     * Create new model in database
     *
     * @param venue return new VenueModel from database
     * @return new VenueModel
     */
    public VenueModel createVenue(VenueModel venue) {

        VenueEntity venueEntity = new VenueEntity(venue.getVenue_id(),
                venue.getVenue_name(),
                venue.getVenue_address(),
                venue.getVenue_city(),
                venue.getVenue_state(),
                venue.getVenue_zip(),
                venue.getContact().getContact_id());

        venueEntity = service.createVenue(venueEntity);

        return new VenueModel(venueEntity.getId(), venueEntity.getVenue_name(), venueEntity.getVenue_address(), venueEntity.getVenue_city(), venueEntity.getVenue_state(), venueEntity.getVenue_zip(), venueEntity.getContact_id());
    }

    /**
     * Update Venue in database
     *
     * @param venue VenueModel to be added to database
     * @return boolean indication operation success
     */
    public boolean updateVenue(VenueModel venue)
    {
        VenueEntity venueEntity = new VenueEntity(venue.getVenue_id(),
                venue.getVenue_name(),
                venue.getVenue_address(),
                venue.getVenue_city(),
                venue.getVenue_state(),
                venue.getVenue_zip(),
                venue.getContact().getContact_id());

        return service.update(venueEntity);
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
