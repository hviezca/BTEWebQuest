package com.btewebquest.data.service;

import com.btewebquest.data.entity.TrackEntity;
import com.btewebquest.data.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Data Service for actions related to Tracks
 *
 */
@Service
public class TrackDataService implements DataAccessInterface<TrackEntity>{

    @Autowired
    private TrackRepository trackRepository;

    public TrackDataService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public List<TrackEntity> findAll() {
        return null;
    }

    /**
     * Find Track by ID
     *
     * @param id id number of Object
     * @return List of TrackEntity
     */
    @Override
    public TrackEntity findById(int id) {

        // Optional Object to hold TrackEntity returned from database by id
        Optional<TrackEntity> track = trackRepository.findById((long) id);

        // If Optional Object not null, return TrackEntity, else throw NoSuchElement Exception
        return track.get();
    }

    /**
     * Create new Track in database
     *
     * @param trackEntity TrackEntity to add to database
     * @return boolean indicating success
     */
    @Override
    public boolean create(TrackEntity trackEntity) {

        try
        {
            // Attempt to save new TrackEntity to database
            trackRepository.save(trackEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Update TrackEntity in database
     *
     * @param trackEntity TrackEntity to be updated
     * @return boolean indicating success
     */
    @Override
    public boolean update(TrackEntity trackEntity)
    {
        try
        {
            // Attempt to update TrackModel in database
            trackRepository.save(trackEntity);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Delete Track from database
     *
     * @param trackEntity TrackEntity to be deleted from database
     * @return boolean indicating success
     */
    @Override
    public boolean delete(TrackEntity trackEntity) {

        try
        {
            // Attempt to delete TrackModel in database
            trackRepository.delete(trackEntity);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
