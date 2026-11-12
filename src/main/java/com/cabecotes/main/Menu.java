package com.cabecotes.main;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Menu extends FXGLMenu {

    public Menu() {
        super(MenuType.MAIN_MENU);

        // fundo do menu preto
        Rectangle background = new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight(), Color.WHITE);

        // Título do Jogo
        Text titulo = new Text("CABEÇOTES");
        titulo.setFill(Color.FORESTGREEN);
        titulo.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");

        // Botão para iniciar a partida
        Button btnJogar = new Button("JOGAR");
        btnJogar.setPrefWidth(250);
        btnJogar.setPrefHeight(100);
        btnJogar.setStyle("-fx-font-size: 32px; -fx-background-color: #6ce241; -fx-text-fill: white; -fx-font-weight: bold;");

        // Ação do botão: fecha o menu e inicia o jogo
        btnJogar.setOnAction(event -> {
            // Fecha o menu atual, permitindo que o jogo comece/deslize para a cena principal
            fireNewGame();
        });

        // Painel para organizar os elementos no centro da tela
        StackPane root = new StackPane();
        root.setPrefSize(FXGL.getAppWidth(), FXGL.getAppHeight());
        root.getChildren().addAll(background, titulo, btnJogar);

        // Posicionamentos finos via StackPane ou translações
        StackPane.setAlignment(titulo, Pos.TOP_CENTER);
        titulo.setTranslateY(230);

        StackPane.setAlignment(btnJogar, Pos.CENTER);
        btnJogar.setTranslateY(60);

        getContentRoot().getChildren().add(root);
    }
}