package com.stackburgers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * =============================================
 * CONCEITO DE POO: ABSTRAÇÃO + ENCAPSULAMENTO
 * =============================================
 * Classe abstrata que define o "molde" de todo produto.
 * Não pode ser instanciada diretamente — apenas subclasses
 * como Hamburger e Bebida podem ser criadas.
 *
 * Encapsulamento: todos os atributos são PRIVATE,
 * acessados apenas por getters e setters.
 */
@Entity
@Table(name = "produtos")
@Inheritance(strategy = InheritanceType.JOINED) // cada subclasse tem sua tabela
@DiscriminatorColumn(name = "tipo_produto")     // coluna que diferencia o tipo
public abstract class Produto {

    // ===============================
    // ATRIBUTOS PRIVADOS (Encapsulamento)
    // ===============================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do produto é obrigatório")
    @Column(nullable = false)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Boolean disponivel = true;

    // ===============================
    // MÉTODO ABSTRATO (Abstração + Polimorfismo)
    // ===============================

    /**
     * Cada subclasse implementa sua própria categoria.
     * Isso é Polimorfismo: mesma assinatura, comportamentos diferentes.
     */
    public abstract String getCategoria();

    // ===============================
    // GETTERS E SETTERS (Encapsulamento)
    // ===============================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Produto{id=" + id + ", nome='" + nome + "', preco=" + preco + "}";
    }
}
