package com.luminary.apieden.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOrderEnum {
    PENDENTE(1, "pendente", "Aguardando pagamento"),
    PROCESSANDO(2, "processando", "Pedido em processamento"),
    ENVIADO(3, "enviado", "Pedido entregue ao cliente"),
    ENTREGUE(4, "entregue", "Pedido entregue ao cliente"),
    CANCELADO(5, "cancelado", "Pedido cancelado pelo cliente ou pelo sistema");

    private final long id;
    private final String status;
    private final String description;
}
