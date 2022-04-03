package com.btewebquest.data.repository;

import com.btewebquest.data.entity.RoleEntity;
import com.btewebquest.data.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * CRUD Repository for UserEntity
 *
 * @author sfradet
 * @version 1.0
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    /**
     * Method for finding a user by their username
     *
     * @param userName Username
     * @return UserEntity
     */
    UserEntity findByUserName(String userName);

    // Custom Query for getting User Roles from database
    @Query("SELECT * FROM ROLES INNER JOIN USERS_ROLES ON ROLES.ROLE_ID = USERS_ROLES.ROLE_ID WHERE USERS_ROLES.USER_ID = :id")
    Set<RoleEntity> findRoles(@Param("id") Long id);

}
