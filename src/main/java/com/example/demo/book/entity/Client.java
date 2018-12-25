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
@Table(name = "client")
@Data
public class Client implements Serializable {
	
	private static final long serialVersionUID = 8608228010424560186L;

	@Id
    @Column(name = "id_client")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;
    
    @OneToMany(mappedBy="client")
    List<BookBorrow> book = new ArrayList<>();
}
