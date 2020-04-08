package com.example.metalTest.tarea.controller.request;

public class TareaRequest {

    private String descripcion;

    private int duracion;

    private int usuario_cod;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getUsuario_cod() {
        return usuario_cod;
    }

    public void setUsuario_cod(int usuario_cod) {
        this.usuario_cod = usuario_cod;
    }
}
