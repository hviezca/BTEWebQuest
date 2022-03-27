package com.btewebquest.data.service;

import com.btewebquest.data.entity.AlbumEntity;
import com.btewebquest.data.entity.TrackEntity;
import com.btewebquest.data.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data Service for Albums
 *
 * @author sfradet
 * @version 1.0
 */
@Service
public class AlbumDataService implements DataAccessInterface<AlbumEntity> {

    @Autowired
    private AlbumRepository albumRepository;

    public AlbumDataService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    /**
     * Get all Albums from database
     *
     * @return List of AlbumEntity
     */
    @Override
    public List<AlbumEntity> findAll() {

        // Empty List of AlbumEntity
        List<AlbumEntity> albums = new ArrayList<AlbumEntity>();

        // Get results and add to List
        try
        {
            // Get List of AlbumEntity
            Iterable<AlbumEntity> userIterable = albumRepository.findAll();

            // Add results to List
            userIterable.forEach(albums::add);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // List of albums
        return albums;
    }

    /**
     * Find Tracks by Album ID
     *
     * @param id Album ID
     * @return List of TrackEntity
     */
    public List<TrackEntity> findAllTracksByID(int id)
    {
        // Empty TrackEntity list
        List<TrackEntity> tracks = new ArrayList<TrackEntity>();

        try
        {
            // Get Iterable of TrackEntity from database
            Iterable<TrackEntity> userIterable = albumRepository.findTracks((long) id);

            // Step through Iterable and add results to List
            userIterable.forEach(tracks::add);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // List of TrackEntity
        return tracks;
    }

    /**
     * Find Album by ID
     *
     * @param id id number of Album
     * @return AlbumEntity
     */
    @Override
    public AlbumEntity findById(int id) {

        // Optional Object to hold AlbumEntity returned from database by id
        Optional<AlbumEntity> album = albumRepository.findById((long) id);

        // If Optional Object not null, return AlbumEntity, else throw NoSuchElement Exception
        return album.get();
    }

    @Override
    public boolean create(AlbumEntity albumEntity) {
        return false;
    }

    /**
     * Update Album in database
     *
     * @param albumEntity AlbumEntity to be updated
     * @return boolean indicating success
     */
    @Override
    public boolean update(AlbumEntity albumEntity) {
        try
        {
            // Attempt to update  in database
            albumRepository.save(albumEntity);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(AlbumEntity albumEntity) {
        return false;
    }
}
