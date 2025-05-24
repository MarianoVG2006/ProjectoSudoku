package org.example;

public class MovimientoInvalidoException extends SudokuException {
    public MovimientoInvalidoException(String message) {
        super("Movimiento invalido " +message+ ". Verifica las reglas del Sudoku.");
    }
}
