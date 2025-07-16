package com.ayush.restapi_withdatabase.Service;

import com.ayush.restapi_withdatabase.Entity.JournalEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JournalEntryService {

    private final Map<Long , JournalEntity> journalEntity = new HashMap<>();

    public List<JournalEntity> getAllEntries(){
        return new ArrayList<>(journalEntity.values());
    }

    public JournalEntity addEntry(JournalEntity entity) {
        return journalEntity.put(entity.getId() , entity);
    }

    public JournalEntity updateEntryById(long id , JournalEntity entity) {
        return journalEntity.put(id , entity);
    }

    public JournalEntity deleteEntry(long id) {
        return journalEntity.remove(id);
    }

    public JournalEntity getEntryById(long id) {
        return journalEntity.get(id);
    }
}
