package com.estsoft.springproject.book;

public class BookDTO {
	private String id;
	private String name;
	private String author;

	public BookDTO(Book book){
		id = book.id;
		name = book.name;
		author = book.author;
	}
}
