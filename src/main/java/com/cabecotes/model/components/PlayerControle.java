package com.cabecotes.model.components;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.input.KeyCode;
import com.almasb.fxgl.physics.PhysicsComponent;

import static com.almasb.fxgl.dsl.FXGL.onKey;

public class PlayerControle extends Component
{
    private PhysicsComponent physics;

    public void moverDireita() {
        physics.setVelocityX(210);
    }

    public void moverEsquerda() {
        physics.setVelocityX(-210);
    }

    public void pular() {
        if (physics.isOnGround()) {
            physics.setVelocityY(-320);
        }
    
    }

    public void chutar(Entity bola) {
        
        double distanciaX = bola.getCenter().getX() - entity.getCenter().getX();
        double distanciaY = bola.getCenter().getY() - entity.getCenter().getY();

        if (Math.abs(distanciaX) <= 65 && Math.abs(distanciaY) <= 65) {

            PhysicsComponent fisicaBola = bola.getComponent(PhysicsComponent.class);

            if (distanciaX >= 0) {

                fisicaBola.setVelocityX(420);

            } else {

                fisicaBola.setVelocityX(-420);

            }

            fisicaBola.setVelocityY(-220);
        }
    }       
}
