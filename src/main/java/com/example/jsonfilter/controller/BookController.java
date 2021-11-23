package com.example.jsonfilter.controller;

import com.example.jsonfilter.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@SuppressWarnings("unused")
public class BookController {

   @GetMapping("/books")
   public List<Book> books() {
      List<Book> books = new ArrayList<>();
      books.add(new Book("Don Quixote", "Miguel de Cervantes"));
      books.add(new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez"));
      return books;
   }
}
