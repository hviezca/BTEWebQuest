/**
 * VENUE DATA SERVICE
 * An Data Service class for Venue objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.service;

import com.btewebquest.data.entity.VenueEntity;
import com.btewebquest.data.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueDataService implements DataAccessInterface<VenueEntity> {

    private VenueRepository venueRepository;

    public VenueDataService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<VenueEntity> findAll() {
        return null;
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


    @Override
    public boolean update(VenueEntity venueEntity) {
        return false;
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

    public VenueEntity createVenue(VenueEntity venueEntity)
    {
        return venueRepository.save(venueEntity);
    }
}
