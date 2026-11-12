package com.cabecotes.main;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.cabecotes.model.EntityType;
import com.cabecotes.model.entities.*;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.cabecotes.model.components.PlayerControle;
import javafx.scene.layout.StackPane;
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
    private int tempo = 0;

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
        reiniciarPartida();
        chao = Floor.criarChao(0, 550, 1100, 50);
        traveE = Trave.criarTrave(1020, 340, 80, 10);
        traveD = Trave.criarTrave(0, 340, 80, 10);
        paredeE = Floor.criarChao( 0, 0, 0,800);
        paredeD = Floor.criarChao( 1100, 0, 0,800);
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

            getGameTimer().runOnceAfter(this::reiniciarPartida, Duration.seconds(2));
        });
    }

    private void reiniciarPartida() {
        // tirar todas as entidades do mapa
        if (player1 != null) player1.removeFromWorld();
        if (player2 != null) player2.removeFromWorld();
        if (bola != null) bola.removeFromWorld();
        if (golD != null) golD.removeFromWorld();
        if (golE != null) golE.removeFromWorld();

        // recriar todas elas pra reiniciar
        player1 = Player.criarPlayer(800, 500);
        player2 = Player.criarPlayer(300, 500);
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
                vacilou.setFont(Font.font(64));
                vacilou.setFill(Color.BLACK);

                vacilou.setTranslateX(300);
                vacilou.setTranslateY(300);

                addUINode(vacilou);

                getGameTimer().runOnceAfter(this::reiniciarPartida, Duration.seconds(2));
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