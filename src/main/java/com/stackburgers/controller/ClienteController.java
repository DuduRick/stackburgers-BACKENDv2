package com.stackburgers.controller;

import com.stackburgers.dto.ClienteDTO;
import com.stackburgers.model.Cliente;
import com.stackburgers.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * GET /api/clientes
     * Lista todos os clientes (admin only)
     */
    @GetMapping
    public ResponseEntity<List<ClienteDTO.ClienteResponse>> listarTodos() {
        List<ClienteDTO.ClienteResponse> clientes = clienteService.listarTodos()
                .stream()
                .map(clienteService::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }

    /**
     * GET /api/clientes/{id}
     * Busca cliente por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO.ClienteResponse> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(clienteService::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/clientes
     * Cadastra novo cliente
     */
    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody ClienteDTO.ClienteRequest request) {
        try {
            Cliente cliente = clienteService.cadastrar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.toResponse(cliente));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    /**
     * PUT /api/clientes/{id}
     * Atualiza dados do cliente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO.ClienteResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDTO.ClienteRequest request) {
        return clienteService.atualizar(id, request)
                .map(clienteService::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/clientes/{id}
     * Desativa cliente (soft delete)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        if (clienteService.desativar(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
