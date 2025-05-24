package org.example;

public class EntradasFueraDeRangoException extends SudokuException {
    public EntradasFueraDeRangoException(int valor) {
        super("El valor ingresado (" + valor + ") está fuera del rango permitido (1-9).message");
    }
}
