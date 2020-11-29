package com.example.demo.repository;

import com.example.demo.model.Book;
import io.kubernetes.client.openapi.ApiException;

import java.io.IOException;

public interface BookRepository /*extends CrudRepository<Book, Long>*/ {
    long count() throws IOException, ApiException;

    Iterable<Book> findAll() throws IOException, ApiException;

    Book findBookById(String id) throws IOException, ApiException;
}
