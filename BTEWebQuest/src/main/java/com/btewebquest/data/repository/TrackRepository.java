package com.btewebquest.data.repository;

import com.btewebquest.data.entity.TrackEntity;
import org.springframework.data.repository.CrudRepository;

public interface TrackRepository extends CrudRepository<TrackEntity, Long> {
}
