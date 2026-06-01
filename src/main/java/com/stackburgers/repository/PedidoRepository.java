package com.stackburgers.repository;

import com.stackburgers.model.Pedido;
import com.stackburgers.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Buscar pedidos de um cliente específico
    List<Pedido> findByClienteId(Long clienteId);

    // Buscar pedidos por status
    List<Pedido> findByStatus(StatusPedido status);

    // Pedidos de um cliente com determinado status
    List<Pedido> findByClienteIdAndStatus(Long clienteId, StatusPedido status);

    // Ordenar por data de criação (mais recentes primeiro)
    List<Pedido> findAllByOrderByDataCriacaoDesc();
}
