package book.web;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import book.Book;
import book.data.BookRepository;

@Controller
public class BookController {
	private final BookRepository bookRepo;
	@Autowired
	public BookController(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}
	@ModelAttribute
	public void addBookToModel(Model model) {
		List<Book> books = (List<Book>) bookRepo.findAll();
		model.addAttribute("books", books);
	}
	@GetMapping
	public String showBook() {
		return "book";
	}
	
	@GetMapping("/detailBook")
	public String detailBook(@RequestParam("code") String code, Model model) {
		Optional <Book> bookX = bookRepo.findById(code);
		bookX.ifPresent(book -> model.addAttribute("book", book));
		return "detailBook";
	}
	
	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addBook";
	}

	@GetMapping("/editForm")
	public String editFormBook(@RequestParam("code") String code, Model model) {
		Optional<Book> bookX = bookRepo.findById(code);
		bookX.ifPresent(book -> model.addAttribute("book", book));
		return "editBook";
	}
	
	@GetMapping("/confirmDelete")
	public String confirmDeleteBook(@RequestParam("code") String code, Model model) {
		Optional<Book> bookX = bookRepo.findById(code);
		bookX.ifPresent(book -> model.addAttribute("book", book));
		return "deleteBook";
	}

	@PostMapping("/delete")
	public String deleteBook(Book book, Model model) {
		bookRepo.delete(book);
		model.addAttribute(book);
		return "redirect:/";
	}

	@PostMapping("/save")
	public String saveBook(@Valid Book book, Model model, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "addBook";
		else {
			bookRepo.save(book);
			model.addAttribute(book);
			return "redirect:/";
		}
	}

	@PostMapping("/edit")
	public String editBook(Book book, Model model) {
		bookRepo.save(book);
		model.addAttribute(book);
		return "redirect:/";
	}
}
