package org.example.view;

import javax.swing.*;
import java.awt.*;

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

    public void atualizarPosicaoJogador(int playerRow, int playerCol) {
        cells[playerRow][playerCol].setBackground(Color.RED);
    }

    public void limparPosicaoJogador(int playerRow, int playerCol) {
        cells[playerRow][playerCol].setBackground(Color.WHITE);
    }
}
