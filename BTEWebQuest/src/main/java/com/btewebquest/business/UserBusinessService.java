package com.btewebquest.business;

import com.btewebquest.data.entity.RoleEntity;
import com.btewebquest.data.entity.UserEntity;
import com.btewebquest.data.entity.UserRoleEntity;
import com.btewebquest.data.service.UserDataService;
import com.btewebquest.data.service.UserRoleDataService;
import com.btewebquest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Business service for all actions related to UserModels
 *
 * @author sfradet
 * @version 1.0
 */
@Service
public class UserBusinessService implements UserDetailsService {

    @Autowired
    private UserDataService service;

    @Autowired
    private UserRoleDataService roleService;

    /**
     * Return list of Users from database
     *      *
     * @return List of Users
     */
    public List<UserModel> getUsers()
    {
        // Get list of UserEntity from database
        List<UserEntity> userEntity = service.findAll();

        // Empty ArrayList to hold results
        List<UserModel> users = new ArrayList<UserModel>();

        // Step through Entity list and add each result to UserList
        for(UserEntity entity : userEntity)
        {
            users.add(new UserModel(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getUserName(), entity.getPassword()));
        }

        // Return list of UserModels
        return users;
    }

    /**
     * Add User to database
     *
     * @param user UserModel to add to database
     * @return boolean indicating success
     */
    public boolean addUser(UserModel user)
    {
        UserEntity userEntity = new UserEntity(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword());

        return service.create(userEntity);
    }

    /**
     * Get User from database by ID
     *
     * @param id Id of desired User
     * @return UserModel
     */
    public UserModel getUserById(int id)
    {
        UserEntity user = service.findById(id);

        return new UserModel(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword());
    }

    /**
     * Delete User from database
     *
     * @param userModel UserModel to be deleted
     * @return boolean indicating success
     */
    public boolean deleteUser(UserModel userModel)
    {
        UserEntity user = new UserEntity(userModel.getId(), userModel.getFirstName(), userModel.getLastName(), userModel.getUserName(), userModel.getPassword());

        return service.delete(user);
    }

    /**
     * Update User in database
     *
     * @param user UserModel to be udpated
     * @return boolean indicating success
     */
    public boolean updateUser(UserModel user)
    {
        UserEntity userEntity = new UserEntity(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword());

        return service.create(userEntity);
    }

    /**
     * Find User by username parameter
     *
     * @param username String of username
     * @return UserModel
     */
    public UserModel findUserByUsername(String username)
    {
        // Call service and return user by username
        UserEntity user =  service.findByUserName(username);

        return new UserModel(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword());
    }

    /**
     * Get UserDetails for SpringSecurity
     *
     * @param username Username of user logging in
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Call service and return user by username
        UserEntity user =  service.findByUserName(username);

        // Retrieve UserDetails
        Set<RoleEntity> roleEntitySet = service.findUserRoles(user.getId());

        // Verify if User is null. If not null load Authorities
        if(user != null)
        {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            for(RoleEntity entity : roleEntitySet)
            {
                authorities.add(new SimpleGrantedAuthority(entity.getName()));
            }

            return new User(user.getUserName(), user.getPassword(), authorities);
        }
        else
        {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    /**
     * Add User Roles to database
     *
     * @param role_id ID of role to be loaded to User
     * @param user_id ID of User to receive role
     * @return boolean indicating success
     */
    public boolean addUserRoles(int role_id, int user_id)
    {
        UserRoleEntity user_role = new UserRoleEntity(0, role_id, user_id);

        return roleService.create(user_role);
    }
}
