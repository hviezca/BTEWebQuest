package com.btewebquest.business;

import com.btewebquest.data.entity.AlbumEntity;
import com.btewebquest.data.entity.TrackEntity;
import com.btewebquest.data.entity.TrackProgressEntity;
import com.btewebquest.data.entity.UserEntity;
import com.btewebquest.data.service.AlbumDataService;
import com.btewebquest.model.AlbumModel;
import com.btewebquest.model.TrackModel;
import com.btewebquest.model.TrackProgressModel;
import com.btewebquest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumBusinessService {

    @Autowired
    private AlbumDataService service;

    public List<AlbumModel> getAlbums()
    {
        //
        List<AlbumEntity> albumEntities = service.findAll();

        // Empty List of CoffeeModels to hold results
        List<AlbumModel> albums = new ArrayList<AlbumModel>();

        // For each CoffeeEntity in coffeeEntity List, add CoffeeModel to coffeeDomain List
        for(AlbumEntity entity : albumEntities)
        {
            albums.add(new AlbumModel(entity.getId(), entity.getAlbumName(), entity.getAlbumYear(), entity.isMixed(), entity.isMastered()));
        }

        // Return list of CoffeeModels
        return albums;
    }

    public List<TrackModel> getTracks(int id)
    {
        //
        List<TrackEntity> trackEntities = service.findAllTracksByID(id);

        // Empty List of CoffeeModels to hold results
        List<TrackModel> tracks = new ArrayList<TrackModel>();

        // For each CoffeeEntity in coffeeEntity List, add CoffeeModel to coffeeDomain List
        for(TrackEntity entity : trackEntities)
        {
            tracks.add(new TrackModel(entity.getId(), entity.getTrackName(), entity.getTrackNumber(), entity.getAlbumId()));
        }

        // Return list of CoffeeModels
        return tracks;
    }

    public List<TrackProgressModel> getTrackProgress(int id)
    {
        //
        List<TrackProgressEntity> trackProgressEntities = service.findAllTrackProgressByID(id);

        // Empty List of CoffeeModels to hold results
        List<TrackProgressModel> trackProgress = new ArrayList<TrackProgressModel>();

        // For each CoffeeEntity in coffeeEntity List, add CoffeeModel to coffeeDomain List
        for(TrackProgressEntity entity : trackProgressEntities)
        {
            trackProgress.add(new TrackProgressModel(entity.getId(), entity.getInstrumentName(), entity.isRecorded()));
        }

        // Return list of CoffeeModels
        return trackProgress;
    }

    public AlbumModel getAlbumById(int id)
    {
        AlbumEntity entity = service.findById(id);

        return new AlbumModel(entity.getId(), entity.getAlbumName(), entity.getAlbumYear(), entity.isMixed(), entity.isMastered());
    }

}
