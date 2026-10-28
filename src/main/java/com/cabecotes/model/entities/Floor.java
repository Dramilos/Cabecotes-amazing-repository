package com.cabecotes.model.entities;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Floor
{

    // método da classe de chão pra criar um chão
    public static Entity criarChao(double x, double y, double largura, double altura) {

        // componente de física do FXGL, definir o tipo de corpo que nosso objeto tem
        PhysicsComponent physics = new PhysicsComponent();
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.STATIC); // static, porque o chão não cai (eu espero)
        physics.setBodyDef(bd);

        // constrói a entidade chão
        return FXGL.entityBuilder()
                .at(x, y)
                .view(new Rectangle(largura, altura, Color.GREEN))
                .with(physics)  // tem física
                .collidable()   // colide
                .bbox(new HitBox(BoundingShape.box(largura, altura)))      // definindo a hitbox
                .buildAndAttach();
    }
}
