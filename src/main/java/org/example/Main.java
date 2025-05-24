package org.example;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new SudokuGUI();
        }); // <-- paréntesis y punto y coma añadidos aquí
    }
}