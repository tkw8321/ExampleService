package com.example.demo.book.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;

/**
 * @author lewmar
 */
@Entity
@Table(name = "book_borrow")
@Data
public class BookBorrow implements Serializable {
	
	private static final long serialVersionUID = -9140105448733344432L;
	
	@Id
	@Column(name = "id_book_borrow")
	private Long id;
	
	@Column(name = "borrow_date")
    private LocalDate borrow_date;
	
	@Column(name = "return_date")
    private LocalDate return_date;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="id_book")
	private Book book;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="id_client")
	private Client client;
}
