package com.eduardokp.handson.exceptions;

public class AeroportoIataDuplicadoException extends IllegalArgumentException {
    public final static String MSG = "Código Iata já pertence ao registro de código: ";

    public AeroportoIataDuplicadoException(Long iata) {
        super(MSG + iata);
    }
}
