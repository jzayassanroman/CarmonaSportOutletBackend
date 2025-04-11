package com.example.carmonasportoutlet.enumerados;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Estado {
    CANCELADO,
    EN_CURSO,
    ENVIADO,
    ENTREGADO;

    @JsonValue
    public int toValue() {
        return this.ordinal();
    }
}
