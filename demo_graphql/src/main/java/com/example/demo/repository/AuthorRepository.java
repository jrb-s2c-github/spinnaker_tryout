package com.example.demo.repository;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import io.kubernetes.client.openapi.ApiException;

import java.io.IOException;

public interface AuthorRepository /*extends CrudRepository<Author, Long>*/ {
    Author findOne(Long id) throws IOException, ApiException;

    Iterable<Author> findAll() throws IOException, ApiException;

    Author findAuthorById(Long id) throws IOException, ApiException;

    long count() throws IOException, ApiException;
}
