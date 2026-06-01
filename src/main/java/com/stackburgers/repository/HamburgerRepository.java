package com.stackburgers.repository;

import com.stackburgers.model.Hamburger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HamburgerRepository extends JpaRepository<Hamburger, Long> {
    List<Hamburger> findByDisponivelTrue();
    List<Hamburger> findByVegetarianoTrue();
    List<Hamburger> findByTipoCarneIgnoreCase(String tipoCarne);
}
