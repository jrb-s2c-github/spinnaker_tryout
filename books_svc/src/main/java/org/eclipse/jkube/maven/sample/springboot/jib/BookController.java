/**
 * Copyright (c) 2019 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at:
 * <p>
 * https://www.eclipse.org/legal/epl-2.0/
 * <p>
 * SPDX-License-Identifier: EPL-2.0
 * <p>
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.jkube.maven.sample.springboot.jib;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
public class BookController {

    public static final Long AUTHOR_1 = 1L;
    public static final Long AUTHOR_2 = 2L;
    public static final Long AUTHOR_3 = 3L;

    private static Map<String, Book> books;

    static {
        books = new HashMap<>(3);

//        String title, String isbn, int pageCount, Author author
        books.put("book-1", new Book("Harry Potter and the Philosopher's Stone",
                "ae345",
                223,
                AUTHOR_1)
        );
        books.put("book-2", new Book("Moby Dick",
                "be345",
                635,
                AUTHOR_2)
        );
        books.put("book-3", new Book("Interview with the vampire",
                "ce345",
                371,
                AUTHOR_3)
        );
    }

    @RequestMapping("/bookAmount")
    public Integer bookAmount() {
        return books.values().size();
    }

    @RequestMapping("/getBooks")
    public Collection<Book> getBooks() {
        return books.values();
    }


    @RequestMapping("/getBookById")
    public Book getBookById(@RequestParam String id) {
        return books.get(id);
    }
}
