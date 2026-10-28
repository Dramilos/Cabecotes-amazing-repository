package com.cabecotes.main;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import com.cabecotes.model.entities.Player; // <--- Importando a classe Player da outra pasta!

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication
{
    // referência do player
    private Entity player;

    @Override
    protected void initSettings(GameSettings settings)
    {
        settings.setWidth(1100);
        settings.setHeight(600);
        settings.setTitle("Cabeçotes");
        settings.setVersion("alpha 1.0");
    }

    @Override
    protected void initGame()
    {
        player = Player.criarPlayer(380, 190);
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
        getPhysicsWorld().setGravity(0, 500);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}