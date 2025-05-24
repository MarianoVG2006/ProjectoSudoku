package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGUITest {

    SudokuGUI gui;

    @BeforeEach
    void setUp() {
        gui = new SudokuGUI();
    }

    @Test
    void generarTablero() {
        gui.generarTablero();
        boolean alMenosUnaCeldaLlena = false;

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                String texto = gui.celdas[fila][col].getText();
                if (!texto.isEmpty()) {
                    alMenosUnaCeldaLlena = true;
                    break;
                }
            }
        }

        assertTrue(alMenosUnaCeldaLlena, "El tablero generado debe tener al menos una celda con número.");
    }

    @Test
    void verificarJuego() {
        int[][] solucionValida = {
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

        // Llenar celdas manualmente como si el usuario las hubiera escrito
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                gui.celdas[fila][col].setText(String.valueOf(solucionValida[fila][col]));
            }
        }

        gui.verificarJuego();

        // Comprobamos que el sudoku esté efectivamente resuelto
        assertTrue(gui.sudoku.estaResuelto(), "El Sudoku debería estar resuelto correctamente.");
    }
}
