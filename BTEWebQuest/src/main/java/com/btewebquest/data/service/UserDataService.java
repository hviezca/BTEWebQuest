package com.btewebquest.data.service;

import com.btewebquest.data.entity.UserEntity;
import com.btewebquest.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        // Optional Object to hold UserEntity returned from database by id
        Optional<UserEntity> user = userRepository.findById((long) id);

        // If Optional Object not null, return UserEntity, else throw NoSuchElement Exception
        return user.get();
    }


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

    public UserEntity findByUserName(String username) {
        // Attempt to find user by username
        return userRepository.findByUserName(username);
    }
}
