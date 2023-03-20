package me.ivajgjakovikj.labfasd.repositories;

import me.ivajgjakovikj.labfasd.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
