package me.ivajgjakovikj.labfasd.web;

import me.ivajgjakovikj.labfasd.domain.Category;
import me.ivajgjakovikj.labfasd.dtos.BookDTO;
import me.ivajgjakovikj.labfasd.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/", "/books"})
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping("/books")
    public BookDTO createBook(BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @DeleteMapping("/books")
    public void deleteBook(Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/books")
    public BookDTO updateBook(BookDTO bookDTO) {
        return bookService.updateBook(bookDTO);
    }

    @PutMapping("/books/rent")
    public void updateBook(Long bookId) {
        bookService.rentBook(bookId);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return Arrays.asList(Category.values());
    }
}
