-- Table: public.book

-- DROP TABLE IF EXISTS public.book;

CREATE TABLE IF NOT EXISTS public.book
(
    book_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    book_name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    book_description character varying(200) COLLATE pg_catalog."default" NOT NULL,
    author_name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    category character varying(200) COLLATE pg_catalog."default" NOT NULL,
    publication_year integer NOT NULL,
    CONSTRAINT book_pkey PRIMARY KEY (book_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.book
    OWNER to postgres;


INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('Life of Pi', 'Story of a boy','Yann Martel', 'Adventure' ,2001);

INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('And Then There Were None', 'Story of ten strangers involved in a murder','Agatha Christie', 'Mystery' ,1939);

INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('The Adventures of Sherlock Holmes',
	 'The stories involve the peculiar exploits of the sharp-witted detective Sherlock Holmes',
	 'Sir Arthur Conan Doyle', 'Mystery' ,1892);

INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('Jaya : An Illustrated Retelling of Mahabharata',
	'This presents precisely that fresh perspective on the epic saga of Mahabharata','Devdutta Pattanaik', 'History' ,2010);

INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('Sita : An Illustrated Retelling of Ramayana',
	'This book speculates on Sita by tracing the various phases of her life','Devdutta Pattanaik', 'History' ,2013);

INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('The Rose and The Yew Tree',
	'This is a story of social circles and the desire to rise above your station.',
	'Agatha Christie', 'Romance' ,1948);

INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('The Only One Left', 'The plot concerns a woman who takes a job caring for elderly invalid Lenora Hope, who was accused decades ago of murdering her parents and younger sister.',
	'Riley Sager', 'Horror' ,2023);

INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('Contact', 'This suggests that cultural conflicts between religion and science would be brought to the fore by the apparent contact with aliens that occurs in the film',
	'Carl Sagan', 'Science Fiction' ,1985);

INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('Shug', 'Shug falls hard for her best boy friend, is stuck tutoring her worst enemy',
	 'Jenny Han', 'Romance' , 2006);

INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('The Hound of Death', 'A young Englishman visiting Cornwall finds himself delving into the legend of a Belgian nun who is living as a refugee in the village.',
	'Agatha Christie', 'Horror' ,1933);

INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('The War of the Worlds', 'This book is about a fictional invasion of Southern England by Martians.','H G Wells', 'Science Fiction' ,1898);

INSERT INTO public.book(
	book_name, book_description, author_name, category, publication_year)
	VALUES ('The Adventure of Dancing Men',	' The little dancing men are at the heart of a mystery which seems to be driving his young wife Elsie Patrick to distraction.',
	'Sir Arthur Conan Doyle', 'Adventure' ,1903);

SELECT * FROM public.book;
------------------------------------------------------------------------------------------------------------------------

-- Table: public.author

-- DROP TABLE IF EXISTS public.author;

CREATE TABLE IF NOT EXISTS public.author
(
    author_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    author_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT author_pkey PRIMARY KEY (author_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.author
    OWNER to postgres;

INSERT INTO public.author(author_name)
	VALUES ('Yann Martel');

INSERT INTO public.author(author_name)
	VALUES ('Agatha Christie');

INSERT INTO public.author(author_name)
	VALUES ('Sir Arthur Conan Doyle');

INSERT INTO public.author(author_name)
	VALUES ('Devdutta Pattnaik');

INSERT INTO public.author(author_name)
	VALUES ('Riley Sager');

INSERT INTO public.author(author_name)
	VALUES ('Carl Sagan');

INSERT INTO public.author(author_name)
	VALUES ('Jenny Han');

INSERT INTO public.author(author_name)
	VALUES ('H G Wells');


SELECT * FROM public.author;
----------------------------------------------------------------------------------------------------------------------------

-- Table: public.category

-- DROP TABLE IF EXISTS public.category;

CREATE TABLE IF NOT EXISTS public.category
(
    category_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    category_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (category_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.category
    OWNER to postgres;

INSERT INTO public.category(category_name)
	VALUES ('Adventure');

INSERT INTO public.category(category_name)
    	VALUES ('Horror');

INSERT INTO public.category(category_name)
	VALUES ('Mystery');

INSERT INTO public.category(category_name)
	VALUES ('History');

INSERT INTO public.category(category_name)
    	VALUES ('Science Fiction');

INSERT INTO public.category(category_name)
	VALUES ('Romance');

SELECT * FROM public.category;

---------------------------------------------------------------------------------------------------------------