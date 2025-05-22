package org.example;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class JuegoSudoku implements JuegoSudokuInterface {
    protected Sudoku sudoku;
    private final Scanner scanner;

    public JuegoSudoku() {
        this(new Scanner(System.in), new Sudoku());
    }

    public JuegoSudoku(Scanner scanner, Sudoku sudoku) {
        this.scanner = scanner;
        this.sudoku = sudoku;
    }

    @Override
    public void iniciar() {
        System.out.println("Bienvenido al Sudoku en Java!");
        System.out.print("Selecciona dificultad (facil/medio/dificil): ");

        String dificultad;
        try {
            if (!scanner.hasNextLine()) {
                System.out.println("❌ No se proporcionó dificultad.");
                return;
            }
            dificultad = scanner.nextLine().trim().toLowerCase();
        } catch (NoSuchElementException e) {
            System.out.println("❌ No se proporcionó dificultad.");
            return;
        }

        sudoku.generarTablero(dificultad);

        while (!sudoku.estaResuelto()) {
            sudoku.mostrarTablero();

            try {
                System.out.print("Introduce fila (0-8): ");
                if (!scanner.hasNextInt()) break;
                int fila = scanner.nextInt();

                System.out.print("Introduce columna (0-8): ");
                if (!scanner.hasNextInt()) break;
                int columna = scanner.nextInt();

                System.out.print("Introduce valor (1-9): ");
                if (!scanner.hasNextInt()) break;
                int valor = scanner.nextInt();

                scanner.nextLine(); // Limpia la línea pendiente tras los nextInt

                if (!sudoku.colocarNumero(fila, columna, valor)) {
                    System.out.println("❌ Movimiento inválido, intenta de nuevo.");
                } else {
                    System.out.println("✅ Movimiento realizado.");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠️ Entrada inválida. Finalizando juego.");
                scanner.nextLine(); // limpia entrada errónea
                break;
            } catch (NoSuchElementException e) {
                System.out.println("⚠️ No hay más entradas. Finalizando juego.");
                break;
            }
        }

        if (sudoku.estaResuelto()) {
            System.out.println("🎉 ¡Felicidades! Has resuelto el Sudoku:");
            sudoku.mostrarTablero();
        } else {
            System.out.println("⏹️ Juego interrumpido.");
        }
    }
}
