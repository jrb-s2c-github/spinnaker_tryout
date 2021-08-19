package com.example.demo;

import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.resolver.BookResolver;
import com.example.demo.resolver.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// from https://www.pluralsight.com/guides/building-a-graphql-server-with-spring-boot
@SpringBootApplication
public class DemoGraphQlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoGraphQlApplication.class, args);
    }
//
    @Bean
    public BookResolver authorResolver(AuthorRepository authorRepository) {
        return new BookResolver(authorRepository);
    }

    @Bean
    public Query query(AuthorRepository authorRepository, BookRepository bookRepository) {
        return new Query(authorRepository, bookRepository);
    }

//    @Bean
//    public Mutation mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
//        return new Mutation(authorRepository, bookRepository);
//    }
}
