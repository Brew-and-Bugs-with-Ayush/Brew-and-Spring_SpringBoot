package com.ayush.restapi_withdatabase.Service;

import com.ayush.restapi_withdatabase.Entity.JournalEntity;
import com.ayush.restapi_withdatabase.Repo.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Optional<JournalEntity> getEntryById(ObjectId id) {
        return repo.findById(id);
    }

    public JournalEntity addEntry(JournalEntity entity) {
        return repo.save(entity);
    }

    public void updateEntryById(JournalEntity entity) {
        repo.save(entity);
    }

    public void deleteEntry(ObjectId id) {
        repo.deleteById(id);
    }
}
