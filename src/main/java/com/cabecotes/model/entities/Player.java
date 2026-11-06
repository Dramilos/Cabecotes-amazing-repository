package com.cabecotes.model.entities;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;

import com.cabecotes.model.EntityType;
import com.cabecotes.model.components.PlayerControle;
import static com.almasb.fxgl.dsl.FXGL.onKey;

public class Player
{

    // Método que constrói e retorna a Entidade do Player pronta para o jogo
    public static Entity criarPlayer(double x, double y)
    {

        // fisica do jogador
        PhysicsComponent physics = new PhysicsComponent();
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.DYNAMIC);
        bd.setFixedRotation(true);
        physics.setBodyDef(bd);
        physics.addGroundSensor(
            new HitBox(
            new Point2D(5, 35),
            BoundingShape.box(30, 10)
            )
        );

        // atrito do player
        FixtureDef fd = new FixtureDef();
        fd.setFriction(0.9f);
        fd.setDensity(1.6f);
        physics.setFixtureDef(fd);

        // construtor de entidades player
        return FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at(x, y)
                .view(new Rectangle(40, 40, Color.BLUE))
                .with(physics)
                .with(new PlayerControle())
                .collidable()
                .bbox(new HitBox(BoundingShape.box(40, 40)))
                .buildAndAttach();
    }
}
