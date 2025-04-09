package com.example.carmonasportoutlet.enumerados;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Entrega {
    RECOGIDA,
    ENVIO_DOMICILIO;

    @JsonValue
    public int toValue() {
        return this.ordinal();
    }
}
