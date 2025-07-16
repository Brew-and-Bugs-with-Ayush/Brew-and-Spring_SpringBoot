package com.ayush.restapi_withdatabase.Repo;

import com.ayush.restapi_withdatabase.Entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepo extends MongoRepository<JournalEntity , ObjectId> {

}
