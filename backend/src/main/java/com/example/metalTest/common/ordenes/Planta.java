package com.example.metalTest.common.ordenes;

public enum Planta {

    BAVIO((short) 1),
    PLANTA50((short)2);

    private short value;

    Planta(short value){
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

}
