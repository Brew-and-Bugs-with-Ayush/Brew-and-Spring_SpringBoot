package com.ayush.RestApiWithDatabase.Service;

import com.ayush.RestApiWithDatabase.Entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JournalEntryService {

    private final Map<ObjectId, JournalEntity> journalEntity = new HashMap<>();

    public List<JournalEntity> getAllEntries(){
        return new ArrayList<>(journalEntity.values());
    }

    public JournalEntity addEntry(JournalEntity entity) {
        return journalEntity.put(entity.getId() , entity);
    }

    public JournalEntity updateEntryById(ObjectId id , JournalEntity entity) {
        return journalEntity.put(id , entity);
    }

    public JournalEntity deleteEntry(ObjectId id) {
        return journalEntity.remove(id);
    }

    public JournalEntity getEntryById(ObjectId id) {
        return journalEntity.get(id);
    }
}
