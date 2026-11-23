package com.cabecotes.main;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.cabecotes.model.EntityType;
import com.cabecotes.model.entities.*;
import com.cabecotes.model.entities.surfaces.*;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.input.KeyCode;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.cabecotes.model.components.PlayerControle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import com.almasb.fxgl.physics.PhysicsComponent;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication
{
    // referências pra gente ter acesso
    private Entity player1, player2;
    private Entity bola;
    private Entity chao, traveE, traveD;
    private Entity paredeE, paredeD;
    private Entity golD, golE;
    private Entity pe1, pe2;

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
        settings.setDeveloperMenuEnabled(true);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);

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
        traveE = Trave.criarTrave(1020, 341, 80, 10);
        traveD = Trave.criarTrave(10, 341, 90, 10);
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
                addUINode(golP2, 380, 310);
                animarObjetos(golP2, 15);
                FXGL.play("GOLART.wav");
            }
            // gol esquerdo
            else {
                pontosP2++; // Soma ponto
                pontuacaoP2.setText(String.valueOf(pontosP2));
                addUINode(golP1, 380, 310);
                animarObjetos(golP1, 15);
                FXGL.play("GOLLEO.wav");
            }

            addUINode(gol, 410, 230);
            animarObjetos(gol, 15);

            getGameTimer().runOnceAfter(() -> {
                reiniciarPartida();
                removeUINode(gol);
                removeUINode(golP1);
                removeUINode(golP2);
            }, Duration.seconds(2));
        });

    onCollision(EntityType.PE, EntityType.BALL, (pe, bola) -> {

    PlayerControle controle;

    if (pe == pe1) {
        controle = player1.getComponent(PlayerControle.class);
    } else if (pe == pe2) {
        controle = player2.getComponent(PlayerControle.class);
    } else {
        return;
    }

    if (controle.registrarAcerto()) {

        PhysicsComponent fisicaBola =
                bola.getComponent(PhysicsComponent.class);

        fisicaBola.setVelocityX(700 * controle.getDirecao());
        fisicaBola.setVelocityY(-350);
    }
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
        gol.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");

        golP1 = new Text ("DO LEOZAO");
        golP1.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");

        golP2 = new Text ("DO ARTHUR");
        golP2.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");
    }

    private void reiniciarPartida() {


        if(pontosP1 > 9)
        {
            vitoriaEpica(true);
        }
        else if(pontosP2 > 9)
        {
            vitoriaEpica(false);
        }
        // tirar todas as entidades do mapa
        if (player1 != null) player1.removeFromWorld();
        if (player2 != null) player2.removeFromWorld();
        if (pe1 != null) pe1.removeFromWorld();
        if (pe2 != null) pe2.removeFromWorld();
        if (bola != null) bola.removeFromWorld();
        if(golD != null) golD.removeFromWorld();
        if(golE != null) golE.removeFromWorld();

        // recriar todas elas pra reiniciar
        player1 = Player.criarPlayer(800, 490, "SPRLEO.png", EntityType.PLAYER1);
        player2 = Player.criarPlayer(300, 490, "SPRART.png",EntityType.PLAYER2);
        bola = Ball.criarBola(550, 100);
        golD = Gol.criarGol(1020, 350, 70, 200, false);
        golE = Gol.criarGol(10, 350, 70, 200, true);
        
        pe1 = Pe.criarPe(0, 0, -1);
        pe2 = Pe.criarPe(0, 0, 1);

        player1.getComponent(PlayerControle.class).configurarPe(pe1, -1);
        player2.getComponent(PlayerControle.class).configurarPe(pe2, 1);

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
        onKeyDown(KeyCode.SPACE, () -> player2.getComponent(PlayerControle.class).chutar());

        // Chute do Player 1
        onKeyDown(KeyCode.DOWN, () -> player1.getComponent(PlayerControle.class).chutar());
    }

    private static void vitoriaEpica(boolean p1Venceu)
    {
        Rectangle background = new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight(), Color.WHITE);
        addUINode(background);

        var ImgArt = texture("vitoriaArthur.jpg");
        var ImgLeo = texture("vitoriaLeo.jpg");

        Text vitoriaArthur = new Text("ARTHUR É O GRANDE VENCEDOR");
        vitoriaArthur.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");
        Text vitoriaLeo = new Text("LEOZAO É O GRANDE VENCEDOR");
        vitoriaLeo.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");

        if(p1Venceu)
        {
            addUINode(vitoriaArthur, 60, 200);
            ImgArt.setFitWidth(500);
            ImgArt.setFitHeight(300);
            ImgArt.setTranslateX(280);
            ImgArt.setTranslateY(250);
            addUINode(ImgArt);
        }
        else
        {
            addUINode(vitoriaLeo, 60, 200);
            ImgLeo.setFitWidth(500);
            ImgLeo.setFitHeight(300);
            ImgLeo.setTranslateX(280);
            ImgLeo.setTranslateY(250);
            addUINode(ImgLeo);
        }
    }

    public static void animarObjetos(javafx.scene.Node objeto, double distanciaY)
    {
        // animacao do botao subir e descer
        TranslateTransition animacaoFlutuar = new TranslateTransition(Duration.seconds(1.5), objeto);
        animacaoFlutuar.setByY(distanciaY);
        animacaoFlutuar.setCycleCount(Animation.INDEFINITE);
        animacaoFlutuar.setAutoReverse(true);
        animacaoFlutuar.setInterpolator(Interpolator.EASE_BOTH);
        animacaoFlutuar.play();
    }

    public static void main(String[] args)
    {
        // só inicia, serve pra nada
        launch(args);
    }
}