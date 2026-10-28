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
        physics.setVelocityX(150);
    }

    public void moverEsquerda() {
        physics.setVelocityX(-150);
    }

    public void pular() {
        physics.setVelocityY(-300);
    }
}
