package com.example.metalTest.common.repuesto;

public enum TipoMovimiento {

    ENTRADA((short) 1),
    SALIDA((short) 2);

    private short value;

    TipoMovimiento(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

}
