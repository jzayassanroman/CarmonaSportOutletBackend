package com.example.carmonasportoutlet.enumerados;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoProducto {
    EXCELENTE,
    BUENO,
    MALO,
    MUY_MALO;
    @JsonValue
    public int toValue() {
        return this.ordinal();
    }
}
