/**
 * VENUE CRUD REPOSITORY
 * An CRUD Repository interface for Venue objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.repository;

import com.btewebquest.data.entity.VenueEntity;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<VenueEntity,Long> {
}
