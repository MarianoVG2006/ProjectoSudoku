package org.example;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneradorSudokuTest {

    Sudoku sudoku;

    @BeforeEach
    void setUp() {
        sudoku = new Sudoku();
    }

    @Test
    void generarFacilTableroConEntre51Y81CeldasLlenas() {
        GeneradorSudoku.generar(sudoku, "facil");
        int noVacias = contarCeldasNoVacias(sudoku);
        assertTrue(noVacias >= 51 && noVacias <= 81, "El tablero fácil debe tener entre 51 y 81 celdas no vacías");
    }

    @Test
    void generarMedioTableroConEntre41Y81CeldasLlenas() {
        GeneradorSudoku.generar(sudoku, "medio");
        int noVacias = contarCeldasNoVacias(sudoku);
        assertTrue(noVacias >= 41 && noVacias <= 81, "El tablero medio debe tener entre 41 y 81 celdas no vacías");
    }

    @Test
    void generarDificilTableroConEntre31Y81CeldasLlenas() {
        GeneradorSudoku.generar(sudoku, "dificil");
        int noVacias = contarCeldasNoVacias(sudoku);
        assertTrue(noVacias >= 31 && noVacias <= 81, "El tablero difícil debe tener entre 31 y 81 celdas no vacías");
    }

    @Test
    void resolverDebeResolverTableroVacio() {
        int[][] tablero = new int[9][9];
        boolean resuelto = GeneradorSudoku.resolver(tablero);
        assertTrue(resuelto, "El tablero vacío debe poder resolverse");

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                assertTrue(tablero[fila][col] >= 1 && tablero[fila][col] <= 9,
                        "Cada celda debe estar entre 1 y 9 después de resolver");
            }
        }
    }

    private int contarCeldasNoVacias(Sudoku sudoku) {
        int count = 0;
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (sudoku.getValor(fila, col) != 0) {
                    count++;
                }
            }
        }
        return count;
    }
}