package com.btewebquest.data.service;

import com.btewebquest.data.entity.RoleEntity;
import com.btewebquest.data.entity.UserEntity;
import com.btewebquest.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Data service for actions related to UserEntity
 *
 */
@Service
public class UserDataService implements DataAccessInterface<UserEntity>{

    @Autowired
    private UserRepository userRepository;

    public UserDataService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    /**
     * Get all Users in database
     *
     * @return List of UserEntity
     */
    @Override
    public List<UserEntity> findAll() {

        // Empty List of UserEntity
        List<UserEntity> users = new ArrayList<UserEntity>();

        try
        {
            // Iterable result set from database
            Iterable<UserEntity> userIterable = userRepository.findAll();

            // Step through Iterable set and add to UserEntity List
            userIterable.forEach(users::add);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Return List of UserEntity
        return users;
    }

    /**
     * Find User by ID
     *
     * @param id id number of Object
     * @return UserEntity
     */
    @Override
    public UserEntity findById(int id) {

        // Optional Object to hold UserEntity returned from database by id
        Optional<UserEntity> user = userRepository.findById((long) id);

        // If Optional Object not null, return UserEntity, else throw NoSuchElement Exception
        return user.get();
    }

    /**
     * Create User in database
     *
     * @param userEntity UserEntity to be created in database
     * @return boolean indicating success
     */
    @Override
    public boolean create(UserEntity userEntity)
    {
        try
        {
            // Attempt to save new UserEntity to database
            userRepository.save(userEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Update User in database
     *
     * @param userEntity UserEntity to be updated
     * @return boolean indicating success
     */
    @Override
    public boolean update(UserEntity userEntity)
    {
        try
        {
            // Attempt to update UserModel in database
            userRepository.save(userEntity);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Delete User in database
     *
     * @param userEntity UserEntity to be deleted in database
     * @return boolean indicating success
     */
    @Override
    public boolean delete(UserEntity userEntity) {

        try
        {
            // Attempt to delete UserModel in database
            userRepository.delete(userEntity);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Find User Roles by ID
     *
     * @param user_id User ID
     * @return Set of RoleEntity
     */
    public Set<RoleEntity> findUserRoles(int user_id)
    {
        return userRepository.findRoles((long) user_id);
    }

    /**
     * Find User by username
     *
     * @param username User's username
     * @return UserEntity
     */
    public UserEntity findByUserName(String username) {
        // Attempt to find user by username
        return userRepository.findByUserName(username);
    }
}
