package com.btewebquest.data.repository;

import com.btewebquest.data.entity.ContactEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<ContactEntity, Long> {

}
