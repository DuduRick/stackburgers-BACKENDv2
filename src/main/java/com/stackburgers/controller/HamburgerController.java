package com.stackburgers.controller;

import com.stackburgers.dto.ProdutoDTO;
import com.stackburgers.model.Hamburger;
import com.stackburgers.service.HamburgerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * =============================================
 * CAMADA CONTROLLER
 * =============================================
 * Recebe requisições HTTP do frontend.
 * Chama o Service para processar a lógica.
 * Retorna respostas HTTP com os dados.
 *
 * @RestController = @Controller + @ResponseBody
 * @RequestMapping = prefixo de todas as rotas deste controller
 * @CrossOrigin = permite que o frontend acesse a API (CORS)
 */
@RestController
@RequestMapping("/api/hamburgeres")
@CrossOrigin(origins = "*") // Em produção, substituir "*" pelo domínio do frontend
public class HamburgerController {

    @Autowired
    private HamburgerService hamburgerService;

    /**
     * GET /api/hamburgeres
     * Lista todos os hambúrgueres
     */
    @GetMapping
    public ResponseEntity<List<Hamburger>> listarTodos() {
        return ResponseEntity.ok(hamburgerService.listarTodos());
    }

    /**
     * GET /api/hamburgeres/disponiveis
     * Lista apenas hambúrgueres disponíveis (para o cardápio)
     */
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Hamburger>> listarDisponiveis() {
        return ResponseEntity.ok(hamburgerService.listarDisponiveis());
    }

    /**
     * GET /api/hamburgeres/vegetarianos
     * Lista hambúrgueres vegetarianos
     */
    @GetMapping("/vegetarianos")
    public ResponseEntity<List<Hamburger>> listarVegetarianos() {
        return ResponseEntity.ok(hamburgerService.listarVegetarianos());
    }

    /**
     * GET /api/hamburgeres/{id}
     * Busca um hambúrguer pelo ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Hamburger> buscarPorId(@PathVariable Long id) {
        return hamburgerService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/hamburgeres
     * Cadastra um novo hambúrguer (apenas admin)
     */
    @PostMapping
    public ResponseEntity<Hamburger> cadastrar(@Valid @RequestBody ProdutoDTO.HamburgerRequest request) {
        Hamburger hamburger = hamburgerService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(hamburger);
    }

    /**
     * PUT /api/hamburgeres/{id}
     * Atualiza um hambúrguer existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Hamburger> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoDTO.HamburgerRequest request) {
        return hamburgerService.atualizar(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/hamburgeres/{id}
     * Remove um hambúrguer
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (hamburgerService.remover(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * PATCH /api/hamburgeres/{id}/disponibilidade
     * Ativa ou desativa um hambúrguer
     */
    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<Hamburger> alternarDisponibilidade(@PathVariable Long id) {
        return hamburgerService.alternarDisponibilidade(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
