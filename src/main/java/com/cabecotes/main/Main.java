package com.cabecotes.main;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.cabecotes.model.entities.Floor;
import javafx.scene.input.KeyCode;
import com.cabecotes.model.entities.Player; // <--- Importando a classe Player da outra pasta!

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication
{
    // referências pra gente ter acesso
    private Entity player;
    private Entity chao;

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
        player = Player.criarPlayer(380, 190);
        chao = Floor.criarChao(300, 300, 600, 20);
    }

    protected void initInput()
    {
        onKey(KeyCode.RIGHT, () ->
        {
            player.translateX(5);
        });

        onKey(KeyCode.LEFT, () ->
        {
            player.translateX(-5);
        });

        onKey(KeyCode.UP, () ->
        {
            player.translateY(-5);
        });

        onKey(KeyCode.DOWN, () ->
        {
            player.translateY(5);
        });
    }

    @Override
    protected void initPhysics()
    {
        // definir gravidade do jogo
        getPhysicsWorld().setGravity(0, 760);
    }

    public static void main(String[] args)
    {
        // só inicia, serve pra nada
        launch(args);
    }
}