package me.ivajgjakovikj.labfasd.services;

import me.ivajgjakovikj.labfasd.dtos.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO findBookById(Long bookId);

    List<BookDTO> getAllBooks();

    BookDTO createBook(BookDTO bookDTO);

    void deleteBook(Long bookId);

    BookDTO updateBook(BookDTO bookDTO);

    void rentBook(Long bookId);
}
