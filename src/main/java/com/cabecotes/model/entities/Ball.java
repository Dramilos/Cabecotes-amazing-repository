package com.cabecotes.model.entities;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

    public static Entity criarBola(double x, double y){


        PhysicsComponent physics = new PhysicsComponent();
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);

        FixtureDef fixture = new FixtureDef();
        fixture.setRestitution(0.8f);
        fixture.setFriction(0.3f);
        physics.setFixtureDef(fixture);

        return FXGL.entityBuilder()
        .at(x, y)
        .view(new Circle(10, 10, 10, Color.BLACK))
        .with(physics)
        .bbox(new HitBox(BoundingShape.circle(10)))
        .collidable()
        .buildAndAttach();



    }
}
