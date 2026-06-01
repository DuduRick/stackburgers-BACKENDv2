package com.stackburgers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTOs (Data Transfer Objects) são classes simples usadas para
 * trafegar dados entre o cliente (frontend) e o backend.
 *
 * Separar DTOs das Entidades é uma boa prática:
 * evita expor dados sensíveis e facilita validações.
 */
public class ProdutoDTO {

    // DTO para criar/editar Hamburger
    public static class HamburgerRequest {
        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        private String descricao;

        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "Preço deve ser positivo")
        private Double preco;

        @NotBlank(message = "Tipo de carne é obrigatório")
        private String tipoCarne;

        private String ingredientes;
        private Integer calorias;
        private Boolean vegetariano = false;
        private Boolean disponivel = true;

        // Getters e Setters
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public String getDescricao() { return descricao; }
        public void setDescricao(String descricao) { this.descricao = descricao; }
        public Double getPreco() { return preco; }
        public void setPreco(Double preco) { this.preco = preco; }
        public String getTipoCarne() { return tipoCarne; }
        public void setTipoCarne(String tipoCarne) { this.tipoCarne = tipoCarne; }
        public String getIngredientes() { return ingredientes; }
        public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; }
        public Integer getCalorias() { return calorias; }
        public void setCalorias(Integer calorias) { this.calorias = calorias; }
        public Boolean getVegetariano() { return vegetariano; }
        public void setVegetariano(Boolean vegetariano) { this.vegetariano = vegetariano; }
        public Boolean getDisponivel() { return disponivel; }
        public void setDisponivel(Boolean disponivel) { this.disponivel = disponivel; }
    }

    // DTO para criar/editar Bebida
    public static class BebidaRequest {
        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        private String descricao;

        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "Preço deve ser positivo")
        private Double preco;

        @NotBlank(message = "Tipo da bebida é obrigatório")
        private String tipo;

        private Integer volumeMl;
        private Boolean alcolica = false;
        private Boolean gelada = true;
        private Boolean disponivel = true;

        // Getters e Setters
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public String getDescricao() { return descricao; }
        public void setDescricao(String descricao) { this.descricao = descricao; }
        public Double getPreco() { return preco; }
        public void setPreco(Double preco) { this.preco = preco; }
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        public Integer getVolumeMl() { return volumeMl; }
        public void setVolumeMl(Integer volumeMl) { this.volumeMl = volumeMl; }
        public Boolean getAlcolica() { return alcolica; }
        public void setAlcolica(Boolean alcolica) { this.alcolica = alcolica; }
        public Boolean getGelada() { return gelada; }
        public void setGelada(Boolean gelada) { this.gelada = gelada; }
        public Boolean getDisponivel() { return disponivel; }
        public void setDisponivel(Boolean disponivel) { this.disponivel = disponivel; }
    }
}
