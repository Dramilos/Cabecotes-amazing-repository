package com.cabecotes;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication
{
    // referência do player
    private Entity player;

    // referência ao objeto da física
    PhysicsComponent physics = new PhysicsComponent();

    @Override
    protected void initSettings(GameSettings settings)
    {
        settings.setWidth(1100);
        settings.setHeight(600);
        settings.setTitle("Cabeçotes");
        settings.setVersion("alpha 1.0");

        BodyDef bd = new BodyDef();
        bd.setType(BodyType.DYNAMIC);

        physics.setBodyDef(bd);
    }

    @Override
    protected void initGame()
    {
        player = entityBuilder()
            .at(380,190)
            .view(new Rectangle(40,40,Color.BLUE))
            .with(physics)
            .collidable()
            .buildAndAttach();
    }

    public static void main(String[] args)
    {
        launch(args);
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

    protected void initPhysics()
    {
        FXGL.getPhysicsWorld().setGravity(0, 500);
    }
}