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
public class AuthorController {

    public static final Long AUTHOR_1 = 1L;
    public static final Long AUTHOR_2 = 2L;
    public static final Long AUTHOR_3 = 3L;
    public static final Long AUTHOR_4 = 4L;

    private static Map<Long, Author> authors;

    static {
        authors = new HashMap<>(3);
        authors.put(AUTHOR_1, new Author(AUTHOR_1, "Joanne", "Rowling"));
        authors.put(AUTHOR_2, new Author(AUTHOR_1, "Herman", "Melville"));
        authors.put(AUTHOR_3, new Author(AUTHOR_1, "Anne", "Rice"));
        authors.put(AUTHOR_4, new Author(AUTHOR_4, "Walter", "Scott"));
    }

    @RequestMapping("/authorAmount")
    public Integer authorAmount() {
        return authors.values().size();
    }

    @RequestMapping("/getAuthors")
    public Collection<Author> getAuthors() {
        return authors.values();
    }


    @RequestMapping("/getAuthorById")
    public Author getBookById(@RequestParam Long id) {
        return authors.get(id);
    }
}
