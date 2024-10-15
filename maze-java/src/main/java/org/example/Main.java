package org.example;

import org.example.controller.MazeController;
import org.example.model.Coordinate;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "maze-java/mazes/20x20.csv";
        //String filePath = "maze-java/mazes/5x5.csv";
        //String filePath = "maze-java/mazes/10x10.csv";
        int size = 20;

        MazeController controller = new MazeController(filePath, size);
        List<Coordinate> path = controller.runDepth();
        SwingUtilities.invokeLater(() -> {
            controller.initGraphics(size);
            // controller.initAnimation(path);
            // controller.initAnimationWithImage(path);
            controller.initAnimationWithImageAndSound(path);
        });
    }
}