package org.example;

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
        assertTrue(sudoku.esMovimientoValido(0, 0, 5));
        try {
            sudoku.colocarNumero(0, 0, 5);
        } catch (SudokuException e) {
            fail("No se esperaba excepción: " + e.getMessage());
        }
        assertFalse(sudoku.esMovimientoValido(0, 1, 5));
    }

    @Test
    void colocarNumero() {
        try {
            // Movimiento válido
            assertTrue(sudoku.colocarNumero(1, 1, 7));
            assertEquals(7, sudoku.getValor(1, 1));
        } catch (SudokuException e) {
            fail("No se esperaba excepción al colocar un número válido: " + e.getMessage());
        }

        // Movimiento inválido: conflicto con 7 en la misma fila
        SudokuException ex = assertThrows(SudokuException.class, () -> {
            sudoku.colocarNumero(1, 2, 7);
        });
        assertTrue(ex.getMessage().contains("No se puede colocar el número"));
    }

    @Test
    void estaResuelto() {
        assertFalse(sudoku.estaResuelto());

        int[][] solucion = {
                {1,2,3,4,5,6,7,8,9},
                {4,5,6,7,8,9,1,2,3},
                {7,8,9,1,2,3,4,5,6},
                {2,1,4,3,6,5,8,9,7},
                {3,6,5,8,9,7,2,1,4},
                {8,9,7,2,1,4,3,6,5},
                {5,3,1,6,4,2,9,7,8},
                {6,4,2,9,7,8,5,3,1},
                {9,7,8,5,3,1,6,4,2}
        };

        try {
            for (int i = 0; i < 9; i++)
                for (int j = 0; j < 9; j++)
                    sudoku.colocarNumero(i, j, solucion[i][j]);
        } catch (SudokuException e) {
            fail("Excepción inesperada al rellenar el tablero: " + e.getMessage());
        }

        assertTrue(sudoku.estaResuelto());
    }

    @Test
    void mostrarTablero() {
        try {
            sudoku.colocarNumero(0, 0, 1);
        } catch (SudokuException e) {
            fail("No se esperaba excepción: " + e.getMessage());
        }
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
        try {
            sudoku.colocarNumero(2, 3, 6);
        } catch (SudokuException e) {
            fail("No se esperaba excepción: " + e.getMessage());
        }
        assertEquals(6, sudoku.getValor(2, 3));
    }


    @Test
    void setCeldaFija() {
        try {
            boolean resultado = sudoku.esMovimientoValido(4, 4, 8);
            assertTrue(resultado, "El movimiento debería ser válido antes de fijar la celda.");
            sudoku.setCeldaFija(4, 4, 8);
            assertEquals(8, sudoku.getValor(4, 4));
            assertTrue(sudoku.esCeldaFija(4, 4));
        } catch (SudokuException e) {
            fail("No se esperaba excepción al fijar celda: " + e.getMessage());
        }
    }


    @Test
    void esCeldaFija() {
        assertFalse(sudoku.esCeldaFija(0, 0));
        try {
            sudoku.setCeldaFija(0, 0, 3);
        } catch (SudokuException e) {
            fail("No se esperaba excepción al fijar celda: " + e.getMessage());
        }
        assertTrue(sudoku.esCeldaFija(0, 0));
    }
}
