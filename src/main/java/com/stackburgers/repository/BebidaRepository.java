package com.stackburgers.repository;

import com.stackburgers.model.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long> {
    List<Bebida> findByDisponivelTrue();
    List<Bebida> findByAlcolicaFalse();
    List<Bebida> findByTipoIgnoreCase(String tipo);
}
