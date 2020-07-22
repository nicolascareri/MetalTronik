package com.example.metalTest.common.repuesto;

public enum TipoRepuesto {
    COMUN((short) 1),
    CRITICO((short) 2),
    CONSUMIBLE((short) 3);

    private short value;

    TipoRepuesto(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }
}
