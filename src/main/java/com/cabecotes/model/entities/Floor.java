package com.cabecotes.model.entities;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

import com.cabecotes.model.EntityType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Floor {

    // Método base interno que lida com a física dos objetos estáticos
    protected static Entity criarObjetoEstatico(double x, double y, double largura, double altura, float atrito, float restitution, EntityType tipo, Color cor) {
        PhysicsComponent physics = new PhysicsComponent();
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.STATIC);
        physics.setBodyDef(bd);

        // atrito e restituicao
        FixtureDef fd = new FixtureDef();
        fd.setRestitution(restitution);
        fd.setFriction(atrito);
        physics.setFixtureDef(fd);

        return FXGL.entityBuilder()
                .type(tipo)
                .at(x, y)
                .view(new Rectangle(largura, altura, cor))
                .with(physics)
                .collidable()
                .bbox(new HitBox(BoundingShape.box(largura, altura)))
                .buildAndAttach();
    }

    // metodo pra criar o chao
    public static Entity criarChao(double x, double y, double largura, double altura) {
        return criarObjetoEstatico(x, y, largura, altura, 0.5f, 0.0f, EntityType.FLOOR, Color.GREEN);
    }
}