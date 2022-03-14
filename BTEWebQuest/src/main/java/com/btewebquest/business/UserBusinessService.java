package com.btewebquest.business;

import com.btewebquest.data.entity.RoleEntity;
import com.btewebquest.data.entity.UserEntity;
import com.btewebquest.data.repository.UserRepository;
import com.btewebquest.data.service.UserDataService;
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

@Service
public class UserBusinessService implements UserDetailsService {

    @Autowired
    private UserDataService service;

    @Autowired
    private UserRepository service2;

    public List<UserModel> getUsers()
    {
        //
        List<UserEntity> userEntity = service.findAll();

        // Empty List of CoffeeModels to hold results
        List<UserModel> users = new ArrayList<UserModel>();

        // For each CoffeeEntity in coffeeEntity List, add CoffeeModel to coffeeDomain List
        for(UserEntity entity : userEntity)
        {
            users.add(new UserModel(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getUserName(), entity.getPassword()));
        }

        // Return list of CoffeeModels
        return users;
    }

    public boolean addUser(UserModel user)
    {
        UserEntity userEntity = new UserEntity(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword());

        return service.create(userEntity);
    }

    public UserModel getUserById(int id)
    {
        UserEntity user = service.findById(id);

        return new UserModel(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword());
    }

    public boolean deleteUser(UserModel userModel)
    {
        UserEntity user = new UserEntity(userModel.getId(), userModel.getFirstName(), userModel.getLastName(), userModel.getUserName(), userModel.getPassword());

        return service.delete(user);
    }

    public boolean updateUser(UserModel user)
    {
        UserEntity userEntity = new UserEntity(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword());

        return service.create(userEntity);
    }

    public UserModel findUserByUsername(String username)
    {
        // Call service and return user by username
        UserEntity user =  service.findByUserName(username);

        return new UserModel(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Call service and return user by username
        UserEntity user =  service.findByUserName(username);

        Set<RoleEntity> roleEntitySet = service2.findRoles((long) user.getId());

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
}
