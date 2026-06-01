package com.stackburgers.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * =============================================
 * HERANÇA: Cliente herda de Usuario
 * =============================================
 * Cliente pode fazer pedidos.
 */
@Entity
@Table(name = "clientes")
@DiscriminatorValue("CLIENTE")
public class Cliente extends Usuario {

    @Column(length = 14)
    private String cpf;

    @Column(length = 15)
    private String telefone;

    @Column(length = 200)
    private String endereco;

    // Relacionamento: um cliente pode ter vários pedidos
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    /**
     * POLIMORFISMO: Cliente NÃO pode gerenciar produtos = false
     */
    @Override
    public boolean podeGerenciarProdutos() {
        return false;
    }

    // GETTERS E SETTERS
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}
