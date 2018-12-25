package com.example.demo.book.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

/**
 * @author lewmar
 */
@Entity
@Table(name = "book")
@Data
public class Book implements Serializable {
	
	private static final long serialVersionUID = 8229239219058529678L;

    @Id
    @Column(name = "id_book")
    private Long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "isbn")
    private String isbn;
    
    @ManyToMany(mappedBy = "books")
    private List<Author> authors = new ArrayList<>();
    
    @OneToMany(mappedBy="book")
    List<BookBorrow> borrows = new ArrayList<>();
}
