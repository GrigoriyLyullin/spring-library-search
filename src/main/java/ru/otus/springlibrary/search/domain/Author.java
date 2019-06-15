package ru.otus.springlibrary.search.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "authorWithFullNamesView")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    private ObjectId id;

    @Field(value = "full_name")
    private String fullName;

    public Author(String fullName) {
        this.fullName = fullName;
    }
}

