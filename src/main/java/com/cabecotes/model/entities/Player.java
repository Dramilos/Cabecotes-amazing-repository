package com.cabecotes.model.entities;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;


import javafx.geometry.Point2D;

import com.cabecotes.model.EntityType;
import com.cabecotes.model.components.PlayerControle;

import com.almasb.fxgl.physics.SensorCollisionHandler;

public class Player
{


    // Metodo que constrói e retorna a Entidade do Player pronta para o jogo
    public static Entity criarPlayer(double x, double y, String sprite, EntityType tipo)
    {

        // fisica do jogador
        PhysicsComponent physics = new PhysicsComponent();
        PlayerControle controle = new PlayerControle();
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.DYNAMIC);
        bd.setFixedRotation(true);
        physics.setBodyDef(bd);

        physics.addSensor(
        new HitBox(
        new Point2D(15, 58),
        BoundingShape.box(30, 4)
    ),
    new SensorCollisionHandler() {

       @Override
        protected void onCollisionBegin(Entity other) {
            if (other.isType(EntityType.FLOOR) ||
                other.isType(EntityType.PLAYER1) ||
                other.isType(EntityType.PLAYER2)) {

                controle.entrouNoChao(other);
            }
        }

       @Override
        protected void onCollisionEnd(Entity other) {
            if (other.isType(EntityType.FLOOR) ||
                other.isType(EntityType.PLAYER1) ||
                other.isType(EntityType.PLAYER2)) {

                controle.saiuDoChao(other);
            }
        }
    }
);

        var imagemDoJogador = FXGL.texture(sprite);
        imagemDoJogador.setFitWidth(55);
        imagemDoJogador.setFitHeight(55);
        imagemDoJogador.setPreserveRatio(true);

        // atrito do player
        FixtureDef fd = new FixtureDef();
        fd.setFriction(1.3f);
        fd.setDensity(5f);
        physics.setFixtureDef(fd);


        // construtor de entidades player
        return FXGL.entityBuilder()
                .type(tipo)
                .at(x, y)
                .view(imagemDoJogador)
                .with(physics)
                .with(controle)
                .collidable()
                .bbox(new HitBox(BoundingShape.circle(30)))
                .buildAndAttach();
        }
    }

