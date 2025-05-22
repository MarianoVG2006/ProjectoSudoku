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
                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
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
