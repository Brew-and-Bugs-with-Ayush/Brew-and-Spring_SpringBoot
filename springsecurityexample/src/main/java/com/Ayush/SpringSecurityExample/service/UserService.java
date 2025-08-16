package com.Ayush.SpringSecurityExample.service;

import com.Ayush.SpringSecurityExample.Model.Users;
import com.Ayush.SpringSecurityExample.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserService {

    private final UserRepo repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserRepo repo, PasswordEncoder encoder, AuthenticationManager manager, JwtService jwtService) {
        this.repo = repo;
        this.encoder = encoder;
        this.manager = manager;
        this.jwtService = jwtService;
    }

    public List<Users> getAllUsers(){
        try {
            return repo.findAll();
        }
        catch (Exception e) {
            log.error("Users list not found");
            throw new NoSuchElementException("Users not found");
        }
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

    public String verify(Users users) {

        try {
            Authentication authentication =
                    manager.authenticate(new UsernamePasswordAuthenticationToken(
                            users.getUsername(), users.getPassword()));

            if (authentication.isAuthenticated())
               return jwtService.generateToken(users.getUsername());

            else
                return "fail";

        }
        catch (Exception e){
            log.error("Cannot verify the user: {}" , e.getMessage());
            throw new IllegalArgumentException("Verification failed");
        }
    }
}
