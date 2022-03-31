package com.btewebquest.business;

import com.btewebquest.data.entity.AlbumEntity;
import com.btewebquest.data.entity.TrackEntity;
import com.btewebquest.data.service.AlbumDataService;
import com.btewebquest.model.AlbumModel;
import com.btewebquest.model.TrackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Business service for all actions related to Albums
 *
 * @author sfradet
 * @version 1.0 *
 */
@Service
public class AlbumBusinessService {

    @Autowired
    private AlbumDataService service;

    /**
     * Returns a List of Albums from the database
     *
     * @return List of AlbumModel
     */
    public List<AlbumModel> getAlbums()
    {
        // Populate List of AlbumEntity from database
        List<AlbumEntity> albumEntities = service.findAll();

        // Empty List of AlbumModel to hold results
        List<AlbumModel> albums = new ArrayList<AlbumModel>();

        // For each AlbumEntity in albumEntity List, add AlbumModel to albums List
        for(AlbumEntity entity : albumEntities)
        {
            albums.add(new AlbumModel(entity.getId(), entity.getAlbumName(), entity.getAlbumYear(), entity.isMixed(), entity.isMastered(), entity.getImageName()));
        }

        // Return list of AlbumModels
        return albums;
    }

    /**
     * Returns list of TrackModel linked to a Album ID
     *
     *
     * @param id Album Id
     * @return List of TrackModel associated with Album
     */
    public List<TrackModel> getTracks(int id)
    {
        // Populate list of TrackEntity with tracks associated to Album id
        List<TrackEntity> trackEntities = service.findAllTracksByID(id);

        // Empty List of TrackModels to hold results
        List<TrackModel> tracks = new ArrayList<TrackModel>();

        // For each trackEntity in trackEntity List, add TrackModel to tracks List
        for(TrackEntity entity : trackEntities)
        {
            tracks.add(new TrackModel(entity.getId(), entity.getTrackName(), entity.getTrackNumber(), entity.getAlbumId(), entity.isVocals(), entity.isGuitar(), entity.isDrums(), entity.isBass()));
        }

        // Return list of TrackModels
        return tracks;
    }

    /**
     * Return Album by ID
     *
     * @param id Id of desired Album
     * @return AlbumModel
     */
    public AlbumModel getAlbumById(int id)
    {
        AlbumEntity entity = service.findById(id);

        return new AlbumModel(entity.getId(), entity.getAlbumName(), entity.getAlbumYear(), entity.isMixed(), entity.isMastered(), entity.getImageName());
    }

    /**
     * Update Album in database
     *
     * @param album AlbumModel to be updated
     * @return boolean indicating success
     */
    public boolean updateAlbum(AlbumModel album)
    {
        AlbumEntity albumEntity = new AlbumEntity(album.getId(), album.getAlbumName(), album.getAlbumYear(), album.isMixed(), album.isMastered(), album.getAlbumImage());

        return service.update(albumEntity);
    }

}
