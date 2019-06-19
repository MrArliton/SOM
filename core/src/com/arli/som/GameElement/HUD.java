package com.arli.som.GameElement;

import com.arli.som.Constants;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD {
    OrthographicCamera cameraHUD;
    public Viewport view = new StretchViewport(Constants.width, Constants.heigth);
    MapCon controll;
    Sprite text = new Sprite(new Texture("HUD/InfoPanel.png"));
    public HUD(MapCon conM) {
    cameraHUD = new OrthographicCamera(Constants.width,Constants.heigth);
    cameraHUD.setToOrtho(false,Constants.width,Constants.heigth);
    view.setCamera(cameraHUD);
        controll = conM;

    }

    public void render(SpriteBatch batch){
        text.setBounds(20,Constants.heigth-60,680,60);
        batch.setProjectionMatrix(cameraHUD.combined);
        text.draw(batch);
    }

    public void update(float delta){

    }
    public boolean clickMe(int x,int y){
        return false;
    }
    public void editPositionMap(){ // Действия при сдвиге карты

    }
    public void resize(int width,int height){
        view.update(width,height);
    }

    public void dispose(){}
}
