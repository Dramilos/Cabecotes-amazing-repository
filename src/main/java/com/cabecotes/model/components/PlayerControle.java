package com.cabecotes.model.components;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.cabecotes.model.EntityType;

import static com.almasb.fxgl.dsl.FXGL.onKey;

public class PlayerControle extends Component
{
    private PhysicsComponent physics;

    public void moverDireita() {
        physics.setVelocityX(150);
    }

    public void moverEsquerda() {
        physics.setVelocityX(-150);
    }

    public void pular()
    {
        if(physics.isOnGround())
        {
            physics.setVelocityY(-300);
        }
    }
<<<<<<< Updated upstream
=======

public void chutar() {

    if (entity.isType(EntityType.PLAYER1)) {
        // Rotacionar o pé para a esquerda


        
    }
    else if (entity.isType(EntityType.PLAYER2)) {
        // Rotacionar o pé para a direita
    }

>>>>>>> Stashed changes
}
}       

