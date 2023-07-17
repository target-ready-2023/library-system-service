-- Table: book

-- DROP TABLE IF EXISTS public.book;

CREATE TABLE IF NOT EXISTS public.book
(
    book_id integer NOT NULL,
    book_description character varying(255) COLLATE pg_catalog."default",
    book_name character varying(255) COLLATE pg_catalog."default",
    publication_year integer NOT NULL,
    CONSTRAINT book_pkey PRIMARY KEY (book_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.book
    OWNER to postgres;

-- Table: public.author

-- DROP TABLE IF EXISTS public.author;

CREATE TABLE IF NOT EXISTS public.author
(
    author_id integer NOT NULL,
    author_name character varying(255) COLLATE pg_catalog."default",
    book_id integer NOT NULL,
    CONSTRAINT author_pkey PRIMARY KEY (author_id),
    CONSTRAINT book_id FOREIGN KEY (book_id)
        REFERENCES public.book (book_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.author
    OWNER to postgres;


-- Table: public.category

-- DROP TABLE IF EXISTS public.category;

CREATE TABLE IF NOT EXISTS public.category
(
    category_id integer NOT NULL,
    book_id integer NOT NULL,
    category_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT category_pkey PRIMARY KEY (category_id),
    CONSTRAINT book_id FOREIGN KEY (book_id)
        REFERENCES public.book (book_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.category
    OWNER to postgres;