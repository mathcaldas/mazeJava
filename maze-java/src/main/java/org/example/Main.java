package org.example;

import org.example.controller.MazeController;
import org.example.model.MazeModel;
import org.example.view.MazeView;

import javax.swing.*;

import static org.example.Labirinto.*;

public class Main {
    public static void main(String[] args) {
        int rows = 7; // Must be odd to create walls
        int cols = 7; // Must be odd to create walls

        MazeGenerator mazeGenerator = new MazeGenerator(rows, cols);
        mazeGenerator.generateMaze();
        mazeGenerator.printMaze(); // Optional: print the maze to console
        /// mazeGenerator.saveMazeToCSV("maze.csv"); // Save to CSV
        /// fim mazegenerator ///


    }
}