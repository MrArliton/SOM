package com.arli.som;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InputControllerGame {
    Viewport port = new FitViewport(Constants.width, Constants.heigth);
    Vector3 vector = new Vector3();
    float buffer = 0;
    float resizeMap = 0;
    public void setViewport(Viewport port, OrthographicCamera camera) {
        this.port = port;
    }

    public Vector3 getCoordinate() {
        vector.x = Gdx.input.getX();
        vector.y = Gdx.input.getY();
        port.unproject(vector);
        return vector;
    }
    public float getResizeMap(){ // ВЫдаёт для камеры изменение приблежения
        return resizeMap;
    }
    public void mapWH(){ // Создаёт процент приблежения
        if(Gdx.input.isTouched(1)){
            int x = Math.abs(Gdx.input.getDeltaX())+Math.abs(Gdx.input.getDeltaX(1));
            int y = Math.abs(Gdx.input.getDeltaY())+Math.abs(Gdx.input.getDeltaY(1));
            if(Gdx.input.getDeltaX()>0&&Gdx.input.getDeltaY()>0&&Gdx.input.getDeltaX(1)<0&&Gdx.input.getDeltaY(1)<0||Gdx.input.getDeltaX()<0&&Gdx.input.getDeltaY()<0&&Gdx.input.getDeltaX(1)>0&&Gdx.input.getDeltaY(1)>0){ // Приближение
                resizeMap = x+y;
            }
        }
    }
    public Vector3 getCoordinateWorld(OrthographicCamera camera){
        vector.x = Gdx.input.getX();
        vector.y = Gdx.input.getY();
        camera.unproject(vector);
        return vector;
    }
    public boolean onClicked(float delta) {
        if (Gdx.input.isTouched()) {
            buffer+= delta;
            if(buffer>0.15){
                mapWH();// Приблежение
            }
        }else{
            if(delta<0.15f&&delta!=0){
                buffer = 0;
                return true;
            }
        }
        return false;
    }
}
