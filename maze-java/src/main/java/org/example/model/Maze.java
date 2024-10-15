package org.example.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private ArrayList<ArrayList<Integer>> maze;
    private int width, height;
    private ArrayList<ArrayList<Boolean>> visited;
    // estamos utilizando um começo fixo no [0,0]
    private Coordinate startPoint;

    public Maze(String csvFileName) {
        this.maze = new ArrayList<>();
        this.startPoint = new Coordinate(0, 0);

        csvToMaze(csvFileName);
    }


    public void csvToMaze(String csvFileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split("\\s+");
                this.width = values.length;

                ArrayList<Integer> row = new ArrayList<>();

                for (String value: values){
                    row.add(Integer.parseInt(value));
                }
                maze.add(row);
                this.height++;
            }
        } catch (IOException e) {
            System.out.println("nao deu pra converter pra integer.");
        }

        iniciateVisitedArray();
    }

    public void iniciateVisitedArray() {
        visited = new ArrayList<>();
        for (int row = 0; row < maze.size(); row++) {
            ArrayList<Boolean> visitedRow = new ArrayList<>();
            for (int col = 0; col < maze.get(row).size(); col++) {
                visitedRow.add(false);
            }
            visited.add(visitedRow);
        }
    }

    public void printMaze() {
        if (maze.isEmpty()) {
            System.out.println("O labirinto está vazio.");
        } else {
            System.out.println("width: " + width + "\nheight: " + height);
            System.out.println("Labirinto:\n");
            for (ArrayList<Integer> row : maze) {
                for (Integer value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        }
    }

    public void printPath(List<Coordinate> path) {
        if (path.isEmpty()) {
            System.out.println("ta vazio amore");
        } else {
            for (Coordinate coordinate : path) {
                coordinate.printCoordinate();
            }
        }
    }

    public void setVisited(int row, int col) {
        visited.get(row).set(col, true);
    }

    public boolean isValid(int row, int col) {
        if (row < 0 || row >= this.height ||
            col < 0 || col >= this.width) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isWall(int row, int col) {
        return maze.get(row).get(col) == 0;
    }

    public boolean isVisited(int row, int col) {
        return visited.get(row).get(col);
    }

    public boolean isExit(int row, int col) {
        return row == getEndPoint().getX() && col == getEndPoint().getY();
    }

    public Coordinate getEndPoint() {
        for (int j = 0; j < this.maze.getFirst().size(); j++)
            if (this.maze.getFirst().get(j) == 1 && !(j == 0))
                return new Coordinate(0, j);

        for (int i = 1; i < this.maze.size(); i++)
            if (this.maze.get(i).getLast() == 1)
                return new Coordinate(i, this.maze.getLast().size()-1);

        for (int j = this.maze.getLast().size()-2; j >= 0; j--)
            if (this.maze.getLast().get(j) == 1 && !(j == 0))
                return new Coordinate(this.maze.size()-1, j);

        for (int i = this.maze.size()-1; i > 0; i--)
            if (this.maze.get(i).getFirst() == 1 && !(i == 0))
                return new Coordinate(i, 0);

        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<ArrayList<Integer>> getMaze() {
        return maze;
    }
}
