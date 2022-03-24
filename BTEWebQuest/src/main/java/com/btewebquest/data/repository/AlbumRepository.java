package com.btewebquest.data.repository;

import com.btewebquest.data.entity.AlbumEntity;
import com.btewebquest.data.entity.RoleEntity;
import com.btewebquest.data.entity.TrackEntity;
import com.btewebquest.data.entity.TrackProgressEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AlbumRepository extends CrudRepository<AlbumEntity, Long> {

    @Query("SELECT * FROM tracks WHERE ALBUM_ID = :id")
    Set<TrackEntity> findTracks(@Param("id") Long id);

    @Query("SELECT * FROM progress WHERE TRACK_ID = :id")
    Set<TrackProgressEntity> findTrackProgress(@Param("id") Long id);
}
