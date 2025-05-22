package org.example;

import org.example.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    private Sudoku sudoku;

    @BeforeEach
    void setUp() {
        sudoku = new Sudoku();
    }

    @Test
    void generarTablero() {

        assertDoesNotThrow(() -> sudoku.generarTablero("facil"));
    }

    @Test
    void esMovimientoValido() {
        // Movimiento válido al principio (celda vacía)
        assertTrue(sudoku.esMovimientoValido(0, 0, 5));

        // Colocar el número y luego intentar colocarlo de nuevo en misma fila
        sudoku.colocarNumero(0, 0, 5);
        assertFalse(sudoku.esMovimientoValido(0, 1, 5));
    }

    @Test
    void colocarNumero() {
        assertTrue(sudoku.colocarNumero(1, 1, 7));
        assertEquals(7, sudoku.getValor(1, 1));

        // Colocar número en conflicto en la misma fila
        assertFalse(sudoku.colocarNumero(1, 2, 7));
    }

    @Test
    void estaResuelto() {
        // Tablero vacío, claramente no resuelto
        assertFalse(sudoku.estaResuelto());

        // Llenar el tablero con una solución válida (para test simple)
        int[][] solucion = {
                {1,2,3,4,5,6,7,8,9},
                {4,5,6,7,8,9,1,2,3},
                {7,8,9,1,2,3,4,5,6},
                {2,3,4,5,6,7,8,9,1},
                {5,6,7,8,9,1,2,3,4},
                {8,9,1,2,3,4,5,6,7},
                {3,4,5,6,7,8,9,1,2},
                {6,7,8,9,1,2,3,4,5},
                {9,1,2,3,4,5,6,7,8}
        };

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                sudoku.colocarNumero(i, j, solucion[i][j]);

        assertTrue(sudoku.estaResuelto());
    }

    @Test
    void mostrarTablero() {

        sudoku.colocarNumero(0, 0, 1);
        assertDoesNotThrow(() -> sudoku.mostrarTablero());
    }

    @Test
    void getTablero() {
        int[][] tablero = sudoku.getTablero();
        assertNotNull(tablero);
        assertEquals(9, tablero.length);
        assertEquals(9, tablero[0].length);
    }

    @Test
    void getValor() {
        sudoku.colocarNumero(2, 3, 6);
        assertEquals(6, sudoku.getValor(2, 3));
    }

    @Test
    void setCeldaFija() {
        sudoku.setCeldaFija(4, 4, 8);
        assertEquals(8, sudoku.getValor(4, 4));
        assertTrue(sudoku.esCeldaFija(4, 4));
    }

    @Test
    void esCeldaFija() {
        assertFalse(sudoku.esCeldaFija(0, 0));
        sudoku.setCeldaFija(0, 0, 3);
        assertTrue(sudoku.esCeldaFija(0, 0));
    }
}
