package com.btewebquest.data.service;

import com.btewebquest.data.entity.UserRoleEntity;
import com.btewebquest.data.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Data Service for User Roles
 *
 * @author sfradet
 * @version 1.0
 */
@Service
public class UserRoleDataService implements DataAccessInterface<UserRoleEntity>{

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRoleDataService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRoleEntity> findAll() {
        return null;
    }

    @Override
    public UserRoleEntity findById(int id) {
        return null;
    }

    /**
     * Create new User Role
     *
     * @param userRoleEntity UserRoleEntity
     * @return boolean indicating success
     */
    @Override
    public boolean create(UserRoleEntity userRoleEntity) {

        try
        {
            // Attempt to save new UserRoleEntity to database
            userRoleRepository.save(userRoleEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(UserRoleEntity userRoleEntity) {
        return false;
    }

    @Override
    public boolean delete(UserRoleEntity userRoleEntity) {
        return false;
    }
}
