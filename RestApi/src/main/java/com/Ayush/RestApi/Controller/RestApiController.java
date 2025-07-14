package com.Ayush.RestApi.Controller;

import com.Ayush.RestApi.Entity.RestApiEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Rest-API")
public class RestApiController {

    private Map<Long , RestApiEntity> restApiEntries = new HashMap<>();

    { // initialisation block

        restApiEntries.put(101L, new RestApiEntity(101L, "Ayush", "ayush@gmail.com"));
        restApiEntries.put(102L, new RestApiEntity(102L, "Rajesh", "rajesh@gmail.com"));
        restApiEntries.put(103L, new RestApiEntity(103L, "Nisha", "nisha@gmail.com"));
    }

    @GetMapping  // retrieving the data
    public List<RestApiEntity> getAll(){
        return new ArrayList<>(restApiEntries.values());
    }

    @PostMapping // creating the value
    public boolean createEntry(@RequestBody RestApiEntity myEntry){
        restApiEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("id/{myId}")  // retrieving the value
    public RestApiEntity getEntryByID(@PathVariable Long myId){
        return restApiEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")  // deleting the value
    public RestApiEntity deleteEntryByID(@PathVariable Long myId){
        return restApiEntries.remove(myId);
    }

    @PutMapping("id/{id}") // updating the value
    public RestApiEntity updateEntryByID(@PathVariable Long id , @RequestBody RestApiEntity myEntry){
        return restApiEntries.put(id, myEntry);
    }
}
