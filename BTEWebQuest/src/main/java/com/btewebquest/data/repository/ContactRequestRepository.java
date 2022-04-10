package com.btewebquest.data.repository;

import com.btewebquest.data.entity.ContactRequestEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContactRequestRepository extends CrudRepository<ContactRequestEntity, Long> {
}
