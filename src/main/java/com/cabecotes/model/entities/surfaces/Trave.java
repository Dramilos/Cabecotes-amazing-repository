package com.cabecotes.model.entities.surfaces;

import com.almasb.fxgl.entity.Entity;
import com.cabecotes.model.EntityType;
import javafx.scene.paint.Color;

public class Trave extends Floor {

    // criador de traves
    public static Entity criarTrave(double x, double y, double largura, double altura) {
        return criadorDeSuperficies(x, y, largura, altura, 0.2f, 0.1f, EntityType.TRAVE, Color.AQUAMARINE);
    }
}