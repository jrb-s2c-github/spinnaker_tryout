package com.example.demo.repository;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Component
public class BookRepositoryImpl implements BookRepository {

//    @Autowired
    RestTemplate restTemplate;

//    @Autowired
    ServiceRegistry serviceRegistry;

//    private final String restRoot;

    BookRepositoryImpl() {
        serviceRegistry = new ServiceRegistry();
//        restRoot = serviceRegistry.getRestRoot(Author.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        restTemplate = builder.build();
        return restTemplate;
    }

    @Override
    public long count() throws IOException, ApiException {
        String restRoot = null;

            restRoot = serviceRegistry.getRestRoot(Book.class);

        return restTemplate.getForObject(
                restRoot+"bookAmount", Long.class);
    }

    @Override
    public Iterable<Book> findAll() throws IOException, ApiException {
        String restRoot = serviceRegistry.getRestRoot(Book.class);
        Book[] books = restTemplate.getForObject(restRoot + "/getBooks", Book[].class);
        return Arrays.asList(books);
    }

    @Override
    public Book findBookById(String id) throws IOException, ApiException {
        String restRoot = serviceRegistry.getRestRoot(Book.class);
        return restTemplate.getForObject(
                restRoot+"getBookById?id="+id, Book.class);
    }
}
