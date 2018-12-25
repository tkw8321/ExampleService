package com.example.demo.book.control.repository;

import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import com.example.demo.book.entity.Book;

@Repository
public class BookRepository {

    private final EntityManager em;

    @Inject
    public BookRepository(EntityManager em) {
        this.em = em;
    }
    
    public List<Book> findAllBooks() {
    	return em.createQuery("" + "SELECT b FROM Book b \n", Book.class).getResultList();
    }
    
    public List<Book> getBooksByTitle(String titlePattern) {
    	return em.createQuery("" + 
    			"SELECT b FROM Book b \n" +
    			"WHERE (upper(b.title) LIKE :titlePattern)", Book.class)
    			.setParameter("titlePattern", "%"+titlePattern.toUpperCase()+"%")
    			.getResultList();
    }
    
    public List<Book> getBooksByAuthor(String authorPattern) {
    	return em.createQuery("" +
    			"SELECT b FROM Book b join b.authors a \n" +
    			"WHERE (upper(a.name) LIKE :authorPattern \n" + 
    			" OR upper(a.surname) LIKE :authorPattern)", Book.class)
    			.setParameter("authorPattern", "%"+authorPattern.toUpperCase()+"%")
    			.getResultList();
    }
    
    public List<Book> getAvailableBooks() {
    	return em.createQuery("" +
    			"SELECT b FROM Book b \n" +
    			"WHERE (b.id not in  \n" + 
    			"(SELECT bb.book.id FROM BookBorrow bb WHERE bb.return_date is null))", Book.class)
    			.getResultList();
    }
    
    public List<Book> getBooksBorrowedLaterThan(Integer days) {
    	
    	LocalDate date = LocalDate.now().minusDays(days);
    	
    	return em.createQuery("" +
    			"SELECT b FROM Book b \n" +
    			"WHERE (b.id in  \n" + 
    			"(SELECT bb.book.id FROM BookBorrow bb WHERE bb.return_date is null AND borrow_date <= DATE(:date)))", Book.class)
    			.setParameter("date", date)
    			.getResultList();
    }
}
