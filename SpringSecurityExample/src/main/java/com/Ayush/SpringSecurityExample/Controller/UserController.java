package com.Ayush.SpringSecurityExample.Controller;

import com.Ayush.SpringSecurityExample.Model.Users;
import com.Ayush.SpringSecurityExample.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users users){
        try{
            Users register = service.register(users);

            return new ResponseEntity<>(register, HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error("User not created sorry");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
