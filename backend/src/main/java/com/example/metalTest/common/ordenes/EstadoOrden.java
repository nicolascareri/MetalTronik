package com.example.metalTest.common.ordenes;

public enum EstadoOrden {

    PENDIENTE((short) 1),
    OK((short) 2);

    private short value;

    EstadoOrden(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

}
