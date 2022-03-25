package com.btewebquest.data.service;

import com.btewebquest.data.entity.ContactEntity;
import com.btewebquest.data.entity.VenueEntity;
import com.btewebquest.data.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public VenueEntity findById(int id) {
        return null;
    }

    @Override
    public boolean create(VenueEntity venueEntity) {
        return false;
    }

    @Override
    public boolean update(VenueEntity venueEntity) {
        VenueEntity savedVenueEntity = venueRepository.save(venueEntity);

        if (savedVenueEntity.getId() != 0)
        {
            System.out.println("The Venue ID is: " + savedVenueEntity.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(VenueEntity venueEntity) {
        return false;
    }

    public VenueEntity createVenue(VenueEntity venueEntity)
    {
        return venueRepository.save(venueEntity);
    }
}
