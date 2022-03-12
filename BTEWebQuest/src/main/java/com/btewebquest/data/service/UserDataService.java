package com.btewebquest.data.service;

import com.btewebquest.data.entity.UserEntity;
import com.btewebquest.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataService implements DataAccessInterface<UserEntity>{

    @Autowired
    private UserRepository userRepository;

    public UserDataService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findAll() {

        //
        List<UserEntity> users = new ArrayList<UserEntity>();

        try
        {
            //
            Iterable<UserEntity> userIterable = userRepository.findAll();

            //
            userIterable.forEach(users::add);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //
        return users;
    }

    @Override
    public UserEntity findById(int id) {
        return null;
    }

    @Override
    public boolean create(UserEntity userEntity) {
        return false;
    }

    @Override
    public boolean update(UserEntity userEntity) {
        return false;
    }

    @Override
    public boolean delete(UserEntity userEntity) {
        return false;
    }

    public UserEntity findByUserName(String username) {
        // Attempt to find user by username
        return userRepository.findByUserName(username);
    }
}
