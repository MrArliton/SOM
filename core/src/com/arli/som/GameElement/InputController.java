package com.arli.som.GameElement;

import com.arli.som.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InputController implements GestureDetector.GestureListener { // Информация о жестах
     MAP map;
     HUD hud;
     int buffer = 0;
     public InputController(MAP map, HUD hud, SpriteBatch batch){
         this.map = map;
         this.hud = hud;
     }
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(pointer == 0){
            buffer+= Gdx.graphics.getDeltaTime();
            if(buffer>0.15f){ // Начинаем двигать камеру

            }
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) { // Функция клика
         if(buffer<0.15f){ // Активируем клик

         }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) { // Тут реализуем перемещение по экрану перемещая камеру HUD

        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        float scaleX = map.view.getWorldWidth() / (float)map.view.getScreenWidth();
        float scaleY = map.view.getWorldHeight() / (float)map.view.getScreenHeight();
        map.cameraMap.translate((int)(-deltaX * scaleX), 0);
        map.cameraMap.translate(0, (int)(deltaY * scaleY));
        map.cameraMap.update();
        Vector2 min = new Vector2(map.cameraMap.viewportWidth,map.cameraMap.viewportHeight);
        min.scl(map.cameraMap.zoom/2);
        Vector2 max = new Vector2(Constants.width,Constants.heigth);
        max.sub(min);
        x = Math.min(max.x, Math.max(map.cameraMap.position.x, min.x));
        y = Math.min(max.y, Math.max(map.cameraMap.position.y, min.y));
        map.cameraMap.position.set(x,y,0);
        map.cameraMap.update();
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        map.setZoom(initialDistance,distance);
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

}
