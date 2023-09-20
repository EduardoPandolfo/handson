package com.eduardokp.handson.exceptions;

public class PassageiroInativoException extends IllegalArgumentException {

    public final static String MSG = "Impossível alterar cadastro de passageiro inativo.";

    public PassageiroInativoException() {
        super(MSG);
    }
}
