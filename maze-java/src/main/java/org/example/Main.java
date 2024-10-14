package org.example;

import org.example.controller.MazeController;
import org.example.model.Backtracker;
import org.example.model.Coordinate;
import org.example.model.Maze;
import org.example.view.MazeView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\jucae\\IdeaProjects\\mazeJava\\maze-java\\mazes\\file.csv";

        Maze maze = new Maze();
        MazeView view = new MazeView(maze);

        MazeController controller = new MazeController();
        controller.initMaze(filePath);
        view.gameWindow(maze.getMaze());
    }
}