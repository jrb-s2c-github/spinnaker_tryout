package com.example.demo.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.AuthorRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class BookResolver implements GraphQLResolver<Book> {

    private AuthorRepository authorRepository = new AuthorRepositoryImpl();

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        return authorRepository.findOne(book.getAuthorId());
    }
}
