package me.ivajgjakovikj.labfasd.services.impl;

import me.ivajgjakovikj.labfasd.domain.Author;
import me.ivajgjakovikj.labfasd.domain.Book;
import me.ivajgjakovikj.labfasd.domain.Category;
import me.ivajgjakovikj.labfasd.dtos.BookDTO;
import me.ivajgjakovikj.labfasd.repositories.AuthorRepository;
import me.ivajgjakovikj.labfasd.repositories.BookRepository;
import me.ivajgjakovikj.labfasd.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDTO findBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(RuntimeException::new);
        return getBookDTO(book);
    }

    private static BookDTO getBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAvailableCopies(book.getAvailableCopies());
        bookDTO.setCategory(book.getCategory().toString());
        bookDTO.setAuthorId(book.getAuthor().getId());
        bookDTO.setAuthorName(book.getAuthor().getName());
        bookDTO.setAuthorSurname(book.getAuthor().getSurname());
        return bookDTO;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(x -> getBookDTO(x)).collect(Collectors.toList());
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Author author = authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(RuntimeException::new);
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setCategory(Category.valueOf(bookDTO.getCategory()));
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        bookDTO.setId(savedBook.getId());
        return bookDTO;
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        Book bookToUpdate = bookRepository.findById(bookDTO.getId()).orElseThrow(RuntimeException::new);
        bookToUpdate.setName(bookDTO.getName());
        bookToUpdate.setCategory(Category.valueOf(bookDTO.getCategory()));
        bookToUpdate.setAuthor(authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(RuntimeException::new));
        bookToUpdate.setAvailableCopies(bookDTO.getAvailableCopies());
        bookToUpdate = bookRepository.save(bookToUpdate);
        bookDTO.setName(bookToUpdate.getName());
        bookDTO.setCategory(bookToUpdate.getCategory().toString());
        bookDTO.setAvailableCopies(bookToUpdate.getAvailableCopies());
        bookDTO.setAuthorId(bookToUpdate.getAuthor().getId());
        bookDTO.setAuthorName(bookToUpdate.getAuthor().getName());
        bookDTO.setAuthorSurname(bookToUpdate.getAuthor().getSurname());
        return bookDTO;
    }

    @Override
    public void rentBook(Long bookId) {
        Book rentedBook = bookRepository.findById(bookId).orElseThrow(RuntimeException::new);
        rentedBook.setAvailableCopies(rentedBook.getAvailableCopies() - 1);
        bookRepository.save(rentedBook);
    }
}
