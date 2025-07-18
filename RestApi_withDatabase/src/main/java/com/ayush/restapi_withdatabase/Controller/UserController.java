package com.ayush.restapi_withdatabase.Controller;

import com.ayush.restapi_withdatabase.Entity.User;
import com.ayush.restapi_withdatabase.Repo.UserRepository;
import com.ayush.restapi_withdatabase.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final UserRepository userRepository;


    @Autowired
    public UserController(UserService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUsers(@RequestBody User user){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

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

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
