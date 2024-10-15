package org.example.view;
import org.example.model.Backtracker;
import org.example.model.Coordinate;
import org.example.model.Maze;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MazeView extends JFrame {
    ArrayList<ArrayList<Integer>> maze;
    private JPanel[][] cells;
    private int rows, cols;
    private int playerRow, playerCol;
    Backtracker backtracker;

    public MazeView(Maze maze) {
        this.maze = maze.getMaze();
        this.rows = maze.getWidth();
        this.cols = maze.getHeight();
        this.cells = new JPanel[rows][cols];
    }

    public void gameWindow(int size) {
        setTitle("Labirinto IH HII");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(size, size));

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
        initPlayer();
        setSize(400, 400); // Ajusta o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true); // Torna a janela visível
    }

    public void initPlayer() {
        playerRow = 0;
        playerCol = 0;
        cells[playerRow][playerCol].setBackground(Color.RED);
    }


    public JPanel[][] getCells() {
        return cells;
    }
}