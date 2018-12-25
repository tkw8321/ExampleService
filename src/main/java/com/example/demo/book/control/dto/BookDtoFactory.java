package com.example.demo.book.control.dto;

import java.util.Collection;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Component;
import com.example.demo.book.entity.Author;
import com.example.demo.book.entity.Book;

@Component
public class BookDtoFactory {

    public BookDto buildBook(Book b, Collection<Author> authors) {
    	return BookDto.builder()
    			.id(b.getId())
    			.title(b.getTitle())
    			.isbn(b.getIsbn())
    			.authors(buildAuthorList(authors))
    			.build();
    }

    private static List<AuthorDto> buildAuthorList(Collection<Author> authors) {
    	return authors.stream()
                .map(a -> AuthorDto.builder()
                		.id(a.getId())
                		.name(a.getName())
                		.surname(a.getSurname())
                		.books(null)
                		.build())
                .collect(toList());
    }
}
