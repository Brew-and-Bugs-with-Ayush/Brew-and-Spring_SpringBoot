package com.ayush.RestApiWithDatabase.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "journal_entries")
public class JournalEntity {

    @Id
    private ObjectId id;

    private String title;
    private String content;

    private LocalDateTime date;
}
