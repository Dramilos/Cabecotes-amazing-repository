package com.cabecotes.main;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.cabecotes.model.entities.Floor;
import javafx.scene.input.KeyCode;
import com.cabecotes.model.entities.Player;
import com.cabecotes.model.components.PlayerControle;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication
{
    // referências pra gente ter acesso
    private Entity player;
    private Entity chao;

    @Override
    protected void initSettings(GameSettings settings)
    {
        //Config do desenvolvedor
        settings.setDeveloperMenuEnabled(true);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);

        // configuraçoes da janela do jogo
        settings.setWidth(1100);
        settings.setHeight(600);
        settings.setTitle("Cabeçotes");
        settings.setVersion("alpha 1.0");
    }

    @Override
    protected void initGame()
    {
        player = Player.criarPlayer(380, 190);
        chao = Floor.criarChao(300, 300, 600, 20);
    }

    @Override
    protected void initPhysics()
    {
        // definir gravidade do jogo
<<<<<<< Updated upstream
        getPhysicsWorld().setGravity(0, 760);
    }

    protected void initInput() {
        onKey(KeyCode.RIGHT, () -> player.getComponent(PlayerControle.class).moverDireita());
        onKey(KeyCode.LEFT, () -> player.getComponent(PlayerControle.class).moverEsquerda());
        onKey(KeyCode.UP, () -> player.getComponent(PlayerControle.class).pular());
=======
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
        player1 = Player.criarPlayer(800, 480, "SPRLEO.png", EntityType.PLAYER1);
        player2 = Player.criarPlayer(300, 480, "SPRART.png", EntityType.PLAYER2);
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
        //onKeyDown(KeyCode.SPACE, () -> player2.getComponent(PlayerControle.class).chutar(bola));

        // Chute do Player 1
        //onKeyDown(KeyCode.P, () -> player1.getComponent(PlayerControle.class).chutar(bola));
>>>>>>> Stashed changes
    }

    public static void main(String[] args)
    {
        // só inicia, serve pra nada
        launch(args);
    }
}