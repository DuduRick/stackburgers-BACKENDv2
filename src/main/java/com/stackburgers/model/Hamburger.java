package com.stackburgers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * =============================================
 * CONCEITO DE POO: HERANÇA
 * =============================================
 * Hamburger HERDA de Produto.
 * Recebe todos os atributos (nome, preco, etc.)
 * e adiciona seus próprios: ingredientes, tipoCarne, etc.
 *
 * Polimorfismo: implementa getCategoria() com seu próprio valor.
 */
@Entity
@Table(name = "hamburgeres")
@DiscriminatorValue("HAMBURGER")
public class Hamburger extends Produto {

    // Atributos específicos do Hamburger
    @NotBlank(message = "Tipo de carne é obrigatório")
    @Column(nullable = false)
    private String tipoCarne; // Ex: Angus, Frango, Vegano

    @Column(length = 500)
    private String ingredientes; // Ex: Queijo, Alface, Tomate, Maionese

    @Column
    private Integer calorias;

    @Column
    private Boolean vegetariano = false;

    // ===============================
    // POLIMORFISMO: implementação do método abstrato
    // ===============================
    @Override
    public String getCategoria() {
        return "HAMBURGER";
    }

    // ===============================
    // GETTERS E SETTERS
    // ===============================

    public String getTipoCarne() {
        return tipoCarne;
    }

    public void setTipoCarne(String tipoCarne) {
        this.tipoCarne = tipoCarne;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Boolean getVegetariano() {
        return vegetariano;
    }

    public void setVegetariano(Boolean vegetariano) {
        this.vegetariano = vegetariano;
    }
}
