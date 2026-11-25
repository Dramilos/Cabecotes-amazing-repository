package com.cabecotes.model.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;


public class PlayerControle extends Component
{
    private PhysicsComponent physics;
    private Entity pe;
    private int direcao;

    private boolean chutando = false;
    private boolean bolaAtingida = false;

    private double tempoChute = 0;

    private static final double DURACAO_CHUTE = 0.25;

    public void moverDireita() {
        physics.setVelocityX(240);
    }

    public void moverEsquerda() {
        physics.setVelocityX(-240);
    }

    public void pular() {
        if (physics.isOnGround()) {
            physics.setVelocityY(-320);
        }
    
    }
    public void chutar() {

    if (chutando || pe == null) {
        return;
    }

    chutando = true;
    bolaAtingida = false;
    tempoChute = 0;
    }

    public void configurarPe(Entity pe, int direcao) {
        this.pe = pe;
        this.direcao = direcao;
    }   
    
    
    public boolean registrarAcerto() {

    if (!chutando || bolaAtingida) {
        return false;
    }

    bolaAtingida = true;
    return true;
    }

    public int getDirecao() {
        return direcao;
    }

    @Override
    public void onUpdate(double tpf) {

    if (pe == null) {
        return;
    }

    // Atualiza a posição do pé conforme o jogador
    if (direcao == -1) {
        pe.setPosition(entity.getX() + 5, entity.getY() + 49);
    } else {
        pe.setPosition(entity.getX() + 30, entity.getY() + 49);
    }

        if (chutando) {

        tempoChute += tpf;

        double progresso = Math.min(tempoChute / DURACAO_CHUTE, 1.0);

        double angulo = 65 * Math.sin(Math.PI * progresso);

        pe.setRotation(-angulo * direcao);

            if (progresso >= 1.0) {
                pe.setRotation(0);
                chutando = false;
            }
        }
    }  
}
