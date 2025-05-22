package org.example;

import java.util.Scanner;

public class JuegoSudoku implements JuegoSudokuInterface {
    private Sudoku sudoku;
    private Scanner scanner;

    public JuegoSudoku() {
        sudoku = new Sudoku();
        scanner = new Scanner(System.in);
    }

    @Override
    public void iniciar() {
        System.out.println("Bienvenido al Sudoku en Java!");
        System.out.print("Selecciona dificultad (facil/medio/dificil): ");
        String dificultad = scanner.nextLine().toLowerCase();

        sudoku.generarTablero(dificultad);

        while (!sudoku.estaResuelto()) {
            sudoku.mostrarTablero();
            System.out.print("Introduce fila (0-8): ");
            int fila = scanner.nextInt();
            System.out.print("Introduce columna (0-8): ");
            int columna = scanner.nextInt();
            System.out.print("Introduce valor (1-9): ");
            int valor = scanner.nextInt();

            if (!sudoku.colocarNumero(fila, columna, valor)) {
                System.out.println("\u274C Movimiento inv\u00e1lido, intenta de nuevo.");
            } else {
                System.out.println("\u2705 Movimiento realizado.");
            }
        }

        System.out.println("\ud83c\udf89 \u00a1Felicidades! Has resuelto el Sudoku:");
        sudoku.mostrarTablero();
    }
}

