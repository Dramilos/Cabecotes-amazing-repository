package com.cabecotes.model.entities.surfaces;

import com.almasb.fxgl.entity.Entity;
import com.cabecotes.model.EntityType;
import com.cabecotes.model.entities.Surface;
import javafx.scene.paint.Color;

public class Wall extends Surface {
    // Metodo para criar o chao a partir da superclasse
    public static Entity criarParede(double x, double y, double largura, double altura) {
        return criadorDeSuperficies(x, y, largura, altura, 0f, 0.0f, EntityType.FLOOR, Color.GREEN);
    }
}
