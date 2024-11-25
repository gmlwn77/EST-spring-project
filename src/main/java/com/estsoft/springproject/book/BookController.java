package com.estsoft.springproject.book;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/books")
	public String showAll(Model model){
		List<BookDTO> bookList = bookService.findAll()
			.stream()
			.map(BookDTO::new)
			.toList();
		model.addAttribute("bookList", bookList);

		return "bookManagement";
	}

	@GetMapping("/books/{id}")
	public String showOne(@PathVariable String id, Model model){
		Book book = bookService.findByID(id);
		model.addAttribute("book", new BookDTO(book));
		return "bookDetail";
	}

	@PostMapping("/books")
	public String addBook(@RequestParam String id,
						@RequestParam String name,
						@RequestParam String author){
		bookService.saveOne(new Book(id, name, author));
		return "redirect:/books";
	}
}
