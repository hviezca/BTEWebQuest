package com.btewebquest.data.service;

import com.btewebquest.data.entity.AlbumEntity;
import com.btewebquest.data.entity.TrackEntity;
import com.btewebquest.data.entity.TrackProgressEntity;
import com.btewebquest.data.entity.UserEntity;
import com.btewebquest.data.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<TrackProgressEntity> findAllTrackProgressByID(int id)
    {
        //
        List<TrackProgressEntity> trackProgress = new ArrayList<TrackProgressEntity>();

        try
        {
            //
            Iterable<TrackProgressEntity> userIterable = albumRepository.findTrackProgress((long) id);

            //
            userIterable.forEach(trackProgress::add);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //
        return trackProgress;
    }

    @Override
    public AlbumEntity findById(int id) {
        return null;
    }

    @Override
    public boolean create(AlbumEntity albumEntity) {
        return false;
    }

    @Override
    public boolean update(AlbumEntity albumEntity) {
        return false;
    }

    @Override
    public boolean delete(AlbumEntity albumEntity) {
        return false;
    }
}
