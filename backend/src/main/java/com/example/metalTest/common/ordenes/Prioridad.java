package com.example.metalTest.common.ordenes;

public enum Prioridad {

    URGENTE((short) 1),
    PRODUCCION_PARADA((short)2),
    EN_LO_INMEDIATO((short) 3),
    FALLA_INMINENTE((short) 4),
    A_PROGRAMAR((short) 5),
    REQUIERE_PARAR_LA_PRODUCCION((short) 6),
    N_E((short) 7);
    private short value;

    Prioridad(short value){
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

}
