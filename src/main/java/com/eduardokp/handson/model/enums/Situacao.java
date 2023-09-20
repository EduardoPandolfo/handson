package com.eduardokp.handson.model.enums;

public enum Situacao {
    ATIVO("Ativo"),
    CANCELADO("Cancelado"),
    INATIVO("Inativo");

    private final String nome;

    private Situacao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
