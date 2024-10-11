package org.example.controller;

import org.example.model.MazeModel;
import org.example.view.MazeView;

import javax.swing.*;

public class MazeController {
    private MazeModel model;
    private MazeView view;

    public MazeController(MazeModel model, MazeView view) {
        this.model = model;
        this.view = view;
        iniciarAnimacao();
    }

    private void iniciarAnimacao() {
        Timer timer = new Timer(250, e -> moverJogador());
        timer.start();
    }

    private void moverJogador() {
        // Remove o jogador da posição atual
        view.limparPosicaoJogador(model.getPlayerRow(), model.getPlayerCol());

        // Atualiza a posição do jogador no model
        model.moverJogador();

        // Atualiza a posição do jogador na view
        view.atualizarPosicaoJogador(model.getPlayerRow(), model.getPlayerCol());
    }
}

