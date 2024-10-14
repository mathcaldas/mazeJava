package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MazeView extends JFrame {
    private JPanel[][] cells;

    public MazeView(int[][] labirinto) {
        cells = new JPanel[labirinto.length][labirinto[0].length];
        initUI(labirinto);
    }

    private void initUI(int[][] labirinto) {
        setTitle("Labirinto com Animação");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(labirinto.length, labirinto[0].length));

        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto[i].length; j++) {
                cells[i][j] = new JPanel();
                cells[i][j].setBackground(labirinto[i][j] == 0 ? Color.BLACK : Color.WHITE);
                add(cells[i][j]);
            }
        }

        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new JPanel();
                if (maze.get(i).get(j) == 0) {
                    cells[i][j].setBackground(Color.BLACK); // Paredes são pretas
                } else {
                    cells[i][j].setBackground(Color.WHITE); // Passagens são brancas
                }
                add(cells[i][j]);
            }
        }
        setSize(400, 400); // Ajusta o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true); // Torna a janela visível
    }

    public JPanel[][] getCells() {
        return cells;
    }

    public void initAnimation() {
        playerRow = 0;
        playerCol = 0;
        cells[playerRow][playerCol].setBackground(Color.RED);
        Timer timer = new Timer(250, e -> movePlayer());
        timer.start();
    }

    public void movePlayer() {
        JPanel[][] cells = getCells();

        for (Coordinate coord : backtracker.getPath()) {
            int i = coord.getX();
            int j = coord.getY();
            cells[i][j].setBackground(Color.RED);
        }
    }
}