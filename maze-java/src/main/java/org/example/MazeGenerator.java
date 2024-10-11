package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MazeGenerator {
    private static final int WALL = 0;
    private static final int PATH = 1;
    private static final int ROWS = 10;
    private static final int COLS = 10;
    private int[][] maze;
    private Random random = new Random();
    private int borderPathCount = 0; // Contador de caminhos nas bordas

    public MazeGenerator() {
        maze = new int[ROWS][COLS];
        generateMaze();
        limitBorderPaths(); // Corrige para garantir apenas 2 caminhos nas bordas
        saveMazeToCSV("maze.csv");
    }

    private void generateMaze() {
        // Inicializa o labirinto com paredes
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                maze[i][j] = WALL;
            }
        }
        maze[0][0] = PATH; // Entrada na posição [0,0]
        borderPathCount++; // Conta a entrada como caminho de borda
        carvePath(0, 0); // Inicia a escavação
    }

    private void carvePath(int x, int y) {
        int[] dx = {0, 1, 0, -1}; // Movimentos possíveis: direita, baixo, esquerda, cima
        int[] dy = {1, 0, -1, 0};

        // Embaralha as direções para garantir aleatoriedade
        for (int i = 0; i < dx.length; i++) {
            int randIndex = random.nextInt(dx.length);
            int temp = dx[i];
            dx[i] = dx[randIndex];
            dx[randIndex] = temp;

            temp = dy[i];
            dy[i] = dy[randIndex];
            dy[randIndex] = temp;
        }

        // Tenta escavar em cada direção
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * 2;
            int ny = y + dy[i] * 2;

            // Verifica se a nova posição está dentro dos limites e se é uma parede
            if (nx >= 0 && ny >= 0 && nx < ROWS && ny < COLS && maze[nx][ny] == WALL) {
                maze[x + dx[i]][y + dy[i]] = PATH; // Cria o caminho
                maze[nx][ny] = PATH; // Marca a nova célula como caminho
                carvePath(nx, ny); // Recursivamente escava a próxima célula
            }
        }
    }

    // Garante que as bordas tenham apenas uma entrada e uma saída
    private void limitBorderPaths() {
        // Conta e coleta as posições dos caminhos nas bordas
        int[][] borderPaths = new int[ROWS * 2 + COLS * 2][2];
        int count = 0;

        for (int i = 0; i < ROWS; i++) {
            if (maze[i][0] == PATH) { // Bordas da coluna esquerda
                borderPaths[count][0] = i;
                borderPaths[count][1] = 0;
                count++;
            }
            if (maze[i][COLS - 1] == PATH) { // Bordas da coluna direita
                borderPaths[count][0] = i;
                borderPaths[count][1] = COLS - 1;
                count++;
            }
        }
        for (int j = 0; j < COLS; j++) {
            if (maze[0][j] == PATH) { // Bordas da linha superior
                borderPaths[count][0] = 0;
                borderPaths[count][1] = j;
                count++;
            }
            if (maze[ROWS - 1][j] == PATH) { // Bordas da linha inferior
                borderPaths[count][0] = ROWS - 1;
                borderPaths[count][1] = j;
                count++;
            }
        }

        // Remove caminhos extras nas bordas, mantendo apenas dois
        while (count > 2) {
            int index = random.nextInt(count); // Escolhe um caminho de borda aleatório para remover
            int row = borderPaths[index][0];
            int col = borderPaths[index][1];
            if (!(row == 0 && col == 0)) { // Garante que não remova a entrada
                maze[row][col] = WALL;
                borderPaths[index] = borderPaths[--count]; // Remove o caminho da lista
            }
        }
    }

    // Salva o labirinto em um arquivo CSV
    private void saveMazeToCSV(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    writer.append(String.valueOf(maze[i][j]));
                    if (j < COLS - 1) writer.append(",");
                }
                writer.append("\n");
            }
            System.out.println("Labirinto salvo com sucesso em " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}