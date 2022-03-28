/**
 * CONTACT CRUD REPOSITORY
 * An CRUD Repository interface for Contact objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.repository;

import com.btewebquest.data.entity.ContactEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<ContactEntity, Long> {

}
