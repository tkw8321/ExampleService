package com.example.demo.book.control.dto;

import java.util.List;

import lombok.Builder;
import lombok.Value;

/**
 * @author lewmar
 */
@Value
@Builder
public class AuthorDto {

	private final long id;
	
	private final String name;
	
	private final String surname;
	
	private final List<BookDto> books;
}
