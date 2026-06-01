package com.stackburgers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;

/**
 * Representa um item dentro de um pedido.
 * Guarda referência ao produto e a quantidade.
 *
 * Relacionamento: ManyToOne com Pedido e com Produto
 */
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento: muitos itens pertencem a um pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    @JsonBackReference
    private Pedido pedido;

    // Relacionamento: cada item referencia um produto
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Min(value = 1, message = "Quantidade mínima é 1")
    @Column(nullable = false)
    private Integer quantidade;

    // Preço no momento da compra (pode mudar depois, por isso salvamos aqui)
    @Column(nullable = false)
    private Double precoUnitario;

    // ===============================
    // MÉTODO DE NEGÓCIO
    // ===============================

    /**
     * Calcula o subtotal deste item.
     * POLIMORFISMO: não importa se é Hamburger ou Bebida,
     * o cálculo é o mesmo (quantidade * preço unitário).
     */
    public Double calcularSubtotal() {
        return this.quantidade * this.precoUnitario;
    }

    // ===============================
    // GETTERS E SETTERS
    // ===============================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }
}
