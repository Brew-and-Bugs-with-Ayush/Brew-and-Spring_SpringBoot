package com.ayush.restapi_withdatabase.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
//import java.util.Date;

@Data
@Document(collection = "journal_entries")
public class JournalEntity {

    @Id
    private ObjectId id;

    private String title;
    private String content;

    private LocalDate date;
}
