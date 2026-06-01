package com.stackburgers.service;

import com.stackburgers.dto.PedidoDTO;
import com.stackburgers.model.*;
import com.stackburgers.repository.ClienteRepository;
import com.stackburgers.repository.PedidoRepository;
import com.stackburgers.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * =============================================
 * POLIMORFISMO em ação neste Service
 * =============================================
 * O pedido pode conter Hamburger e/ou Bebida.
 * O sistema trata ambos como "Produto" graças à herança,
 * e o cálculo do total funciona para qualquer subclasse.
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Lista todos os pedidos (para o admin)
     */
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAllByOrderByDataCriacaoDesc();
    }

    /**
     * Lista pedidos de um cliente específico
     */
    public List<Pedido> listarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    /**
     * Lista pedidos por status
     */
    public List<Pedido> listarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }

    /**
     * Busca pedido por ID
     */
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    /**
     * Cria um novo pedido a partir dos dados recebidos.
     *
     * POLIMORFISMO aqui:
     * - Produto pode ser Hamburger ou Bebida
     * - Ambos têm getPreco() herdado de Produto
     * - calcularValorTotal() soma tudo corretamente
     */
    @Transactional
    public Pedido criarPedido(PedidoDTO.PedidoRequest request) {
        // 1. Buscar o cliente
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cliente não encontrado: " + request.getClienteId()));

        // 2. Verificar se tem itens
        if (request.getItens() == null || request.getItens().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve ter pelo menos um item.");
        }

        // 3. Criar o pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setObservacao(request.getObservacao());

        // 4. Processar cada item do pedido
        for (PedidoDTO.ItemPedidoRequest itemRequest : request.getItens()) {
            // Busca o produto (pode ser Hamburger ou Bebida — Polimorfismo!)
            Produto produto = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Produto não encontrado: " + itemRequest.getProdutoId()));

            // Verifica se o produto está disponível
            if (!produto.getDisponivel()) {
                throw new IllegalArgumentException(
                        "Produto indisponível: " + produto.getNome());
            }

            // Cria o item do pedido
            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemRequest.getQuantidade());
            // Salva o preço atual (histórico de preço)
            item.setPrecoUnitario(produto.getPreco());

            pedido.adicionarItem(item); // Também recalcula o total
        }

        // 5. Salvar e retornar
        return pedidoRepository.save(pedido);
    }

    /**
     * Atualiza o status de um pedido (para o admin)
     */
    public Optional<Pedido> atualizarStatus(Long id, StatusPedido novoStatus) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.atualizarStatus(novoStatus);
            return pedidoRepository.save(pedido);
        });
    }

    /**
     * Cancela um pedido (apenas se ainda não foi para o preparo)
     */
    public Optional<Pedido> cancelar(Long id) {
        return pedidoRepository.findById(id).map(pedido -> {
            if (pedido.getStatus() == StatusPedido.EM_PREPARO
                    || pedido.getStatus() == StatusPedido.PRONTO
                    || pedido.getStatus() == StatusPedido.ENTREGUE) {
                throw new IllegalStateException(
                        "Não é possível cancelar um pedido com status: " + pedido.getStatus());
            }
            pedido.atualizarStatus(StatusPedido.CANCELADO);
            return pedidoRepository.save(pedido);
        });
    }
}
