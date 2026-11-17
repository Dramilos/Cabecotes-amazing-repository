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

    // Metodo que constrói e retorna a Entidade do Player pronta para o jogo
    public static Entity criarPlayer(double x, double y, String sprite)
    {

        // fisica do jogador
        PhysicsComponent physics = new PhysicsComponent();
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.DYNAMIC);
        bd.setFixedRotation(true);
        physics.setBodyDef(bd);
        physics.addGroundSensor(
            new HitBox(
            new Point2D(15, 58),
            BoundingShape.box(30, 4)
            )
        );

        var imagemDoJogador = FXGL.texture(sprite);
        imagemDoJogador.setFitWidth(55);
        imagemDoJogador.setFitHeight(55);
        imagemDoJogador.setPreserveRatio(true);

        // atrito do player
        FixtureDef fd = new FixtureDef();
        fd.setFriction(0.9f);
        fd.setDensity(1.8f);
        physics.setFixtureDef(fd);

        // construtor de entidades player
        return FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at(x, y)
                .view(imagemDoJogador)
                .with(physics)
                .with(new PlayerControle())
                .collidable()
                .bbox(new HitBox(BoundingShape.circle(30)))
                .bbox(new HitBox(new Point2D(10, 50), BoundingShape.box(30, 8)))
                .buildAndAttach();
    }
}
