package com.arli.som.GameElement.Elements;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.Button;

public class WindowObject extends Actor {
    Sprite fone;
    Button build;
    BitmapFont texter; // Информация
    Sprite iconM;
    Sprite iconE;
    Sprite iconT;
    int months;
    @Override
    public void act(float delta){
        super.act(delta);
    }
    public void render(SpriteBatch sb,int level){ // level уровень прорисовки
        super.draw(sb,1f);
    }

    public void dispose(){
        fone.getTexture().dispose();
    }
}
