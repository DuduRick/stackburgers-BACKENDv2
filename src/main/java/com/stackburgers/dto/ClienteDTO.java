package com.stackburgers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteDTO {

    // Request para cadastro de cliente
    public static class ClienteRequest {
        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        @Email(message = "E-mail inválido")
        @NotBlank(message = "E-mail é obrigatório")
        private String email;

        @NotBlank(message = "Senha é obrigatória")
        private String senha;

        private String cpf;
        private String telefone;
        @NotBlank(message = "Endereço é obrigatório")
        private String endereco;

        // Getters e Setters
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
        public String getCpf() { return cpf; }
        public void setCpf(String cpf) { this.cpf = cpf; }
        public String getTelefone() { return telefone; }
        public void setTelefone(String telefone) { this.telefone = telefone; }
        public String getEndereco() { return endereco; }
        public void setEndereco(String endereco) { this.endereco = endereco; }
    }

    // Response - dados do cliente para retornar ao frontend (sem senha)
    public static class ClienteResponse {
        private Long id;
        private String nome;
        private String email;
        private String cpf;
        private String telefone;
        private String endereco;
        private Boolean ativo;

        public ClienteResponse(Long id, String nome, String email, String cpf,
                               String telefone, String endereco, Boolean ativo) {
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.cpf = cpf;
            this.telefone = telefone;
            this.endereco = endereco;
            this.ativo = ativo;
        }

        public Long getId() { return id; }
        public String getNome() { return nome; }
        public String getEmail() { return email; }
        public String getCpf() { return cpf; }
        public String getTelefone() { return telefone; }
        public String getEndereco() { return endereco; }
        public Boolean getAtivo() { return ativo; }
    }
}
