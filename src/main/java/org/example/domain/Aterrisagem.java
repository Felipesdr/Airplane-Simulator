package org.example.domain;

public class Aterrisagem {

    private Integer chegada;
    private Integer saida;
    private Aviao aviao;

    public Aterrisagem(Integer chegada, Integer saida, Aviao aviao) {
        this.chegada = chegada;
        this.saida = saida;
        this.aviao = aviao;
    }

    public Integer getChegada() {
        return chegada;
    }

    public void setChegada(Integer chegada) {
        this.chegada = chegada;
    }

    public Integer getSaida() {
        return saida;
    }

    public void setSaida(Integer saida) {
        this.saida = saida;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }


}
