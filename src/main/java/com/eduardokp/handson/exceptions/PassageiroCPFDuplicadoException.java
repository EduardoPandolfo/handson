package com.eduardokp.handson.exceptions;

public class PassageiroCPFDuplicadoException extends IllegalArgumentException {

    public final static String MSG = "Já existe passageiro cadastrado com o mesmo CPF.";

    public PassageiroCPFDuplicadoException() {
        super(MSG);
    }
}
