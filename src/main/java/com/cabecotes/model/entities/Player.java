package com.cabecotes.model.entities;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import com.cabecotes.model.components.PlayerControle;
import static com.almasb.fxgl.dsl.FXGL.onKey;

public class Player
{

<<<<<<< Updated upstream
    // Método que constrói e retorna a Entidade do Player pronta para o jogo
    public static Entity criarPlayer(double x, double y)
=======
    HitBox  pe;

    // Metodo que constrói e retorna a Entidade do Player pronta para o jogo
    public static Entity criarPlayer(double x, double y, String sprite, EntityType tipo)
>>>>>>> Stashed changes
    {

        // fisica do jogador
        PhysicsComponent physics = new PhysicsComponent();
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);
<<<<<<< Updated upstream
=======
        physics.addGroundSensor(
            new HitBox(
            new Point2D(5, 46),
            BoundingShape.circle(15)
            )
        );

        // atrito do player
        FixtureDef fd = new FixtureDef();
        fd.setFriction(0.9f);
        fd.setDensity(1.8f);
        physics.setFixtureDef(fd);
<<<<<<< Updated upstream
>>>>>>> Stashed changes

        // construtor de entidades player
        return FXGL.entityBuilder()
=======
    
    HitBox  pe;    

    if (tipo == EntityType.PLAYER1) {
        // Pé esquerdo
        pe = new HitBox("PE", new Point2D(-5, 45), BoundingShape.box(25, 12));

    } else {
        // Pé direito
        pe = new HitBox("PE", new Point2D(35, 45), BoundingShape.box(25, 12));
}

    Entity pe1 = FXGL.entityBuilder()
        .type(EntityType.PE)
        .at(x, y)
        .view(new Rectangle(25, 10, Color.RED))
        .bbox(new HitBox("PE", BoundingShape.box(25, 10)))
        .collidable()
        .buildAndAttach();  


        // construtor de entidades player
        return FXGL.entityBuilder()
                .type(tipo)
>>>>>>> Stashed changes
                .at(x, y)
                .view(new Rectangle(40, 40, Color.BLUE))
                .with(physics)
                .with(new PlayerControle())
                .collidable()
<<<<<<< Updated upstream
                .bbox(new HitBox(BoundingShape.box(40, 40)))
=======
                .bbox(new HitBox(BoundingShape.circle(30)))
<<<<<<< Updated upstream
                .bbox(new HitBox(new Point2D(5, 40), BoundingShape.box(30, 8)))
>>>>>>> Stashed changes
=======
                .bbox(pe)
>>>>>>> Stashed changes
                .buildAndAttach();
        }
    }

