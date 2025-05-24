package org.example;

public class SudokuException extends RuntimeException {
    public SudokuException(String message) {
        super("Error de sudoku" + message);
    }
}
