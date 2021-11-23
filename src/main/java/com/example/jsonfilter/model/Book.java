package com.example.jsonfilter.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonFilter("dynamicFilter")
public class Book {
   String name;
   String author;
}
