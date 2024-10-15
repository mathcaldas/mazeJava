package org.example.controller;

import org.example.model.Backtracker;
import org.example.model.Coordinate;
import org.example.model.Maze;
import org.example.view.MazeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class MazeController {
    Maze maze;
    MazeView view;
    Backtracker backtracker;

    public MazeController(String csvFileName) {
        this.maze = new Maze(csvFileName);
        this.view = new MazeView(maze);
        this.backtracker = new Backtracker();
    }

    public List<Coordinate> runDepth() {
        return backtracker.runDepth(maze);
    }

    public void initGraphics() {
        view.gameWindow();
    }

    public void initAnimation( List<Coordinate> path) {
        JPanel[][] cells = view.getCells();

        Timer timer = new Timer(1000, new ActionListener() {
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < path.size()) {
                    Coordinate coord = path.get(index);
                    int i = coord.getX();
                    int j = coord.getY();

                    cells[i][j].setBackground(Color.RED);

                    index++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.setInitialDelay(0);
        timer.start();
    }
}
