package com.example.metalTest.common.ordenes;

public enum Tipo {

    PREVENTIVO((short) 1),
    CORRECTIVO((short) 2),
    MEJORATIVO((short) 3);

    private short value;

    Tipo(short value){
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

}
