package com.cabecotes.model.entities;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player
{

    // Método que constrói e retorna a Entidade do Player pronta para o jogo
    public static Entity criarPlayer(double x, double y) {

        // fisica do jogador
        PhysicsComponent physics = new PhysicsComponent();
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);

        return FXGL.entityBuilder()
                .at(x, y)
                .view(new Rectangle(40, 40, Color.BLUE))
                .with(physics) // Adiciona a física que criamos aqui dentro
                .collidable()
                .buildAndAttach(); // Já cria e anexa ao mundo do jogo
    }
}
