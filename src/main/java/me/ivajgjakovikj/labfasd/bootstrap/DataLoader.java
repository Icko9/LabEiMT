package me.ivajgjakovikj.labfasd.bootstrap;

import me.ivajgjakovikj.labfasd.domain.Author;
import me.ivajgjakovikj.labfasd.domain.Book;
import me.ivajgjakovikj.labfasd.domain.Category;
import me.ivajgjakovikj.labfasd.domain.Country;
import me.ivajgjakovikj.labfasd.repositories.AuthorRepository;
import me.ivajgjakovikj.labfasd.repositories.BookRepository;
import me.ivajgjakovikj.labfasd.repositories.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataLoader(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCountries();
        loadAuthors();
        loadBooks();
    }

    private void loadBooks() {
        List<Author> authors = authorRepository.findAll();
        List<Book> books = new ArrayList<>();

        Book b1 = Book.builder()
                .author(authors.get(0))
                .availableCopies(10)
                .name("Design Patterns")
                .category(Category.CLASSICS).build();

        Book b2 = Book.builder()
                .author(authors.get(1))
                .availableCopies(3)
                .name("Refactoring")
                .category(Category.BIOGRAPHY).build();

        Book b3 = Book.builder()
                .author(authors.get(2))
                .availableCopies(5)
                .name("Test Driven Development")
                .category(Category.NOVEL).build();

        Book b4 = Book.builder()
                .author(authors.get(0))
                .availableCopies(1)
                .name("Enterprise Architecture")
                .category(Category.THRILER).build();

        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        bookRepository.saveAll(books);
    }

    private void loadAuthors() {
        List<Author> authors = new ArrayList<>();
        List<Country> countryList = countryRepository.findAll();
        Author a1 = Author.builder()
                .name("Ivan")
                .surname("Gjakovikj")
                .country(countryList.get(0)).build();
        Author a2 = Author.builder()
                .name("Fyodor")
                .surname("Dostoyevski")
                .country(countryList.get(1)).build();
        Author a3 = Author.builder()
                .name("Jordan")
                .surname("Peterson")
                .country(countryList.get(3)).build();

        authors.add(a1);
        authors.add(a2);
        authors.add(a3);
        authorRepository.saveAll(authors);
    }

    private void loadCountries() {
        List<Country> countryList = new ArrayList<>();

        Country c1 = Country.builder()
                .name("Macedonia")
                .continent("Europe").build();

        Country c2 = Country.builder()
                .name("Serbia")
                .continent("Europe").build();

        Country c3 = Country.builder()
                .name("Germany")
                .continent("Europe").build();

        Country c4 = Country.builder()
                .name("Japan")
                .continent("Asia").build();

        countryList.add(c1);
        countryList.add(c2);
        countryList.add(c3);
        countryList.add(c4);

        countryRepository.saveAll(countryList);
    }
}
