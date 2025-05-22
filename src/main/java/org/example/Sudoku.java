package org.example;

public class Sudoku implements SudokuInterface {
    protected int[][] tablero;
    protected boolean[][] celdasFijas;

    public Sudoku() {
        tablero = new int[9][9];
        celdasFijas = new boolean[9][9];
    }

    @Override
    public void generarTablero(String dificultad) {
        GeneradorSudoku.generar(this, dificultad);
    }

    @Override
    public boolean esMovimientoValido(int fila, int columna, int valor) {
        if (celdasFijas[fila][columna]) return false;

        int original = tablero[fila][columna];
        tablero[fila][columna] = 0;

        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == valor || tablero[i][columna] == valor) {
                tablero[fila][columna] = original;
                return false;
            }
        }

        int bloqueFila = (fila / 3) * 3;
        int bloqueCol = (columna / 3) * 3;
        for (int i = bloqueFila; i < bloqueFila + 3; i++) {
            for (int j = bloqueCol; j < bloqueCol + 3; j++) {
                if (tablero[i][j] == valor) {
                    tablero[fila][columna] = original;
                    return false;
                }
            }
        }

        tablero[fila][columna] = original;
        return true;
    }

    @Override
    public boolean colocarNumero(int fila, int columna, int valor) {
        if (esMovimientoValido(fila, columna, valor)) {
            tablero[fila][columna] = valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean estaResuelto() {
        // Verifica filas y columnas
        for (int i = 0; i < 9; i++) {
            boolean[] filaCheck = new boolean[9];
            boolean[] colCheck = new boolean[9];
            for (int j = 0; j < 9; j++) {
                int numFila = tablero[i][j];
                int numCol = tablero[j][i];

                if (numFila == 0 || numCol == 0) return false;

                if (filaCheck[numFila - 1] || colCheck[numCol - 1]) return false;

                filaCheck[numFila - 1] = true;
                colCheck[numCol - 1] = true;
            }
        }

        // Verifica bloques 3x3
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                boolean[] blockCheck = new boolean[9];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int val = tablero[blockRow * 3 + i][blockCol * 3 + j];
                        if (val == 0) return false;
                        if (blockCheck[val - 1]) return false;
                        blockCheck[val - 1] = true;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void mostrarTablero() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("------+-------+------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("| ");
                System.out.print((tablero[i][j] == 0 ? "." : tablero[i][j]) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public int[][] getTablero() {
        return tablero;
    }

    public int getValor(int fila, int columna) {
        return tablero[fila][columna];
    }


    public void setCeldaFija(int fila, int columna, int valor) {
        tablero[fila][columna] = valor;
        celdasFijas[fila][columna] = true;
    }

    public boolean esCeldaFija(int fila, int columna) {
        return celdasFijas[fila][columna];
    }
}