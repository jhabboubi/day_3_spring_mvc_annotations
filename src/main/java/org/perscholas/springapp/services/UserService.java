package org.perscholas.springapp.services;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.springapp.models.User;
import org.perscholas.springapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j

public class UserService {

    private UserRepo userRepo;
    private User user;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Transactional
    public void saveUser(User user){

        // i want to make sure that the user is not already in the database

        // if the email has 3 letter or more
        if(user.getName().length() <3){
            log.warn("User name has less than 3 letters");
            return;
        }
        // save to database
        userRepo.save(user);

    }

    public List<User> getByEmail(String email){


       return userRepo.getByEmail(email);

    }

}
