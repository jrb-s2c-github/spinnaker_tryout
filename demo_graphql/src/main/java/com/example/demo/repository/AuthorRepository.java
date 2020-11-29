package com.example.demo.repository;

import com.example.demo.model.Author;
import com.example.demo.model.Book;

public interface AuthorRepository /*extends CrudRepository<Author, Long>*/ {
    Author findOne(Long id);

    Iterable<Author> findAll();

    Author findAuthorById(Long id);

    long count();
}
