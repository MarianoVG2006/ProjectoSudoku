package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

    public class SudokuGUI extends JFrame implements SudokuGUIInterface {



        private Sudoku sudoku;
        private JTextField[][] celdas = new JTextField[9][9];
        private JComboBox<String> dificultadCombo;
        private JButton verificarBtn;

        public SudokuGUI() {
            setTitle("Sudoku - Java Swing");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            sudoku = new Sudoku();

            JPanel panelTablero = new JPanel(new GridLayout(9, 9));
            panelTablero.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            Font fuente = new Font("SansSerif", Font.BOLD, 18);

            // Crear celdas
            for (int fila = 0; fila < 9; fila++) {
                for (int col = 0; col < 9; col++) {
                    JTextField celda = new JTextField();
                    celda.setHorizontalAlignment(JTextField.CENTER);
                    celda.setFont(fuente);
                    celda.setDocument(new JTextFieldLimit(1));

                    int top = (fila % 3 == 0) ? 2 : 1;
                    int left = (col % 3 == 0) ? 2 : 1;
                    int bottom = (fila == 8) ? 2 : 1;
                    int right = (col == 8) ? 2 : 1;

                    celda.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.RED));

                    // Listener para verificar si el número es válido mientras se escribe
                    final int f = fila, c = col;
                    celda.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                        public void insertUpdate(javax.swing.event.DocumentEvent e) { verificarCelda(); }
                        public void removeUpdate(javax.swing.event.DocumentEvent e) { verificarCelda(); }
                        public void changedUpdate(javax.swing.event.DocumentEvent e) { verificarCelda(); }

                        private void verificarCelda() {
                            String texto = celda.getText();
                            if (texto.isEmpty()) {
                                celda.setBackground(Color.WHITE);
                                return;
                            }
                            try {
                                int valor = Integer.parseInt(texto);
                                if (sudoku.esMovimientoValido(f, c, valor)) {
                                    celda.setBackground(Color.GREEN);
                                } else {
                                    celda.setBackground(Color.PINK);
                                }
                            } catch (NumberFormatException ex) {
                                celda.setBackground(Color.PINK);
                            }
                        }
                    });

                    celdas[fila][col] = celda;
                    panelTablero.add(celda);

                }
            }

            // Panel superior con selección de dificultad
            JPanel panelSuperior = new JPanel();
            dificultadCombo = new JComboBox<>(new String[]{"facil", "medio", "dificil"});
            JButton generarBtn = new JButton("Generar Tablero");
            generarBtn.addActionListener(e -> generarTablero());
            panelSuperior.add(new JLabel("Dificultad: "));
            panelSuperior.add(dificultadCombo);
            panelSuperior.add(generarBtn);

            // Botón de verificación
            verificarBtn = new JButton("Verificar");
            verificarBtn.addActionListener(e -> verificarJuego());

            add(panelSuperior, BorderLayout.NORTH);
            add(panelTablero, BorderLayout.CENTER);
            add(verificarBtn, BorderLayout.SOUTH);

            setSize(500, 550);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        @Override
        public void generarTablero() {
            sudoku = new Sudoku();
            sudoku.generarTablero(dificultadCombo.getSelectedItem().toString());

            int[][] tablero = sudoku.getTablero();
            for (int fila = 0; fila < 9; fila++) {
                for (int col = 0; col < 9; col++) {
                    JTextField celda = celdas[fila][col];
                    if (tablero[fila][col] != 0) {
                        celda.setText(String.valueOf(tablero[fila][col]));
                        celda.setEditable(false);
                        celda.setBackground(Color.LIGHT_GRAY);
                    } else {
                        celda.setText("");
                        celda.setEditable(true);
                        celda.setBackground(Color.WHITE);
                    }
                }
            }
        }

        @Override
        public void verificarJuego() {
            for (int fila = 0; fila < 9; fila++) {
                for (int col = 0; col < 9; col++) {
                    if (sudoku.getTablero()[fila][col] == 0) {
                        String texto = celdas[fila][col].getText();
                        if (!texto.isEmpty()) {
                            int valor = Integer.parseInt(texto);
                            if (!sudoku.esMovimientoValido(fila, col, valor)) {
                                JOptionPane.showMessageDialog(this,
                                        "Error en fila " + fila + ", columna " + col + " con valor " + valor,
                                        "Movimiento inválido", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            sudoku.colocarNumero(fila, col, valor);
                        }
                    }
                }
            }

            if (sudoku.estaResuelto()) {
                JOptionPane.showMessageDialog(this, "🎉 ¡Felicidades, resolviste el Sudoku!", "¡Completado!",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Aún hay errores o casillas vacías.", "No resuelto",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        // Límite de caracteres en JTextField, CLASE ANIDADAS
        class JTextFieldLimit extends javax.swing.text.PlainDocument {
            private int limit;

            JTextFieldLimit(int limit) {
                this.limit = limit;
            }

            @Override
            public void insertString(int offset, String str, javax.swing.text.AttributeSet attr)
                    throws javax.swing.text.BadLocationException {
                if (str == null || getLength() >= limit) return;
                if (!str.matches("[1-9]")) return;
                super.insertString(offset, str, attr);
            }
        }


    }


