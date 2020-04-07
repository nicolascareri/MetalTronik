package com.example.metalTest.usuario.controller.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class UsuarioRequest {
    private String nombre;
    private String apellido;
    private int dni;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}
