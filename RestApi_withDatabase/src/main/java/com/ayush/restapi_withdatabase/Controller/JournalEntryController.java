package com.ayush.restapi_withdatabase.Controller;

import com.ayush.restapi_withdatabase.Entity.JournalEntity;
import com.ayush.restapi_withdatabase.Entity.User;
import com.ayush.restapi_withdatabase.Service.JournalEntryService;
import com.ayush.restapi_withdatabase.Service.JournalEntryServiceV2;
import com.ayush.restapi_withdatabase.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private final JournalEntryServiceV2 service2;
    private final UserService userService;

    @Autowired
    public JournalEntryController(JournalEntryServiceV2 service2 , UserService userService) {
        this.service2 = service2;
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllEntriesOfUser(@PathVariable String username) {
        User user = userService.findByUserName(username);

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        List<JournalEntity> all = user.getEntities();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }


    @GetMapping("/getEntry/{id}")
    public ResponseEntity<JournalEntity> getEntryById(@PathVariable ObjectId id){

        Optional<JournalEntity> entity=  service2.getEntryById(id);

        return entity.map(journalEntity -> new ResponseEntity<>(entity.get(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<JournalEntity> addEntry(@RequestBody  JournalEntity entity , @PathVariable String username){

        try {
            service2.addEntry(entity , username);
            return new ResponseEntity<>(entity , HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{username}/{id}")
    public ResponseEntity<String> updateEntryById(
            @PathVariable ObjectId id ,
            @PathVariable String username ,
            @RequestBody JournalEntity newEntity
    ){

        JournalEntity old = service2.getEntryById(id).orElse(null);

        if(old!= null) {
            old.setTitle(newEntity.getTitle() != null && !newEntity.getTitle().isEmpty() ? newEntity.getTitle() : old.getTitle());
            old.setContent(newEntity.getContent() != null && !newEntity.getContent().isEmpty() ? newEntity.getContent() : old.getContent());
            service2.updateEntryById(old);
            return new ResponseEntity<>("Updated" , HttpStatus.OK);
        }

        return new ResponseEntity<>("Failed to Update" , HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable ObjectId id ,  @PathVariable String username){
       Optional<JournalEntity> entity =  service2.getEntryById(id);

       if (entity.isPresent()) {
           service2.deleteEntry(id , username);
           return new ResponseEntity<>("Entry deleted" , HttpStatus.OK);
       }
       return new ResponseEntity<>("Entry not Found" , HttpStatus.NOT_FOUND);
    }
}
