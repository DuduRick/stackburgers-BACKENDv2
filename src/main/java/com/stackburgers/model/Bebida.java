package com.stackburgers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * =============================================
 * CONCEITO DE POO: HERANÇA + POLIMORFISMO
 * =============================================
 * Bebida também herda de Produto.
 * Tem seus próprios atributos: volume, tipo, etc.
 * Polimorfismo: getCategoria() retorna "BEBIDA".
 */
@Entity
@Table(name = "bebidas")
@DiscriminatorValue("BEBIDA")
public class Bebida extends Produto {

    // Atributos específicos de Bebida
    @NotBlank(message = "Tipo da bebida é obrigatório")
    @Column(nullable = false)
    private String tipo; // Ex: Refrigerante, Suco, Água, Cerveja

    @Column
    private Integer volumeMl; // Volume em mililitros

    @Column
    private Boolean alcolica = false;

    @Column
    private Boolean gelada = true;

    // ===============================
    // POLIMORFISMO: implementação do método abstrato
    // ===============================
    @Override
    public String getCategoria() {
        return "BEBIDA";
    }

    // ===============================
    // GETTERS E SETTERS
    // ===============================

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getVolumeMl() {
        return volumeMl;
    }

    public void setVolumeMl(Integer volumeMl) {
        this.volumeMl = volumeMl;
    }

    public Boolean getAlcolica() {
        return alcolica;
    }

    public void setAlcolica(Boolean alcolica) {
        this.alcolica = alcolica;
    }

    public Boolean getGelada() {
        return gelada;
    }

    public void setGelada(Boolean gelada) {
        this.gelada = gelada;
    }
}
