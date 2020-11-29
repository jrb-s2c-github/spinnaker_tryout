package com.example.demo.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class Query implements GraphQLQueryResolver {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> findAllBooks() throws IOException, ApiException {
        return bookRepository.findAll();
    }

    public Iterable<Author> findAllAuthors() throws IOException, ApiException {
        return authorRepository.findAll();
    }

    public long countBooks() throws IOException, ApiException {
        return bookRepository.count();
    }
    public long countAuthors() throws IOException, ApiException {
        return authorRepository.count();
    }

    public Book findBookById(String id) throws IOException, ApiException { return bookRepository.findBookById(id);}
    public Author findAuthorById(Long id) throws IOException, ApiException { return authorRepository.findAuthorById(id);}
}
