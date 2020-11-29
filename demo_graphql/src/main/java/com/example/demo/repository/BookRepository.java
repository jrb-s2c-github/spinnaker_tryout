package com.example.demo.repository;

import com.example.demo.model.Book;

public interface BookRepository /*extends CrudRepository<Book, Long>*/ {
    long count();

    Iterable<Book> findAll();

    Book findBookById(String id);
}
