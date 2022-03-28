/**
 * MESSAGE CRUD REPOSITORY
 * An CRUD Repository interface for Message objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.repository;

import com.btewebquest.data.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
}
