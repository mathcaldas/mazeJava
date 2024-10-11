package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MazeGenerator {
    private int rows;
    private int cols;
    private int[][] maze;
    private boolean[][] visited;
    private int entranceRow = 0;
    private int entranceCol = 1;
    private int exitRow;
    private int exitCol;

    public MazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new int[rows][cols];
        this.visited = new boolean[rows][cols];

        // Definindo a saída
        this.exitRow = rows - 1;
        this.exitCol = cols - 2;

        // Inicializando o labirinto com paredes (1)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                maze[r][c] = 1; // Paredes
            }
        }
    }

    public void generateMaze() {
        maze[entranceRow][entranceCol] = 0;
        visited[entranceRow][entranceCol] = true;

        // Gera o caminho aleatório até a saída
        createPath(entranceRow, entranceCol);

        // Certifica que a saída está marcada como caminho
        maze[exitRow][exitCol] = 0;
    }

    private void createPath(int currentRow, int currentCol) {
        if (currentRow == exitRow && currentCol == exitCol) {
            return; // Se chegamos à saída, paramos a recursão
        }

        int[][] directions = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}}; // Up, Down, Left, Right
        Random random = new Random();

        // Embaralha as direções
        for (int i = 0; i < directions.length; i++) {
            int randIndex = random.nextInt(directions.length);
            int[] temp = directions[i];
            directions[i] = directions[randIndex];
            directions[randIndex] = temp;
        }

        for (int[] direction : directions) {
            int newRow = currentRow + direction[0];
            int newCol = currentCol + direction[1];

            // Verifica se a nova célula é válida
            if (isValid(newRow, newCol)) {
                // Remove a parede entre a célula atual e a nova célula
                maze[currentRow + direction[0] / 2][currentCol + direction[1] / 2] = 0;

                // Marca a nova célula como visitada
                maze[newRow][newCol] = 0;
                visited[newRow][newCol] = true;

                // Chama recursivamente para a nova célula
                createPath(newRow, newCol);
            }
        }
    }

    private boolean isValid(int row, int col) {
        return row > 0 && row < rows && col > 0 && col < cols && !visited[row][col];
    }


    public void printMaze() {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public void saveMazeToCSV(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int[] row : maze) {
                for (int j = 0; j < row.length; j++) {
                    writer.write(row[j] + (j == row.length - 1 ? "" : ","));
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the maze: " + e.getMessage());
        }
    }

}
