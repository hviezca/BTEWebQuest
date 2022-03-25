package com.btewebquest.data.repository;

import com.btewebquest.data.entity.BookingEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<BookingEntity, Long> {
}
