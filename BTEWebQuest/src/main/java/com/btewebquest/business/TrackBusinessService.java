package com.btewebquest.business;

import com.btewebquest.data.entity.AlbumEntity;
import com.btewebquest.data.entity.TrackEntity;
import com.btewebquest.data.service.TrackDataService;
import com.btewebquest.model.AlbumModel;
import com.btewebquest.model.TrackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackBusinessService {

    @Autowired
    private TrackDataService service;

    public TrackModel getTrackById(int id)
    {
        TrackEntity entity = service.findById(id);

        return new TrackModel(entity.getId(), entity.getTrackName(), entity.getTrackNumber(), entity.getAlbumId());
    }
}
