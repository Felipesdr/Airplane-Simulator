package org.example.domain;

import org.example.util.RandomPicker;

public class Aviao {

    private Long idAviao;
    private Integer nivelCombustivel;

    public Aviao(Long idAviao){
        this.idAviao = idAviao;
        this.nivelCombustivel = RandomPicker.getRandomNumberNot0(12);
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
}
