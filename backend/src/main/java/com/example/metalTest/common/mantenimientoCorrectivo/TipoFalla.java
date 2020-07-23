package com.example.metalTest.common.mantenimientoCorrectivo;

public enum TipoFalla {
    ELECTRICA((short) 1),
    MECANICA((short) 2);

    private short value;

    TipoFalla(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }
}
