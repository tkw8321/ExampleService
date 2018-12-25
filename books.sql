
CREATE SEQUENCE book_id_book_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE TABLE book (
    id_book integer DEFAULT nextval('"book_id_book_seq"'::text) NOT NULL,
    title character varying(100),
    isbn character varying(25)
);

CREATE SEQUENCE book_author_id_book_author_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE TABLE book_author (
    id_book_author integer DEFAULT nextval('"book_author_id_book_author_seq"'::text) NOT NULL,
    id_book integer,
	id_author integer
);

CREATE SEQUENCE author_id_author_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE TABLE author (
    id_author integer DEFAULT nextval('"author_id_author_seq"'::text) NOT NULL,
    name character varying(250),
    surname character varying(250)
);


CREATE SEQUENCE client_id_client_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE TABLE client (
    id_client integer DEFAULT nextval('"client_id_client_seq"'::text) NOT NULL,
    name character varying(250),
    surname character varying(250)
);


CREATE SEQUENCE book_borrow_id_book_borrow_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE TABLE book_borrow (
    id_book_borrow integer DEFAULT nextval('"book_borrow_id_book_borrow_seq"'::text) NOT NULL,
    id_book integer,
	id_client integer,
	borrow_date date,
	return_date date
);


INSERT INTO book VALUES (1, 'Lsnienie', '978-83-7648-809-7');
INSERT INTO book VALUES (2, 'Bastion', '978-83-812-5388-8');
INSERT INTO book VALUES (3, 'Najpiękniejsze baśnie braci Grimm', '978-83-245-7251-9');
INSERT INTO book VALUES (4, 'Akademia pana Kleksa', '978-83-658-7530-3');
INSERT INTO book VALUES (5, 'Podróże Pana Kleksa', '978-83-751-7734-3');

INSERT INTO author VALUES (1, 'Stephen', 'King');
INSERT INTO author VALUES (2, 'Jakub', 'Grimm');
INSERT INTO author VALUES (3, 'Wilhelm', 'Grimm');
INSERT INTO author VALUES (4, 'Jan', 'Brzechwa');

INSERT INTO book_author VALUES (1, 1, 1);
INSERT INTO book_author VALUES (2, 2, 1);
INSERT INTO book_author VALUES (3, 3, 2);
INSERT INTO book_author VALUES (4, 3, 3);
INSERT INTO book_author VALUES (5, 4, 4);
INSERT INTO book_author VALUES (6, 5, 4);

INSERT INTO client VALUES (1, 'Mariusz', 'Lewandowski');


INSERT INTO book_borrow VALUES (1, 1, 1, '2018-12-20', '2018-12-23');
INSERT INTO book_borrow VALUES (2, 3, 1, '2018-12-23', NULL);




CREATE UNIQUE INDEX ui_isbn ON book USING btree (isbn);


ALTER TABLE ONLY book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id_book);

ALTER TABLE ONLY book_author
    ADD CONSTRAINT book_author_pkey PRIMARY KEY (id_book_author);

ALTER TABLE ONLY author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id_author);

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id_client);

ALTER TABLE ONLY book_borrow
    ADD CONSTRAINT book_borrow_pkey PRIMARY KEY (id_book_borrow);

ALTER TABLE book_author 
    ADD CONSTRAINT book_author_book_fk FOREIGN KEY (id_book) REFERENCES book (id_book);
	
ALTER TABLE book_author 
    ADD CONSTRAINT book_author_author_fk FOREIGN KEY (id_author) REFERENCES author (id_author);
	
ALTER TABLE book_borrow 
    ADD CONSTRAINT book_borrow_book_fk FOREIGN KEY (id_book) REFERENCES book (id_book);
	
ALTER TABLE book_borrow 
    ADD CONSTRAINT book_borrow_client_fk FOREIGN KEY (id_client) REFERENCES client (id_client);


	
SELECT pg_catalog.setval('book_id_book_seq', 6, true);
SELECT pg_catalog.setval('book_author_id_book_author_seq', 7, true);
SELECT pg_catalog.setval('author_id_author_seq', 5, true);
SELECT pg_catalog.setval('client_id_client_seq', 2, true);
SELECT pg_catalog.setval('book_borrow_id_book_borrow_seq', 3, true);
