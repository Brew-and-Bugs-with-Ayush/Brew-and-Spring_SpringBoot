package com.ayush.restapi_withdatabase.Controller;

import com.ayush.restapi_withdatabase.Entity.JournalEntity;
import com.ayush.restapi_withdatabase.Service.JournalEntryService;
import com.ayush.restapi_withdatabase.Service.JournalEntryServiceV2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private final JournalEntryServiceV2 service2;

    @Autowired
    public JournalEntryController(JournalEntryServiceV2 service2) {
        this.service2 = service2;
    }

    @GetMapping("retrieve")
    public List<JournalEntity> getAll(){
        return service2.getAllEntries();
    }

    @PostMapping("/add")
    public JournalEntity addEntry(@RequestBody  JournalEntity entity){
        entity.setDate(LocalDate.now());
        JournalEntity entity1 = service2.addEntry(entity);
        return entity1;
    }

    @PutMapping("/update/{id}")
    public JournalEntity updateEntryById(@PathVariable ObjectId id , @RequestBody JournalEntity newEntity){

        JournalEntity old = service2.getEntryById(id).orElse(null);

        if(old!= null) {
            old.setTitle(newEntity.getTitle() != null && !newEntity.getTitle().isEmpty() ? newEntity.getTitle() : old.getTitle());
            old.setContent(newEntity.getContent() != null && !newEntity.getContent().isEmpty() ? newEntity.getContent() : old.getContent());
        }

        service2.updateEntryById(old);
        return old;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteEntry(@PathVariable ObjectId id){
        return service2.deleteEntry(id);
    }

    @GetMapping("/getEntry/{id}")
    public JournalEntity getEntryById(@PathVariable ObjectId id){
        return service2.getEntryById(id).orElse(null);
    }
}
