package com.stackburgers.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * Representa um pedido feito por um cliente.
 *
 * Relacionamentos:
 * - ManyToOne com Cliente (um cliente tem vários pedidos)
 * - OneToMany com ItemPedido (um pedido tem vários itens)
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com o cliente que fez o pedido
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Lista de itens do pedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemPedido> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status = StatusPedido.AGUARDANDO;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column
    private LocalDateTime dataAtualizacao;

    @Column(length = 300)
    private String observacao;

    @Column(nullable = false)
    private Double valorTotal = 0.0;

    // ===============================
    // MÉTODOS DE NEGÓCIO
    // ===============================

    /**
     * POLIMORFISMO em ação:
     * Percorre todos os itens (que podem ser Hamburger ou Bebida)
     * e calcula o total usando calcularSubtotal() de cada item.
     * Não importa o tipo do produto — o cálculo funciona para todos.
     */
    public void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .mapToDouble(ItemPedido::calcularSubtotal)
                .sum();
    }

    /**
     * Adiciona um item ao pedido e recalcula o total
     */
    public void adicionarItem(ItemPedido item) {
        item.setPedido(this);
        this.itens.add(item);
        calcularValorTotal();
    }

    /**
     * Atualiza o status e registra a data de atualização
     */
    public void atualizarStatus(StatusPedido novoStatus) {
        this.status = novoStatus;
        this.dataAtualizacao = LocalDateTime.now();
    }

    // ===============================
    // GETTERS E SETTERS
    // ===============================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }

    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
}
