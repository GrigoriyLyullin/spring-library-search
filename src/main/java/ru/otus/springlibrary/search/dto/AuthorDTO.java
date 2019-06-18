package ru.otus.springlibrary.search.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorDTO {

    private String id;

    private String fullName;
}

