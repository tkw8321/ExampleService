package com.example.demo.book.boundary;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import java.util.List;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.example.demo.book.control.BookService;
import com.example.demo.book.control.dto.BookDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/books")
@Validated
public class BookController {

    private final BookService bookService;

    @Inject
    BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    /**
     * Returns all available books.
     * <p>
     * The endpoint returns a list of {@link BookDto}s for all available books.
     * The order of entries in the result list in not guaranteed.
     * @return list of books
     */
    @RequestMapping(value="/getAllBooks", method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public List<BookDto> getAllBooks() {
    	log.info("Received request for all books.");
		List<BookDto> books = bookService.getAllBooks();
		log.info("Returning response with books {}.", books);
		return books;
	}
    

    
    /**
     * Returns books for given title pattern.
     * <p>
     * The endpoint returns a list of {@link BookDto}s that title matches given pattern.
     * In case no book entity is found for an input book title pattern, returned list is empty.
     * The order of entries in the result list in not guaranteed.
     * @param titlePattern title or part of a title to match
     * @return list of books with title matching given pattern
     */
    @RequestMapping(value="/getBooksByTitle", method = GET, produces = APPLICATION_JSON_UTF8_VALUE, params = {"titlePattern"})
	@ResponseStatus(OK)
    public List<BookDto> getBooksByTitle(
    		@Valid @NotBlank @RequestParam String titlePattern) {
    	log.info("Received request for books with title matching {}.", titlePattern);
    	List<BookDto> books = bookService.getBooksByTitle(titlePattern);
    	log.info("Returning response with books {}.", books);
    	return books;
	}
    
    /**
     * Returns books written by author that matches given pattern.
     * <p>
     * The endpoint returns a list of {@link BookDto}s that author matches given pattern.
     * In case no book entity is found for an input book author pattern, returned list is empty.
     * The order of entries in the result list in not guaranteed.
     * @param authorPattern part of authors name or surname to match
     * @return list of books with author name or surname matching given pattern
     */
    @RequestMapping(value="/getBooksByAuthor", method = GET, produces = APPLICATION_JSON_UTF8_VALUE, params = {"authorPattern"})
    @ResponseStatus(OK)
    public List<BookDto> getBooksByAuthor(
    		@Valid @NotBlank @RequestParam String authorPattern) {
    	log.info("Received request for books with author matching {}.", authorPattern);
    	List<BookDto> books = bookService.getBooksByAuthor(authorPattern);
    	log.info("Returning response with books {}.", books);
    	return books;
    }
    
    /**
     * Returns books that currently are not borrowed.
     * <p>
     * The endpoint returns a list of {@link BookDto}s that currently are not borrowed.
     * In case all books are currently borrowed , returned list is empty.
     * The order of entries in the result list in not guaranteed.
     * @return list of books that are not borrowed at this moment
     */
    @RequestMapping(value="/getAvailableBooks", method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public List<BookDto> getAvailableBooks() {
    	log.info("Received request for get available books.");
    	List<BookDto> books = bookService.getAvailableBooks();
    	log.info("Returning response with books {}.", books);
    	return books;
    }
    
    /**
     * Returns books that were borrowed later than x days ago and not returned yet.
     * <p>
     * The endpoint returns a list of {@link BookDto}s that were borrowed x days ago and not returned yet.
     * In case no book entity were borrowed later than x days ago and not yet returned, returned list is empty.
     * The order of entries in the result list in not guaranteed.
     * @param days minimum  number of days book were rented ago
     * @return list of books that were rented more than x days ago and still not returned
     */
    @RequestMapping(value="/getBooksBorrowedLaterThan", method = GET, produces = APPLICATION_JSON_UTF8_VALUE, params = {"days"})
    @ResponseStatus(OK)
    public List<BookDto> getBooksBorrowedLaterThan(
    		@Min(value = 1) @RequestParam Integer days) {
    	log.info("Received request for books borrowed later than {} days ago.", days);
    	List<BookDto> books = bookService.getBooksBorrowedLaterThan(days);
    	log.info("Returning response with books {}.", books);
    	return books;
    }
    
    @ExceptionHandler({
    	MethodArgumentTypeMismatchException.class,
    	MethodArgumentNotValidException.class,
    	ConstraintViolationException.class})
    public String handleValidationError(Exception ex) {
    	return "Data provided in request was not valid" + ex.getMessage();
    }
    
    @ExceptionHandler(Throwable.class)
    public String handleUnhandledError(Throwable ex) {
    	return "Unknown error occurred while processing request" + ex.getMessage();
    }
}
