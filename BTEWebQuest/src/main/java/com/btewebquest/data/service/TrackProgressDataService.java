package com.btewebquest.data.service;

import com.btewebquest.data.entity.TrackProgressEntity;
import com.btewebquest.data.repository.TrackProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackProgressDataService implements DataAccessInterface<TrackProgressEntity>{

    @Autowired
    private TrackProgressRepository trackProgressRepository;

    public TrackProgressDataService(TrackProgressRepository trackProgressRepository) {
        this.trackProgressRepository = trackProgressRepository;
    }

    @Override
    public List<TrackProgressEntity> findAll() {
        return null;
    }

    @Override
    public TrackProgressEntity findById(int id) {
        return null;
    }

    @Override
    public boolean create(TrackProgressEntity trackProgressEntity) {
        return false;
    }

    @Override
    public boolean update(TrackProgressEntity trackProgressEntity) {
        return false;
    }

    @Override
    public boolean delete(TrackProgressEntity trackProgressEntity) {
        return false;
    }
}
