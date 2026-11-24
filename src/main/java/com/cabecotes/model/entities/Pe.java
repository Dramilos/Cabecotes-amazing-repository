package com.cabecotes.model.entities;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.cabecotes.model.EntityType;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pe {

    public static Entity criarPe(double x, double y, int direcao, String chuteira) {

        Entity pe = FXGL.entityBuilder()
                .type(EntityType.PE)
                .at(x, y)
                .view(chuteira)
                .bbox(new HitBox("PE", BoundingShape.box(25, 12)))
                .collidable()
                .buildAndAttach();

        // Define o ponto em torno do qual o pé gira
        if (direcao == -1) {
            pe.setRotationOrigin(new Point2D(12.5, -30));
        } else {
            pe.setRotationOrigin(new Point2D(12.5, -30));
        }

        return pe;
    }
}