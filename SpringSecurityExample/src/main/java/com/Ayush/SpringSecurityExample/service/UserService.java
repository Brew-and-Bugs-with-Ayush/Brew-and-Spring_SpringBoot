package com.Ayush.SpringSecurityExample.service;

import com.Ayush.SpringSecurityExample.Model.Users;
import com.Ayush.SpringSecurityExample.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {

    private final UserRepo repo;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepo repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Transactional
    public Users register(Users users){
        try {
            users.setPassword(encoder.encode(users.getPassword()));

           return repo.save(users);

        }
        catch (Exception e){
            log.error("User not created: {}", e.getMessage() ,e);
            throw new IllegalArgumentException("User not created sorry");
        }
    }
}
