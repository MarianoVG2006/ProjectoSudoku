package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class JuegoSudokuTest {

    @BeforeEach
    void setUp(){
        JuegoSudoku JuegoSudoku = new JuegoSudoku();
    }

    @Test
    void iniciar() {
        // Simula entrada del usuario
        String input = String.join(System.lineSeparator(),
                "facil",    // dificultad
                "8", "8", "9"  // fila, columna, valor
        );
        Scanner scanner = new Scanner(new StringReader(input));

        Sudoku sudoku = new Sudoku() {
            boolean resuelto = false;

            @Override
            public void generarTablero(String dificultad) {
                // Casi resuelto, solo falta (8,8)
                tablero = new int[][] {
                        {1, 2, 3, 4, 5, 6, 7, 8, 9},
                        {4, 5, 6, 7, 8, 9, 1, 2, 3},
                        {7, 8, 9, 1, 2, 3, 4, 5, 6},
                        {2, 3, 1, 5, 6, 4, 8, 9, 7},
                        {5, 6, 4, 8, 9, 7, 2, 3, 1},
                        {8, 9, 7, 2, 3, 1, 5, 6, 4},
                        {3, 1, 2, 6, 4, 5, 9, 7, 8},
                        {6, 4, 5, 9, 7, 8, 3, 1, 2},
                        {9, 7, 8, 3, 1, 2, 6, 4, 0} // falta (8,8)
                };
                celdasFijas = new boolean[9][9]; // Ninguna celda fija
            }

            @Override
            public boolean esCeldaFija(int fila, int columna) {
                return false;
            }

            @Override
            public boolean colocarNumero(int fila, int columna, int valor) {
                if (fila == 8 && columna == 8 && valor == 9) {
                    tablero[fila][columna] = valor;
                    resuelto = true;
                    return true;
                }
                return false;
            }

            @Override
            public boolean estaResuelto() {
                return resuelto;
            }
        };

        JuegoSudoku juego = new JuegoSudoku(scanner, sudoku);

    }
}
