package org.example.view;
import org.example.model.Backtracker;
import org.example.model.Coordinate;
import org.example.model.Maze;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MazeView extends JFrame {
    private JPanel[][] cells;
    private int rows, cols;
    private int playerRow, playerCol;
    Backtracker backtracker;

    public MazeView(Maze maze) {
        this.rows = maze.getWidth();
        this.cols = maze.getHeight();
        this.cells = new JPanel[rows][cols];
    }

    public void gameWindow(ArrayList<ArrayList<Integer>> maze) {
        setTitle("Labirinto IH HII");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 10));

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