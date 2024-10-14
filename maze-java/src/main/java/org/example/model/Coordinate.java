package org.example.model;

public class Coordinate {
    int x, y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void printCoordinate() {
        System.out.println(x + ", " + y);
    }
}