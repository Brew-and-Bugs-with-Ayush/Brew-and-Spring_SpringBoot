package com.ayush.RestApiWithDatabase.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SentimentData {
    private String email;
    private String sentiment;
}
