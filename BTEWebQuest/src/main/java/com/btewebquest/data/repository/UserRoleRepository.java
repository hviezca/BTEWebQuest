package com.btewebquest.data.repository;

import com.btewebquest.data.entity.UserRoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Long> {
}
