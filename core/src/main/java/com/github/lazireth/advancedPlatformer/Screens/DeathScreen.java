package com.github.lazireth.advancedPlatformer.Screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.utils.ScreenUtils;
import com.github.lazireth.advancedPlatformer.FontManager;
import com.github.lazireth.advancedPlatformer.GameCore;

import static com.github.lazireth.advancedPlatformer.GameCore.*;
import static com.github.lazireth.advancedPlatformer.InputHandler.keys;

public class DeathScreen extends ScreenAdapter {
    final private GameCore game;

    float restartTimer=5;
    boolean countDown=false;
    BitmapFont fontCalibri;
    public DeathScreen(final GameCore game){
        this.game=game;
        fontCalibri=FontManager.getFont("Calibri",64);
    }
    @Override
    public void render(float delta) {
        input();
        update(delta);
        draw();
    }
    void input(){
        if(keys[Keys.SPACE]){
            countDown=true;
        }
    }
    private void update(float delta){

        if(countDown){
            restartTimer-=delta;
        }
        if(restartTimer<=0){
            game.loadGameScreen();
        }
    }
    private void draw(){
        renderer.begin();
        ScreenUtils.clear(Color.BLACK);
        renderer.drawText((int)restartTimer+"",fontCalibri,WIDTH/2,HEIGHT/4*2);
        renderer.drawText("Press SPACE To Start",fontCalibri,WIDTH/2,HEIGHT/4*1);

        renderer.end();
    }

    @Override
    public void show() {

        restartTimer=5;
        countDown=false;
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width,height);
    }
}
