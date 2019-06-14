package com.arli.som;

import com.arli.som.GameClasses.Controllers.IntelectBot;
import com.arli.som.GameClasses.Controllers.Player;
import com.arli.som.GameClasses.Controllers.PlayerInternet;
import com.arli.som.GameClasses.POLE;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Map;

public class Game implements Screen {
        OrthographicCamera cameraWorld = new OrthographicCamera(Constants.width,Constants.heigth); // Мировая камера
        OrthographicCamera cameraHud = new OrthographicCamera(Constants.width,Constants.heigth);
        Viewport viewport = new FitViewport(Constants.width,Constants.heigth);
       public InputControllerGame inputC = new InputControllerGame();
        Player player;
        SpriteBatch batch;
        Array<IntelectBot> intelects = new Array<IntelectBot>();
        Array<PlayerInternet> internets;
        POLE pole;
        // Время
    int years = 0;
    int days = 0;
        //
        public Game(boolean multiplayer, Map<String,String> options){ // Тут получаем все параметры для создания карты и её генерации
            batch = new SpriteBatch();
            player = new Player();
            viewport.setCamera(cameraHud);
            if(multiplayer){ // Если присутствует режим мультиплеера
                internets = new Array<PlayerInternet>();
            }
        }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
            // Update
            if(inputC.onClicked(delta)){ // При клике
                boolean hucYes = false;
                // Тут передаём всем координаты о клике
                // Чьи координаты тот отзовётся
                // getCoordinate() HUD


                if(!hucYes) { // Если нажали на HUD то отключаем нажатие на клетки
                    //getCoordinateWorld(Camera) World
                    // Для отслеживания нажатия по клеткам


                }
            }

            // render
        batch.setProjectionMatrix(cameraWorld.combined); // Мировые координаты

        batch.setProjectionMatrix(cameraHud.combined); // Координаты относительно игрока
        player.render(batch); // Прорисовка HUD
            //
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        viewport.apply();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
