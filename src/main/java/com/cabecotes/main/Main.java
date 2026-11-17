package com.cabecotes.main;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.cabecotes.model.EntityType;
import com.cabecotes.model.entities.*;
import com.cabecotes.model.entities.surfaces.*;
import javafx.scene.input.KeyCode;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.cabecotes.model.components.PlayerControle;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication
{
    // referências pra gente ter acesso
    private Entity player1, player2;
    private Entity bola;
    private Entity chao, traveE, traveD;
    private Entity paredeE, paredeD;
    private Entity golD, golE;

    private boolean golMarcado = false;
    private boolean bolaIsolada = false;

    private int pontosP1 = 0;
    Text pontuacaoP1;
    private int pontosP2 = 0;
    Text pontuacaoP2;
    Text golP1;
    Text golP2;
    Text gol;

    @Override
    protected void initSettings(GameSettings settings)
    {
        // configuraçoes da janela do jogo
        settings.setWidth(1100);
        settings.setHeight(600);
        settings.setTitle("Cabeçotes");
        settings.setVersion("alpha 1.1");
        settings.setMainMenuEnabled(true);

        // Diz ao FXGL qual classe deve gerenciar o menu principal
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new Menu(); // Retorna a nossa classe Menu recém-criada
            }
        });
    }

    @Override
    protected void initGame()
    {
        FXGL.getGameScene().setBackgroundRepeat("background_clouds.png");
        reiniciarPartida();
        chao = Floor.criarChao(0, 550, 1100, 50);
        traveE = Trave.criarTrave(1020, 340, 80, 10);
        traveD = Trave.criarTrave(0, 340, 80, 10);
        paredeE = Wall.criarParede( 0, 0, 0,800);
        paredeD = Wall.criarParede( 1100, 0, 0,800);
    }

    @Override
    protected void initPhysics()
    {
        // definir gravidade do jogo
        getPhysicsWorld().setGravity(0, 660);

        onCollisionBegin(EntityType.BALL, EntityType.GOL, (ballEntity, goalEntity) -> {
            // se marcou gol ele nao deixa marcar de novo
            if (golMarcado) {
                return;
            }

            golMarcado = true;

            boolean golLadoEsquerdo = goalEntity.getPosition().getX() > 500;
            // gol direito
            if (golLadoEsquerdo) {
                pontosP1++; // Soma ponto
                pontuacaoP1.setText(String.valueOf(pontosP1));
                addUINode(golP2, 440, 340);
                FXGL.play("GOLART.wav");
            }
            // gol esquerdo
            else {
                pontosP2++; // Soma ponto
                pontuacaoP2.setText(String.valueOf(pontosP2));
                addUINode(golP1, 440, 340);
                FXGL.play("gol.wav");
            }

            addUINode(gol, 460, 300);

            getGameTimer().runOnceAfter(() -> {
                reiniciarPartida();
                removeUINode(gol);
                removeUINode(golP1);
                removeUINode(golP2);
            }, Duration.seconds(2));
        });
    }

    protected void initUI()
    {
        Text placarTexto = new Text("PLACAR");
        placarTexto.setFill(Color.WHITE);
        placarTexto.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        // Placar P1
        pontuacaoP1 = new Text("0");
        pontuacaoP1.setFill(Color.WHITE);
        pontuacaoP1.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        // Placar P2
        pontuacaoP2 = new Text("0");
        pontuacaoP2.setFill(Color.WHITE);
        pontuacaoP2.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        FXGL.addUINode(placarTexto, 30, 60);
        FXGL.addUINode(pontuacaoP1, 30, 110);
        FXGL.addUINode(pontuacaoP2, 30, 160);

        gol = new Text("GOOOLLL");
        gol.setFill(Color.BLACK);
        gol.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        golP1 = new Text ("DO LEOZAO");
        golP1.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        golP2 = new Text ("DO ARTHUR");
        golP2.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
    }

    private void reiniciarPartida() {
        // tirar todas as entidades do mapa
        if (player1 != null) player1.removeFromWorld();
        if (player2 != null) player2.removeFromWorld();
        if (bola != null) bola.removeFromWorld();
        if (golD != null) golD.removeFromWorld();
        if (golE != null) golE.removeFromWorld();

        // recriar todas elas pra reiniciar
        player1 = Player.criarPlayer(800, 490, "SPRLEO.png");
        player2 = Player.criarPlayer(300, 490, "SPRART.png");
        bola = Ball.criarBola(550, 100);
        golD = Gol.criarGol(1020, 350, 70, 200);
        golE = Gol.criarGol(10, 350, 70, 200);

        golMarcado = false;
        bolaIsolada = false;
    }

    @Override
    protected void onUpdate(double tpf) {

        if(golMarcado || bolaIsolada)
        {
            return;
        }

        if (bola != null && bola.isActive()) {
            double x = bola.getPosition().getX();
            double y = bola.getPosition().getY();

            if (x > 1100 || x < 0 || y > 800) {
                bolaIsolada = true;
                Text vacilou = new Text("isolou a bola lol");
                play("isolou.wav");
                vacilou.setFill(Color.BLACK);
                vacilou.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

                vacilou.setTranslateX(420);
                vacilou.setTranslateY(300);

                addUINode(vacilou);

                getGameTimer().runOnceAfter(() -> {
                    reiniciarPartida();
                    FXGL.removeUINode(vacilou);
                }, Duration.seconds(2));
            }
        }
    }

    protected void initInput() {
        onKey(KeyCode.RIGHT, () -> player1.getComponent(PlayerControle.class).moverDireita());
        onKey(KeyCode.LEFT, () -> player1.getComponent(PlayerControle.class).moverEsquerda());
        onKey(KeyCode.UP, () -> player1.getComponent(PlayerControle.class).pular());

        onKey(KeyCode.D, () -> player2.getComponent(PlayerControle.class).moverDireita());
        onKey(KeyCode.A, () -> player2.getComponent(PlayerControle.class).moverEsquerda());
        onKey(KeyCode.W, () -> player2.getComponent(PlayerControle.class).pular());

        // Chute do Player 2
        onKeyDown(KeyCode.SPACE, () -> player2.getComponent(PlayerControle.class).chutar(bola));

        // Chute do Player 1
        onKeyDown(KeyCode.P, () -> player1.getComponent(PlayerControle.class).chutar(bola));
    }

    public static void main(String[] args)
    {
        // só inicia, serve pra nada
        launch(args);
    }
}