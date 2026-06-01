package com.stackburgers.service;

import com.stackburgers.dto.ProdutoDTO;
import com.stackburgers.model.Hamburger;
import com.stackburgers.repository.HamburgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * =============================================
 * CAMADA SERVICE
 * =============================================
 * Contém a lógica de negócio relacionada a Hambúrgueres.
 * Os Controllers chamam os Services.
 * Os Services chamam os Repositories.
 */
@Service
public class HamburgerService {

    // Injeção de dependência: Spring injeta automaticamente
    @Autowired
    private HamburgerRepository hamburgerRepository;

    /**
     * Lista todos os hambúrgueres
     */
    public List<Hamburger> listarTodos() {
        return hamburgerRepository.findAll();
    }

    /**
     * Lista apenas os hambúrgueres disponíveis
     */
    public List<Hamburger> listarDisponiveis() {
        return hamburgerRepository.findByDisponivelTrue();
    }

    /**
     * Lista hambúrgueres vegetarianos
     */
    public List<Hamburger> listarVegetarianos() {
        return hamburgerRepository.findByVegetarianoTrue();
    }

    /**
     * Busca hambúrguer por ID
     */
    public Optional<Hamburger> buscarPorId(Long id) {
        return hamburgerRepository.findById(id);
    }

    /**
     * Cadastra um novo hambúrguer
     */
    public Hamburger cadastrar(ProdutoDTO.HamburgerRequest request) {
        Hamburger hamburger = new Hamburger();
        hamburger.setNome(request.getNome());
        hamburger.setDescricao(request.getDescricao());
        hamburger.setPreco(request.getPreco());
        hamburger.setTipoCarne(request.getTipoCarne());
        hamburger.setIngredientes(request.getIngredientes());
        hamburger.setCalorias(request.getCalorias());
        hamburger.setVegetariano(request.getVegetariano());
        hamburger.setDisponivel(request.getDisponivel());
        return hamburgerRepository.save(hamburger);
    }

    /**
     * Atualiza dados de um hambúrguer existente
     */
    public Optional<Hamburger> atualizar(Long id, ProdutoDTO.HamburgerRequest request) {
        return hamburgerRepository.findById(id).map(hamburger -> {
            hamburger.setNome(request.getNome());
            hamburger.setDescricao(request.getDescricao());
            hamburger.setPreco(request.getPreco());
            hamburger.setTipoCarne(request.getTipoCarne());
            hamburger.setIngredientes(request.getIngredientes());
            hamburger.setCalorias(request.getCalorias());
            hamburger.setVegetariano(request.getVegetariano());
            hamburger.setDisponivel(request.getDisponivel());
            return hamburgerRepository.save(hamburger);
        });
    }

    /**
     * Remove um hambúrguer (ou apenas desativa)
     */
    public boolean remover(Long id) {
        return hamburgerRepository.findById(id).map(h -> {
            hamburgerRepository.delete(h);
            return true;
        }).orElse(false);
    }

    /**
     * Alterna disponibilidade (ativar/desativar)
     */
    public Optional<Hamburger> alternarDisponibilidade(Long id) {
        return hamburgerRepository.findById(id).map(h -> {
            h.setDisponivel(!h.getDisponivel());
            return hamburgerRepository.save(h);
        });
    }
}
