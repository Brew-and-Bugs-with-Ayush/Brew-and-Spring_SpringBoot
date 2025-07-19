package com.ayush.restapi_withdatabase.Service;

import com.ayush.restapi_withdatabase.Entity.User;
import com.ayush.restapi_withdatabase.Repo.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void saveEntry(User user){
        repository.save(user);
    }

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoll(Arrays.asList("USER"));
        repository.save(user);
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoll(Arrays.asList("USER" , "ADMIN"));
        repository.save(user);
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return repository.findById(id);
    }

    public void deleteById(ObjectId id){
        repository.deleteById(id);
    }

    public User findByUserName(String username) {
        return repository.findByUsername(username);
    }

}
