package com.example.demo.book.control.dto;

import java.util.List;

import lombok.Builder;
import lombok.Value;

/**
 * @author lewmar
 */
@Value
@Builder
public class BookDto {
	
	private final long id;
	
	private final String title;
	
	private final String isbn;
	
	private final List<AuthorDto> authors;
}
