package com.ayush.restapi_withdatabase.Service;

import com.ayush.restapi_withdatabase.Entity.JournalEntity;
import com.ayush.restapi_withdatabase.Repo.JournalRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
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

    public void addEntry(JournalEntity entity) {
       try {
           entity.setDate(LocalDate.from(LocalDateTime.now()));
           repo.save(entity);
       } catch (Exception e) {
           log.error("Exception" , e);
       }

    }

    public void updateEntryById(JournalEntity entity) {
       try {
           entity.setDate(LocalDate.from(LocalDateTime.now()));
           repo.save(entity);
       } catch (Exception e) {
           log.error("Exception" , e);
       }
    }

    public void deleteEntry(ObjectId id) {
        repo.deleteById(id);
    }
}
