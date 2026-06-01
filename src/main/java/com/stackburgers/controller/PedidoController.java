package com.stackburgers.controller;

import com.stackburgers.dto.PedidoDTO;
import com.stackburgers.model.Pedido;
import com.stackburgers.model.StatusPedido;
import com.stackburgers.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /**
     * GET /api/pedidos
     * Lista todos os pedidos (admin)
     */
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    /**
     * GET /api/pedidos/cliente/{clienteId}
     * Lista pedidos de um cliente
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoService.listarPorCliente(clienteId));
    }

    /**
     * GET /api/pedidos/status/{status}
     * Lista pedidos por status (ex: EM_PREPARO)
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pedido>> listarPorStatus(@PathVariable StatusPedido status) {
        return ResponseEntity.ok(pedidoService.listarPorStatus(status));
    }

    /**
     * GET /api/pedidos/{id}
     * Busca pedido por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/pedidos
     * Cria um novo pedido
     *
     * Exemplo de body:
     * {
     *   "clienteId": 1,
     *   "itens": [
     *     { "produtoId": 1, "quantidade": 2 },
     *     { "produtoId": 5, "quantidade": 1 }
     *   ],
     *   "observacao": "Sem cebola"
     * }
     */
    @PostMapping
    public ResponseEntity<?> criarPedido(@Valid @RequestBody PedidoDTO.PedidoRequest request) {
        try {
            Pedido pedido = pedidoService.criarPedido(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * PATCH /api/pedidos/{id}/status
     * Atualiza o status do pedido (admin)
     *
     * Exemplo de body: { "status": "CONFIRMADO" }
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(
            @PathVariable Long id,
            @RequestBody PedidoDTO.AtualizarStatusRequest request) {
        return pedidoService.atualizarStatus(id, request.getStatus())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * PATCH /api/pedidos/{id}/cancelar
     * Cancela um pedido
     */
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        try {
            return pedidoService.cancelar(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
