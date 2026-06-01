package com.stackburgers.controller;

import com.stackburgers.dto.ProdutoDTO;
import com.stackburgers.model.Bebida;
import com.stackburgers.service.BebidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bebidas")
@CrossOrigin(origins = "*")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;

    @GetMapping
    public ResponseEntity<List<Bebida>> listarTodas() {
        return ResponseEntity.ok(bebidaService.listarTodas());
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Bebida>> listarDisponiveis() {
        return ResponseEntity.ok(bebidaService.listarDisponiveis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bebida> buscarPorId(@PathVariable Long id) {
        return bebidaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Bebida> cadastrar(@Valid @RequestBody ProdutoDTO.BebidaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bebidaService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bebida> atualizar(@PathVariable Long id,
                                             @Valid @RequestBody ProdutoDTO.BebidaRequest request) {
        return bebidaService.atualizar(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (bebidaService.remover(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<Bebida> alternarDisponibilidade(@PathVariable Long id) {
        return bebidaService.alternarDisponibilidade(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
