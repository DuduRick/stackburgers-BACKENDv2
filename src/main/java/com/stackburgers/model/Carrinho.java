package com.stackburgers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Carrinho de compras do cliente.
 * Não é uma entidade JPA (não salva no banco diretamente).
 * É convertido em Pedido quando o cliente finaliza a compra.
 *
 * Demonstra Encapsulamento com lógica de negócio interna.
 */
public class Carrinho {

    private Long clienteId;
    private List<ItemCarrinho> itens = new ArrayList<>();

    public Carrinho(Long clienteId) {
        this.clienteId = clienteId;
    }

    // ===============================
    // MÉTODOS DE NEGÓCIO
    // ===============================

    /**
     * Adiciona produto ao carrinho.
     * Se o produto já existe, aumenta a quantidade.
     */
    public void adicionarItem(Produto produto, int quantidade) {
        Optional<ItemCarrinho> itemExistente = itens.stream()
                .filter(i -> i.getProduto().getId().equals(produto.getId()))
                .findFirst();

        if (itemExistente.isPresent()) {
            // Produto já está no carrinho, soma a quantidade
            ItemCarrinho item = itemExistente.get();
            item.setQuantidade(item.getQuantidade() + quantidade);
        } else {
            // Produto novo no carrinho
            itens.add(new ItemCarrinho(produto, quantidade));
        }
    }

    /**
     * Remove um produto do carrinho pelo ID do produto
     */
    public void removerItem(Long produtoId) {
        itens.removeIf(i -> i.getProduto().getId().equals(produtoId));
    }

    /**
     * Calcula o total do carrinho.
     * POLIMORFISMO: não importa se é Hamburger ou Bebida
     */
    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemCarrinho::calcularSubtotal)
                .sum();
    }

    /**
     * Limpa todos os itens do carrinho
     */
    public void limpar() {
        itens.clear();
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    // GETTERS E SETTERS
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public List<ItemCarrinho> getItens() { return itens; }
    public void setItens(List<ItemCarrinho> itens) { this.itens = itens; }

    // ===============================
    // CLASSE INTERNA: ItemCarrinho
    // ===============================

    /**
     * Representa um item dentro do carrinho (antes de virar pedido)
     */
    public static class ItemCarrinho {
        private Produto produto;
        private int quantidade;

        public ItemCarrinho(Produto produto, int quantidade) {
            this.produto = produto;
            this.quantidade = quantidade;
        }

        public double calcularSubtotal() {
            return produto.getPreco() * quantidade;
        }

        public Produto getProduto() { return produto; }
        public void setProduto(Produto produto) { this.produto = produto; }
        public int getQuantidade() { return quantidade; }
        public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    }
}
