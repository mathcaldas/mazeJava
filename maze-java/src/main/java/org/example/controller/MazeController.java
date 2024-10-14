package org.example.controller;

import org.example.model.Backtracker;
import org.example.model.Coordinate;
import org.example.model.Maze;
import org.example.view.MazeView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MazeController {
    Maze maze;
    MazeView view;
    Backtracker backtracker;

    public MazeController() {
        //this.maze = new Maze();
        //this.view = new MazeView(maze);
        //this.backtracker = new Backtracker();
    }

    public void initMaze (String filePath) {
        maze.csvToMaze(filePath);
        backtracker.runDepth(maze);
    }

    public void initGraphics() {
        ArrayList<ArrayList<Integer>> mazeArray = maze.getMaze();
        view.gameWindow(mazeArray);
    }

    public void initAnimation() {
        Timer timer = new Timer(250, e -> movePlayer());
        timer.start();
    }

    public void movePlayer() {
        JPanel[][] cells = view.getCells();
        for (Coordinate coord : backtracker.getPath()) {
            int i = coord.getX();
            int j = coord.getY();
            cells[i][j].setBackground(Color.RED);
        }
    }
}
