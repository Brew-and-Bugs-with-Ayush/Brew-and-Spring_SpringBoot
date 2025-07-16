package com.ayush.restapi_withdatabase.Controller;

import com.ayush.restapi_withdatabase.Entity.JournalEntity;
import com.ayush.restapi_withdatabase.Service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private final JournalEntryService service;

    @Autowired
    public JournalEntryController(JournalEntryService service) {
        this.service = service;
    }

    @GetMapping("retrieve")
    public List<JournalEntity> getAll(){
        return service.getAllEntries();
    }

    @PostMapping("/add")
    public boolean addEntry(@RequestBody  JournalEntity entity){
        JournalEntity entity1 = service.addEntry(entity);
        return true;
    }

    @PutMapping("/update/{id}")
    public boolean updateEntryById(@PathVariable long id , @RequestBody JournalEntity entity){
        JournalEntity entity1 =  service.updateEntryById(id , entity);
        return true;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteEntry(@PathVariable long id){
        JournalEntity entity = service.deleteEntry(id);
        return true;
    }

    @GetMapping("/getEntry/{id}")
    public JournalEntity getEntryById(@PathVariable long id){
        return service.getEntryById(id);
    }
}
