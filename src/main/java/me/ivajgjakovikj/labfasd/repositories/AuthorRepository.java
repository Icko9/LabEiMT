package me.ivajgjakovikj.labfasd.repositories;

import me.ivajgjakovikj.labfasd.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
