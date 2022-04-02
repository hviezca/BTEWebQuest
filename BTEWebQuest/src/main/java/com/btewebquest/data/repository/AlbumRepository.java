package com.btewebquest.data.repository;

import com.btewebquest.data.entity.AlbumEntity;
import com.btewebquest.data.entity.TrackEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * CRUD Repository for AlbumEntity
 *
 * @author sfradet
 * @version 1.0
 */
public interface AlbumRepository extends CrudRepository<AlbumEntity, Long> {

    /**
     * Custom Query for getting Tracks for Album
     *
     * @param id Album ID
     * @return Set of TrackEntity
     */
    @Query("SELECT * FROM TRACKS WHERE ALBUM_ID = :id")
    Set<TrackEntity> findTracks(@Param("id") Long id);

}
