package com.example.metalTest.apiError.controller.response;

public class ValidateFieldResponse {
    private String error;
    private String campo;
    private String valorRechazado;

    public ValidateFieldResponse(String error, String campo, String valorRechazado) {
        this.error = error;
        this.campo = campo;
        this.valorRechazado = valorRechazado;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValorRechazado() {
        return valorRechazado;
    }

    public void setValorRechazado(String valorRechazado) {
        this.valorRechazado = valorRechazado;
    }

}
