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
@Table(name = "author")
@Data
public class Author implements Serializable {
	
	private static final long serialVersionUID = -7747976336969187123L;

	@Id
    @Column(name = "id_author")
    private Long id;
	
	@Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
    		name = "book_author", 
    		joinColumns = { @JoinColumn(name = "id_author") },
    		inverseJoinColumns = { @JoinColumn(name = "id_book") }
    		)
    List<Book> books = new ArrayList<>();
}
