package com.Ayush.SpringSecurityExample.Controller;

import com.Ayush.SpringSecurityExample.Model.Users;
import com.Ayush.SpringSecurityExample.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        try {
            List<Users> allUsers = service.getAllUsers();

            return new ResponseEntity<>(allUsers , HttpStatus.OK);
        }
        catch(Exception e){
            log.error("Users list not found: {}" , e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users users){
        try {

            String login =service.verify(users);

            return new ResponseEntity<>(login , HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Failed to login try again..{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
