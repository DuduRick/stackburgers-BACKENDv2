package com.stackburgers.model;

/**
 * Status possíveis de um pedido.
 * O pedido avança pelos status em sequência.
 */
public enum StatusPedido {
    AGUARDANDO,    // Pedido criado, aguardando confirmação
    CONFIRMADO,    // Pedido confirmado pela lanchonete
    EM_PREPARO,    // Sendo preparado na cozinha
    PRONTO,        // Pronto para retirada/entrega
    ENTREGUE,      // Entregue ao cliente
    CANCELADO      // Cancelado
}
