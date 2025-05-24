package org.example;

import java.util.Random;

public class GeneradorSudoku {

    public static void generar(Sudoku sudoku, String dificultad) {
        int[][] tablero = new int[9][9];
        resolver(tablero);

        int celdasARetirar = switch (dificultad.toLowerCase()) {
            case "facil" -> 30;
            case "medio" -> 40;
            case "dificil" -> 50;
            default -> 40;
        };

        Random rand = new Random();
        while (celdasARetirar > 0) {
            int fila = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (tablero[fila][col] != 0) {
                tablero[fila][col] = 0;
                celdasARetirar--;
            }
        }

        // Fijar las celdas no vacías como celdas fijas
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j] != 0) {
                    try {
                        sudoku.setCeldaFija(i, j, tablero[i][j]);
                    } catch (SudokuException e) {
                        throw new RuntimeException("Error al fijar la celda (" + i + ", " + j + "): " + e.getMessage(), e);
                    }
                }
            }
        }
    }

    public static boolean resolver(int[][] tablero) {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (tablero[fila][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (esSeguro(tablero, fila, col, num)) {
                            tablero[fila][col] = num;
                            if (resolver(tablero)) return true;
                            tablero[fila][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean esSeguro(int[][] tablero, int fila, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == num || tablero[i][col] == num) return false;
        }

        int startRow = (fila / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (tablero[i][j] == num) return false;
            }
        }

        return true;
    }
}
