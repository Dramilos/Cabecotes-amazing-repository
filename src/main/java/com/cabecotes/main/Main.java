package com.cabecotes.main;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.cabecotes.model.entities.Ball;
import com.cabecotes.model.entities.Floor;
import javafx.scene.input.KeyCode;
import com.cabecotes.model.entities.Player;
import com.cabecotes.model.components.PlayerControle;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication
{
    // referências pra gente ter acesso
    private Entity player1;
    private Entity player2;
    private Entity bola;
    private Entity chao;
    private Entity chao_E;
    private Entity chao_D;

    @Override
    protected void initSettings(GameSettings settings)
    {
        // configuraçoes da janela do jogo
        settings.setWidth(1100);
        settings.setHeight(600);
        settings.setTitle("Cabeçotes");
        settings.setVersion("alpha 1.0");
    }

    @Override
    protected void initGame()
    {
        player1 = Player.criarPlayer(380, 190);
        player2 = Player.criarPlayer(600, 190);
        chao = Floor.criarChao(0, 580, 1100, 20);
        chao_E = Floor.criarChao( 0, 0, 0,600);
        chao_D = Floor.criarChao( 1100, 0, 0,600);
        bola = Ball.criarBola(500, 100);
    }

    @Override
    protected void initPhysics()
    {
        // definir gravidade do jogo
        getPhysicsWorld().setGravity(0, 760);
    }

    protected void initInput() {
        onKey(KeyCode.RIGHT, () -> player2.getComponent(PlayerControle.class).moverDireita());
        onKey(KeyCode.LEFT, () -> player2.getComponent(PlayerControle.class).moverEsquerda());
        onKey(KeyCode.UP, () -> player2.getComponent(PlayerControle.class).pular());

        onKey(KeyCode.D, () -> player1.getComponent(PlayerControle.class).moverDireita());
        onKey(KeyCode.A, () -> player1.getComponent(PlayerControle.class).moverEsquerda());
        onKey(KeyCode.W, () -> player1.getComponent(PlayerControle.class).pular());
    }

    public static void main(String[] args)
    {
        // só inicia, serve pra nada
        launch(args);
    }
}