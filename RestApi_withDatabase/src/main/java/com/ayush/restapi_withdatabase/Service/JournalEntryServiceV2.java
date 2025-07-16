package com.ayush.restapi_withdatabase.Service;

import com.ayush.restapi_withdatabase.Entity.JournalEntity;
import com.ayush.restapi_withdatabase.Repo.JournalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JournalEntryServiceV2 {

   private final JournalRepo repo;

   @Autowired
    public JournalEntryServiceV2(JournalRepo repo) {
        this.repo = repo;
    }

    public List<JournalEntity> getAllEntries(){
        return repo.findAll();
    }

    public JournalEntity addEntry(JournalEntity entity) {
        return repo.save(entity);
    }

    public JournalEntity updateEntryById(long id , JournalEntity entity) {

       if (entity.getId() == id) return repo.save(entity);

       else return new JournalEntity();
    }

    public boolean deleteEntry(long id) {

       if (repo.existsById(id)){
           repo.deleteById(id);
           return true;
       }
       else return false;
    }

    public JournalEntity getEntryById(long id) {
        return repo.findById(id).orElse(new JournalEntity());
    }
}
