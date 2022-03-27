package com.btewebquest.business;

import com.btewebquest.data.entity.TrackEntity;
import com.btewebquest.data.service.TrackDataService;
import com.btewebquest.model.TrackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business service for all actions related to Tracks
 *
 * @author sfradet
 * @version 1.0
 */
@Service
public class TrackBusinessService {

    @Autowired
    private TrackDataService service;

    /**
     * Return Track by ID
     *
     * @param id Id of desired Track
     * @return TrackModel
     */
    public TrackModel getTrackById(int id)
    {
        TrackEntity entity = service.findById(id);

        return new TrackModel(entity.getId(), entity.getTrackName(), entity.getTrackNumber(), entity.getAlbumId(), entity.isVocals(), entity.isGuitar(), entity.isDrums(), entity.isBass());
    }

    /**
     * Update TrackModel in database
     *
     * @param track TrackModel to be updated
     * @return boolean indicating success
     */
    public boolean updateTrack(TrackModel track)
    {
        TrackEntity trackEntity = new TrackEntity(track.getId(), track.getTrackName(), track.getTrackNumber(), track.getAlbumId(), track.isVocals(), track.isGuitar(), track.isDrums(), track.isBass() );

        return service.update(trackEntity);
    }

    /**
     * Add TrackModel to database
     *
     * @param track TrackModel to be added to database
     * @return boolean indicating success
     */
    public boolean addTrack(TrackModel track)
    {
        TrackEntity trackEntity = new TrackEntity(track.getId(), track.getTrackName(), track.getTrackNumber(), track.getAlbumId(), track.isVocals(), track.isGuitar(), track.isDrums(), track.isBass() );

        return service.create(trackEntity);
    }

    /**
     * Delete Track from database
     *
     * @param track TrackModel to be deleted from database
     * @return boolean indicating success
     */
    public boolean deleteTrack(TrackModel track)
    {
        TrackEntity trackEntity = new TrackEntity(track.getId(), track.getTrackName(), track.getTrackNumber(), track.getAlbumId(), track.isVocals(), track.isGuitar(), track.isDrums(), track.isBass() );

        return service.delete(trackEntity);
    }

}
