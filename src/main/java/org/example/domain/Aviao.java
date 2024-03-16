package org.example.domain;

import org.example.util.RandomPicker;

import java.util.Objects;

public class Aviao {

    private Long idAviao;
    private Integer nivelCombustivel;

    private Integer tempoNaFila;

    public Aviao(Long idAviao){
        this.idAviao = idAviao;
        this.nivelCombustivel = RandomPicker.getRandomNumberNot0(20);
        this.tempoNaFila = 0;
    }

    public Long getIdAviao() {
        return idAviao;
    }

    public void setIdAviao(Long idAviao) {
        this.idAviao = idAviao;
    }

    public Integer getNivelCombustivel() {
        return nivelCombustivel;
    }

    public void setNivelCombustivel(Integer nivelCombustivel) {
        this.nivelCombustivel = nivelCombustivel;
    }

    public void consumirCombustivel(){

        nivelCombustivel --;
    }

    public Integer getTempoNaFila() {
        return tempoNaFila;
    }

    public void setTempoNaFila(Integer tempoNaFila) {
        this.tempoNaFila = tempoNaFila;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aviao aviao = (Aviao) o;
        return Objects.equals(idAviao, aviao.idAviao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAviao);
    }
}
