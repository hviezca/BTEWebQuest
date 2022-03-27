package com.btewebquest.data.repository;

import com.btewebquest.data.entity.UserRoleEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD Repository for UserRoleEntity
 *
 * @author sfradet
 * @version 1.0
 */
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Long> {
}
