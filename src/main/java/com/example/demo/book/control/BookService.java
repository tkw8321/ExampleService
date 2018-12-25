package com.example.demo.book.control;

import java.util.List;
import static java.util.stream.Collectors.*;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.example.demo.book.control.dto.BookDto;
import com.example.demo.book.control.dto.BookDtoFactory;
import com.example.demo.book.control.repository.BookRepository;
import com.example.demo.book.entity.Book;

/**
 * @author lewmar
 */
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookDtoFactory bookDtoFactory;

    @Inject
    BookService(BookRepository bookRepository, BookDtoFactory bookDtoFactory) {
    	this.bookRepository = bookRepository;
    	this.bookDtoFactory = bookDtoFactory;
    }
	
	public List<BookDto> getAllBooks() {
		
      List<Book> books = bookRepository.findAllBooks();
      
      return books.stream()
    		  .map(book -> bookDtoFactory.buildBook(book, book.getAuthors()))
    		  .collect(toList());
	}
	
	public List<BookDto> getBooksByTitle(String titlePattern) {
		
		List<Book> books = bookRepository.getBooksByTitle( titlePattern);
		
		return books.stream()
				.map(book -> bookDtoFactory.buildBook(book, book.getAuthors()))
				.collect(toList());
	}
	
	public List<BookDto> getBooksByAuthor(String authorPattern) {
		
		List<Book> books = bookRepository.getBooksByAuthor(authorPattern);
		
		return books.stream()
				.map(book -> bookDtoFactory.buildBook(book, book.getAuthors()))
				.collect(toList());
	}
	
	public List<BookDto> getAvailableBooks() {
		
		List<Book> books = bookRepository.getAvailableBooks();
		
		return books.stream()
				.map(book -> bookDtoFactory.buildBook(book, book.getAuthors()))
				.collect(toList());
	}
	
	public List<BookDto> getBooksBorrowedLaterThan(Integer days) {
		
		List<Book> books = bookRepository.getBooksBorrowedLaterThan(days);
		
		return books.stream()
				.map(book -> bookDtoFactory.buildBook(book, book.getAuthors()))
				.collect(toList());
	}
}
