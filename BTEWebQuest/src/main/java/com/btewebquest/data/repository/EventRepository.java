/**
 * EVENT CRUD REPOSITORY
 * An CRUD Repository interface for Event objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.repository;

import com.btewebquest.data.entity.EventEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<EventEntity, Long> {

    /**
     * Fina all Events and sort by DATE
     *
     * @param DATE Parameter to be sorted by
     * @return List<EventEntity> of sorted Events
     */
    List<EventEntity> findAll(Sort DATE);
}
