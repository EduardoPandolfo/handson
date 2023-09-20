package com.eduardokp.handson.model.enums;

public enum Classe {
    A("Classe A"),
    B("Classe B"),
    C("Classe C");

    private final String nome;

    private Classe(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
