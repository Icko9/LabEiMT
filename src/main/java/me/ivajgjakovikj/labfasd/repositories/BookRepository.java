package me.ivajgjakovikj.labfasd.repositories;

import me.ivajgjakovikj.labfasd.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
