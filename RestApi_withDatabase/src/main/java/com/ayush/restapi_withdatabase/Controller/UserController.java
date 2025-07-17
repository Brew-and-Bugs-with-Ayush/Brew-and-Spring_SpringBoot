package com.ayush.restapi_withdatabase.Controller;

import com.ayush.restapi_withdatabase.Entity.User;
import com.ayush.restapi_withdatabase.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUsers(@RequestBody User user){
        try {
            service.saveEntry(user);
            return new ResponseEntity<>(user , HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<String> updateUsers(@RequestBody User user  , @PathVariable String username){
        try{
            User user1 = service.findByUserName(username);
            if (user1 == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            user1.setUsername(user1.getUsername());
            user1.setPassword(user1.getPassword());

            service.saveEntry(user1);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("User not found" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
