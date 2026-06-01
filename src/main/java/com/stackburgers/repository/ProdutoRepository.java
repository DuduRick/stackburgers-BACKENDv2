package com.stackburgers.repository;

import com.stackburgers.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository de Produto.
 * Spring Data JPA já fornece os métodos básicos:
 * save(), findById(), findAll(), deleteById(), etc.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Buscar produtos disponíveis
    List<Produto> findByDisponivelTrue();

    // Buscar por nome (contendo o texto, ignorando maiúsculas/minúsculas)
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    // Buscar produtos dentro de uma faixa de preço
    List<Produto> findByPrecoBetween(Double precoMin, Double precoMax);
}
