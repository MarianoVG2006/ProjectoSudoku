package org.example;

public interface SudokuInterface {
    void generarTablero(String dificultad);
    boolean esMovimientoValido(int fila, int columna, int valor);
    boolean colocarNumero(int fila, int columna, int valor);
    boolean estaResuelto();
    void mostrarTablero();
    int[][] getTablero();
}
