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

public class Gol {
    public static Entity criarGol(double x, double y, double largura, double altura, boolean golEsquerdo) {
        PhysicsComponent physics = new PhysicsComponent();
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.STATIC);
        physics.setBodyDef(bd);

        // atrito
        FixtureDef fd = new FixtureDef();
        physics.setFixtureDef(fd);

        // sprite
        var spriteGol = FXGL.texture("GOL.png");
        if(!golEsquerdo)
        {
            spriteGol.setScaleX(-1);
        }

        return FXGL.entityBuilder()
                .type(EntityType.GOL)
                .at(x, y)
                .view(spriteGol)
                .bbox(new HitBox(BoundingShape.box(largura, altura)))
                .collidable()
                .buildAndAttach();
    }
}
