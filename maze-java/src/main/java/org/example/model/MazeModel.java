package org.example.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MazeModel {
    ArrayList<ArrayList<Integer>> maze;

    public MazeModel(String csvFilename) {
        this.maze = loadMaze(csvFilename);
    }
/*
    public int[][] getLabirinto() {
        return labirinto;
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public void moverJogador() {
        int direcao = random.nextInt(4);
        int novaLinha = playerRow;
        int novaColuna = playerCol;

        switch (direcao) {
            case 0: novaLinha--; break; // Cima
            case 1: novaLinha++; break; // Baixo
            case 2: novaColuna--; break; // Esquerda
            case 3: novaColuna++; break; // Direita
        }

        if (novaLinha >= 0 && novaLinha < labirinto.length &&
                novaColuna >= 0 && novaColuna < labirinto[0].length &&
                labirinto[novaLinha][novaColuna] == 1) {

            playerRow = novaLinha;
            playerCol = novaColuna;
        }
    }
*/
    public static ArrayList<ArrayList<Integer>> loadMaze(String csvFilename) {
        ArrayList<ArrayList<Integer>> maze = new ArrayList<>();
        File f = new File(csvFilename);

        try (Scanner s = new Scanner(f)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();

                ArrayList<Integer> row = new ArrayList<>();
                for (String v: line.split(","))
                    row.add(Integer.parseInt(v));
                maze.add(row);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado.");
            return null;
        }
        return maze;
    }
}