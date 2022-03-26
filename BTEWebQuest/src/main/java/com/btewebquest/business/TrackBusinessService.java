package com.btewebquest.business;

import com.btewebquest.data.entity.AlbumEntity;
import com.btewebquest.data.entity.TrackEntity;
import com.btewebquest.data.entity.UserEntity;
import com.btewebquest.data.service.TrackDataService;
import com.btewebquest.model.AlbumModel;
import com.btewebquest.model.TrackModel;
import com.btewebquest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackBusinessService {

    @Autowired
    private TrackDataService service;

    public TrackModel getTrackById(int id)
    {
        TrackEntity entity = service.findById(id);

        return new TrackModel(entity.getId(), entity.getTrackName(), entity.getTrackNumber(), entity.getAlbumId(), entity.isVocals(), entity.isGuitar(), entity.isDrums(), entity.isBass());
    }

    public boolean updateTrack(TrackModel track)
    {
        TrackEntity trackEntity = new TrackEntity(track.getId(), track.getTrackName(), track.getTrackNumber(), track.getAlbumId(), track.isVocals(), track.isGuitar(), track.isDrums(), track.isBass() );

        return service.update(trackEntity);
    }
}
