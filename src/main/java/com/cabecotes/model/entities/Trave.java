package com.cabecotes.model.entities;

import com.almasb.fxgl.entity.Entity;
import com.cabecotes.model.EntityType;
import javafx.scene.paint.Color;

public class Trave extends Floor {

    // A trave também pede apenas posição e tamanho, mas aplica o quique baixo por dentro
    public static Entity criarTrave(double x, double y, double largura, double altura) {
        return criarObjetoEstatico(x, y, largura, altura, 0.2f, 0.1f, EntityType.TRAVE, Color.AQUAMARINE);
    }
}