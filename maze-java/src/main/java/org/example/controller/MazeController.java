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

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

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

    public void initAnimationWithImage(List<Coordinate> path) {
        JPanel[][] cells = view.getCells();

        // Caminho para a imagem
        ImageIcon playerIcon = new ImageIcon("maze-java/images/mikejacksinminusculo.jpeg");

        Timer timer = new Timer(1000, new ActionListener() {
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < path.size()) {
                    Coordinate coord = path.get(index);
                    int i = coord.getX();
                    int j = coord.getY();

                    // Limpa a célula anterior (remove a imagem)
                    if (index > 0) {
                        Coordinate prevCoord = path.get(index - 1);
                        int prevI = prevCoord.getX();
                        int prevJ = prevCoord.getY();
                        cells[prevI][prevJ].removeAll(); // Remove o JLabel anterior
                        cells[prevI][prevJ].revalidate();
                        cells[prevI][prevJ].repaint();
                    }

                    // Adiciona a imagem no JLabel
                    JLabel label = new JLabel(playerIcon);
                    cells[i][j].setLayout(new BorderLayout()); // Define o layout para facilitar o posicionamento do JLabel
                    cells[i][j].add(label); // Adiciona o JLabel com a imagem na célula

                    cells[i][j].revalidate(); // Atualiza o layout da célula
                    cells[i][j].repaint(); // Redesenha a célula

                    index++;
                } else {
                    ((Timer) e.getSource()).stop(); // Para a animação quando terminar
                }
            }
        });

        timer.setInitialDelay(0);
        timer.start();
    }

    public void initAnimationWithImageAndSound(List<Coordinate> path) {
        JPanel[][] cells = view.getCells();

        // Caminho para a imagem e áudio
        ImageIcon playerIcon = resizeImage(new ImageIcon("maze-java/images/mikejacksinminusculo.jpeg"), 50, 50); // Ajuste o tamanho conforme necessário

        // Verificação se a imagem foi carregada
        if (playerIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            System.out.println("Imagem carregada com sucesso.");
        } else {
            System.out.println("Falha ao carregar a imagem.");
        }

        Clip audioClip = loadAudioClip("maze-java/audios/IHI.wav");

        Timer timer = new Timer(1000, new ActionListener() {
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < path.size()) {
                    Coordinate coord = path.get(index);
                    int i = coord.getX();
                    int j = coord.getY();

                    // Limpa a célula anterior (remove a imagem)
                    if (index > 0) {
                        Coordinate prevCoord = path.get(index - 1);
                        int prevI = prevCoord.getX();
                        int prevJ = prevCoord.getY();

                        cells[prevI][prevJ].removeAll(); // Remove o JLabel anterior
                        cells[prevI][prevJ].revalidate(); // Revalida
                        cells[prevI][prevJ].repaint(); // Repinta
                    }

                    // Adiciona a imagem
                    cells[i][j].removeAll(); // Limpa antes de adicionar
                    JLabel label = new JLabel(playerIcon);
                    cells[i][j].setLayout(new BorderLayout());
                    cells[i][j].add(label, BorderLayout.CENTER);

                    cells[i][j].revalidate(); // Revalida após adicionar
                    cells[i][j].repaint(); // Repinta após adicionar

                    // Toca o áudio a cada 10 segundos
                    if (index % 10 == 0) {
                        new Thread(() -> playAudio(audioClip)).start();
                    }

                    index++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.setInitialDelay(0);
        timer.start();
    }

    private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage(); // pega a imagem
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // redimensiona
        return new ImageIcon(newImg); // retorna um novo ImageIcon
    }

    // Função para carregar o áudio
    private Clip loadAudioClip(String audioFilePath) {
        try {
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Erro ao carregar o áudio: " + ex.getMessage());
            return null;
        }
    }

    // Função para tocar o áudio
    private void playAudio(Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0); // Reinicia o áudio do início
            clip.start();
        }
    }
}