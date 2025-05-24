package org.example;

public class MovimientoInvalidoException extends SudokuException {
    public MovimientoInvalidoException(String message) {
        super("Error" +message+ ". Verifica las reglas del Sudoku.");
    }
}
