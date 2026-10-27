package com.cabecotes;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Meu Primeiro Jogo FXGL");
        settings.setVersion("1.0");
    }

    @Override
    protected void initGame() {
        System.out.println("FXGL iniciado com sucesso!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}