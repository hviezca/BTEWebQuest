package com.btewebquest.data.service;

import com.btewebquest.data.entity.AlbumEntity;
import com.btewebquest.data.entity.TrackEntity;
import com.btewebquest.data.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public TrackEntity findById(int id) {

        // Optional Object to hold UserEntity returned from database by id
        Optional<TrackEntity> track = trackRepository.findById((long) id);

        // If Optional Object not null, return UserEntity, else throw NoSuchElement Exception
        return track.get();
    }

    @Override
    public boolean create(TrackEntity trackEntity) {
        return false;
    }

    @Override
    public boolean update(TrackEntity trackEntity) {
        return false;
    }

    @Override
    public boolean delete(TrackEntity trackEntity) {
        return false;
    }
}
