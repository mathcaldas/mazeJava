package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MazeGenerator {
    private int rows;
    private int cols;
    private int[][] maze;
    private boolean[][] visited;

    public MazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new int[rows][cols];
        this.visited = new boolean[rows][cols];
    }

    public void generateMaze(int startRow, int startCol) {
        visited[startRow][startCol] = true;
        maze[startRow][startCol] = 0; // 0 represents path

        // Directions for moving up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Random random = new Random();

        // Shuffle directions to create randomness
        for (int i = 0; i < 4; i++) {
            int randIndex = random.nextInt(4);
            int[] temp = directions[i];
            directions[i] = directions[randIndex];
            directions[randIndex] = temp;
        }

        for (int[] direction : directions) {
            int newRow = startRow + direction[0] * 2;
            int newCol = startCol + direction[1] * 2;

            if (isValid(newRow, newCol)) {
                // Remove wall between current cell and new cell
                maze[startRow + direction[0]][startCol + direction[1]] = 0;
                generateMaze(newRow, newCol);
            }
        }
    }

    private boolean isValid(int row, int col) {
        return row > 0 && row < rows && col > 0 && col < cols && !visited[row][col];
    }

    public void setEntryAndExit() {
        // Set entrance at (0, 1)
        maze[0][1] = 0; // Entrance
        // Set exit at (rows-1, cols-2)
        maze[rows - 1][cols - 2] = 0; // Exit
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
