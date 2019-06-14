package com.arli.som;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InputControllerGame {
    Viewport port = new FitViewport(Constants.width, Constants.heigth);
    Vector3 vector = new Vector3();
    float buffer = 0;

    public void setViewport(Viewport port) {
        this.port = port;
    }

    public Vector3 getCoordinate() {
        vector.x = Gdx.input.getX();
        vector.y = Gdx.input.getY();
        port.unproject(vector);
        return vector;
    }

    public boolean onClicked(float delta) {
        if (Gdx.input.isTouched()) {
            buffer+= delta;
        }else{
            if(delta<0.15f&&delta!=0){
                buffer = 0;
                return true;
            }
        }
        return false;
    }
}
