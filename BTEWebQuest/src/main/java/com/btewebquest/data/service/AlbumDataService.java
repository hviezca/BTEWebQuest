package com.btewebquest.data.service;

import com.btewebquest.data.entity.AlbumEntity;
import com.btewebquest.data.entity.TrackEntity;
import com.btewebquest.data.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumDataService implements DataAccessInterface<AlbumEntity> {

    @Autowired
    private AlbumRepository albumRepository;

    public AlbumDataService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<AlbumEntity> findAll() {

        //
        List<AlbumEntity> albums = new ArrayList<AlbumEntity>();

        try
        {
            //
            Iterable<AlbumEntity> userIterable = albumRepository.findAll();

            //
            userIterable.forEach(albums::add);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //
        return albums;
    }

    public List<TrackEntity> findAllTracksByID(int id)
    {
        //
        List<TrackEntity> tracks = new ArrayList<TrackEntity>();

        try
        {
            //
            Iterable<TrackEntity> userIterable = albumRepository.findTracks((long) id);

            //
            userIterable.forEach(tracks::add);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //
        return tracks;
    }

    @Override
    public AlbumEntity findById(int id) {

        // Optional Object to hold UserEntity returned from database by id
        Optional<AlbumEntity> album = albumRepository.findById((long) id);

        // If Optional Object not null, return UserEntity, else throw NoSuchElement Exception
        return album.get();
    }

    @Override
    public boolean create(AlbumEntity albumEntity) {
        return false;
    }

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
