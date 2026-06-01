package com.stackburgers.dto;

import com.stackburgers.model.StatusPedido;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {

    // Item do pedido na requisição
    public static class ItemPedidoRequest {
        @NotNull(message = "ID do produto é obrigatório")
        private Long produtoId;

        @Min(value = 1, message = "Quantidade mínima é 1")
        private Integer quantidade = 1;

        public Long getProdutoId() { return produtoId; }
        public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }
        public Integer getQuantidade() { return quantidade; }
        public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    }

    // Criação de pedido
    public static class PedidoRequest {
        @NotNull(message = "ID do cliente é obrigatório")
        private Long clienteId;

        private List<ItemPedidoRequest> itens;
        private String observacao;

        public Long getClienteId() { return clienteId; }
        public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
        public List<ItemPedidoRequest> getItens() { return itens; }
        public void setItens(List<ItemPedidoRequest> itens) { this.itens = itens; }
        public String getObservacao() { return observacao; }
        public void setObservacao(String observacao) { this.observacao = observacao; }
    }

    // Atualização de status
    public static class AtualizarStatusRequest {
        @NotNull(message = "Status é obrigatório")
        private StatusPedido status;

        public StatusPedido getStatus() { return status; }
        public void setStatus(StatusPedido status) { this.status = status; }
    }
}
