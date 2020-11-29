package com.example.demo.repository;

import com.example.demo.model.Author;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Component
public class AuthorRepositoryImpl  implements AuthorRepository {


//
//    protected static Map<Long, Author> authors;
//
//    AuthorRepositoryImpl() {
//        authors = new HashMap<>(3);
//        authors.put(AUTHOR_1, new Author(AUTHOR_1, "Joanne", "Rowling"));
//        authors.put(AUTHOR_2, new Author(AUTHOR_1, "Herman", "Melville"));
//        authors.put(AUTHOR_3, new Author(AUTHOR_1, "Anne", "Rice"));
//    }
//
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        restTemplate = builder.build();
//        return restTemplate;
//    }

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
    ServiceRegistry serviceRegistry;

//    private final String restRoot;

    AuthorRepositoryImpl() {
        serviceRegistry = new ServiceRegistry();

    }

    @Override
    public Author findOne(Long id) throws IOException, ApiException {
        return findAuthorById(id);
    }

    @Override
    public Iterable<Author> findAll() throws IOException, ApiException {
        String restRoot = serviceRegistry.getRestRoot(Author.class);
        Author[] authors = restTemplate.getForObject(restRoot + "/getAuthors", Author[].class);
        return Arrays.asList(authors);
    }

    @Override
    public Author findAuthorById(Long id) throws IOException, ApiException {
        String restRoot = serviceRegistry.getRestRoot(Author.class);
        return restTemplate.getForObject(
                restRoot+"getAuthorById?id="+id, Author.class);
    }

    @Override
    public long count() throws IOException, ApiException {
        String restRoot = null;

            restRoot = serviceRegistry.getRestRoot(Author.class);


        return restTemplate.getForObject(
                restRoot+"authorAmount", Long.class);
    }
}
