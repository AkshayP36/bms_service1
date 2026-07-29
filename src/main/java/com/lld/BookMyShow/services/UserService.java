package com.lld.BookMyShow.services;

import com.lld.BookMyShow.models.User;
import com.lld.BookMyShow.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User signup(String name, String email, String password){
        //check if user already exists, or same email exists
        Optional<User> userCheck = userRepository.findByEmail(email);
        if(userCheck.isPresent()){
            return userCheck.get();
        }
        //else create a new user
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        User savedUser = userRepository.save(newUser);
        return savedUser;
    }
}
