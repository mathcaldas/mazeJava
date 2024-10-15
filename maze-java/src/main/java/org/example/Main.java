package org.example;

import org.example.controller.MazeController;
import org.example.model.Coordinate;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "maze-java/mazes/20x20.csv";

        MazeController controller = new MazeController(filePath);
        List<Coordinate> path = controller.runDepth();
        SwingUtilities.invokeLater(() -> {
            controller.initGraphics();
            // controller.initAnimation(path);
            // controller.initAnimationWithImage(path);
            controller.initAnimationWithImageAndSound(path);
        });
    }
}