package com.btewebquest.data.repository;

import com.btewebquest.data.entity.TrackEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD Repository for TrackEntity
 *
 * @author sfradet
 * @version 1.0
 */
public interface TrackRepository extends CrudRepository<TrackEntity, Long> {
}
