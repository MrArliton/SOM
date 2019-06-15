package com.arli.som.GameElement;

import com.arli.som.Constants;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD {
    OrthographicCamera cameraHUD;
    Viewport view = new FitViewport(Constants.width,Constants.heigth);
    MapCon controll;

    public HUD(MapCon conM) {
    cameraHUD = new OrthographicCamera(Constants.width/2,Constants.heigth/2);
    cameraHUD.setToOrtho(false,Constants.width,Constants.heigth);
        controll = conM;

    }

    public void render(SpriteBatch batch){
        batch.setProjectionMatrix(cameraHUD.combined);
    }

    public void update(float delta){

    }

    public void resize(int width,int height){
        view.update(width,height);
    }

    public void dispose(){}
}
