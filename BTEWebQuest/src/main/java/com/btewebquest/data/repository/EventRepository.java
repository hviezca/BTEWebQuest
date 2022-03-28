/**
 * EVENT CRUD REPOSITORY
 * An CRUD Repository interface for Event objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.repository;

import com.btewebquest.data.entity.EventEntity;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventEntity, Long> {
}
