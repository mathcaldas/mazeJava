package org.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Backtracker {
    List<Coordinate> path = new ArrayList<>();

    private static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0},
            {-1, 1}, {0, 1},
            {1, 1}, {1, 0},
            {1, -1}, {0, -1}
    };

    public List<Coordinate> runDepth(Maze maze) {

        if (recursiveBacktracking(maze, 0, 0, path)) {
            return path;
        }

        return Collections.emptyList();
    }

    private boolean recursiveBacktracking(Maze maze, int row, int col, List<Coordinate> path) {
        if (!maze.isValid(row, col) ||
             maze.isWall(row, col)  ||
             maze.isVisited(row, col)) {
            return false;
        }

        path.add(new Coordinate(row, col));
        maze.setVisited(row, col);

        if (maze.isExit(row, col)) {
            return true;
        }

        for (int[] direction : DIRECTIONS) {
            Coordinate newCoord = nextCoordinate(row, col, direction[0], direction[1]);

            if (!maze.isValid(newCoord.getX(), newCoord.getY())) {
                continue;
            } else {
                if (recursiveBacktracking(maze, newCoord.getX(), newCoord.getY(), path)) {
                    return true;
                }
            }

        }
        path.remove(path.size() - 1);

        return false;
    }

    private Coordinate nextCoordinate(int row, int col, int i, int j) {
        return new Coordinate(row + i, col + j);
    }

    public List<Coordinate> getPath() {
        return path;
    }
}
