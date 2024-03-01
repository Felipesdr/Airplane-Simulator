package org.example;

import org.example.domain.Aviao;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Aviao aviao = new Aviao(1L);

        System.out.println(aviao.getNivelCombustivel());

    }
}