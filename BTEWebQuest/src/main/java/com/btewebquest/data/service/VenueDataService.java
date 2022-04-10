/**
 * VENUE DATA SERVICE
 * An Data Service class for Venue objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.service;

import com.btewebquest.data.entity.VenueEntity;
import com.btewebquest.data.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VenueDataService implements DataAccessInterface<VenueEntity> {

    private VenueRepository venueRepository;

    public VenueDataService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    /**
     * Return List of all Venues from database
     *
     * @return List of VenueEntity
     */
    @Override
    public List<VenueEntity> findAll() {

        // Empty List of VenueEntity
        List<VenueEntity> venues = new ArrayList<VenueEntity>();

        try
        {
            // Iterable result set from database
            Iterable<VenueEntity> userIterable = venueRepository.findAll();

            // Step through Iterable set and add to VenueEntity List
            userIterable.forEach(venues::add);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Return List of VenueEntity
        return venues;
    }

    /**
     * Find a Venue object from the database
     * @param id The ID number of Venue Object
     * @return A VenueEntity object
     */
    @Override
    public VenueEntity findById(int id)
    {
        Optional<VenueEntity> venue = venueRepository.findById((long) id);

        return venue.get();
    }

    /**
     * Save a Venue object to the database
     * @param venueEntity A VenueEntity object
     * @return True if successful. False if not successful
     */
    @Override
    public boolean create(VenueEntity venueEntity) {

        VenueEntity savedVenueEntity = venueRepository.save(venueEntity);

        if (savedVenueEntity.getId() != 0)
        {
            System.out.println("The Venue ID is: " + savedVenueEntity.getId());
            return true;
        }
        return false;
    }

    /**
     * Create new Venue and return ID
     *
     * @param venueEntity VenueEntity to be created
     * @return new VenueEntity with ID
     */
    public int createReturnId(VenueEntity venueEntity) {
        return venueRepository.save(venueEntity).getId();
    }

    /**
     * Update Venue in database
     *
     * @param venueEntity VenueEntity to be updated
     * @return boolean indicating operation success
     */
    @Override
    public boolean update(VenueEntity venueEntity) {
        try
        {
            // Attempt to update  in database
            venueRepository.save(venueEntity);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Save a Venue object to the database
     * @param venueEntity A MessageEntity object
     * @return A VenueEntity object with its ID number from the database
     */
    @Override
    public boolean delete(VenueEntity venueEntity) {
        return false;
    }

    /**
     * Create new Venue in database
     *
     * @param venueEntity VenueEntity to be created
     * @return new VenueEntity
     */
    public VenueEntity createVenue(VenueEntity venueEntity)
    {
        return venueRepository.save(venueEntity);
    }
}
