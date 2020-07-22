package com.example.metalTest.common.ordenes;

public enum Estado {

    ACTIVO((short) 30),
    ELIMINADO((short) 80);

    private short value;

    Estado(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }
}
