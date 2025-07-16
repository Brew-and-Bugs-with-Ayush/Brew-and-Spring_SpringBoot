package com.ayush.restapi_withdatabase.Controller;

import com.ayush.restapi_withdatabase.Entity.JournalEntity;
import com.ayush.restapi_withdatabase.Service.JournalEntryService;
import com.ayush.restapi_withdatabase.Service.JournalEntryServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public boolean addEntry(@RequestBody  JournalEntity entity){
        JournalEntity entity1 = service2.addEntry(entity);
        return true;
    }

    @PutMapping("/update/{id}")
    public boolean updateEntryById(@PathVariable long id , @RequestBody JournalEntity entity){
        JournalEntity entity1 =  service2.updateEntryById(id , entity);
        return true;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteEntry(@PathVariable long id){
        return service2.deleteEntry(id);
    }

    @GetMapping("/getEntry/{id}")
    public JournalEntity getEntryById(@PathVariable long id){
        return service2.getEntryById(id);
    }
}
