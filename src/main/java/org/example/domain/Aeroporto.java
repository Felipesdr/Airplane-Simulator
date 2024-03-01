package org.example.domain;

public class Aeroporto {

    private Pista pista1;

    private Pista pista2;

    private PistaEmergencia pistaEmergencia;

    public Aeroporto(Pista pista1, Pista pista2, PistaEmergencia pistaEmergencia) {
        this.pista1 = pista1;
        this.pista2 = pista2;
        this.pistaEmergencia = pistaEmergencia;
    }

    public Pista getPista1() {
        return pista1;
    }

    public void setPista1(Pista pista1) {
        this.pista1 = pista1;
    }

    public Pista getPista2() {
        return pista2;
    }

    public void setPista2(Pista pista2) {
        this.pista2 = pista2;
    }

    public PistaEmergencia getPistaEmergencia() {
        return pistaEmergencia;
    }

    public void setPistaEmergencia(PistaEmergencia pistaEmergencia) {
        this.pistaEmergencia = pistaEmergencia;
    }
}
