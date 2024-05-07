package com.KeyCloakTest.KeyCloakTest.Services;

import com.KeyCloakTest.KeyCloakTest.Models.Userdet;
import com.KeyCloakTest.KeyCloakTest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<Userdet> findAllUsers() {
        return userRepository.findAll();
    }

    public Userdet findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Userdet saveUser(Userdet user) {
        return userRepository.save(user);
    }
}
