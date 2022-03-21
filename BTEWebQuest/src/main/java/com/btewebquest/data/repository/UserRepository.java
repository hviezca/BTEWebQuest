package com.btewebquest.data.repository;

import com.btewebquest.data.entity.RoleEntity;
import com.btewebquest.data.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    // Method for finding a user by their username
    UserEntity findByUserName(String userName);

    @Query("SELECT * FROM roles INNER JOIN users_roles ON roles.ROLE_ID = users_roles.ROLE_ID WHERE users_roles.USER_ID = :id")
    Set<RoleEntity> findRoles(@Param("id") Long id);
}
