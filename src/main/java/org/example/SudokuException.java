package org.example;

public class SudokuException extends Exception {
    public SudokuException(String message) {
        super("Error de sudoku " + message);
    }
}
