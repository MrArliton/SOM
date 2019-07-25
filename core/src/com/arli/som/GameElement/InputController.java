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
     MapCon mapCon;
    Vector3 hudXY =  new Vector3(0,0,0);
    Vector3 mapXY = new Vector3(0,0,0);
     boolean onClick = true;
     public InputController(MAP map, HUD hud, MapCon con){
         this.map = map;
         this.hud = hud;
            mapCon = con;
     }
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(pointer == 0){

        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) { // Функция клика
         if(onClick){ // Активируем клик
             // Распределяем по важности
             hudXY.set(x,y,0);
             mapXY.set(x,y,0);
             this.hud.view.unproject(hudXY);
             this.map.view.unproject(mapXY);
             System.out.println("Click x:"+mapXY.x+" y:"+mapXY.y);
             //HUD
            if(!this.hud.clickMe((int)hudXY.x,(int)hudXY.y)){
             //MAP
                mapCon.clickMap((int)mapXY.x,(int)mapXY.y); // Определяем нажатие относительно экрана
            }


         }else{onClick = true;}
        return false;
    }

    @Override
    public boolean longPress(float x, float y) { // Тут реализуем перемещение по экрану перемещая камеру HUD
        onClick = false;
        return false;
    }
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
         mapCon.clearDefaultWindows();
         onClick = false;
          hudXY.set(x,y,0);
         hud.cameraHUD.unproject(hudXY);
         if(!hud.clickMe((int)hudXY.x,(int)hudXY.y)) {
             float scaleX = map.view.getWorldWidth() / (float) map.view.getScreenWidth();
             float scaleY = map.view.getWorldHeight() / (float) map.view.getScreenHeight();
             map.cameraMap.translate((int) (-deltaX * scaleX), 0);
             map.cameraMap.translate(0, (int) (deltaY * scaleY));
             map.cameraMap.update();
             hud.editPositionMap();
         }
         return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
         onClick = true;
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
         mapCon.clearDefaultWindows();
         onClick = false;
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
