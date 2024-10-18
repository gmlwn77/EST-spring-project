package com.estsoft.springproject.book;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookService {
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> findAll(){
		return bookRepository.findAll();
	}

	public Book findByID(String id){
		return bookRepository.findById(id).orElse(new Book());
	}

	public Book saveOne(Book book){
		return bookRepository.save(book);
	}
}
