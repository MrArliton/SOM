package com.arli.som.GameElement;

import com.arli.som.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ConcurrentModificationException;
import java.util.Map;

// Основной класс игры
public class GameClass implements Screen {
    MAP map;
    HUD hud;
    SpriteBatch batch;
     InputController input;
     Map<String,String> options; // Настройки при запуске игры
     InfoCountry infoCountry; // Тут информация о старанах их ресурсах.
    Viewport view = new FitViewport(Constants.width,Constants.heigth);
    MapController MCont;
    public GameClass(Map<String,String> options) { // Создаём основные части игры
        batch = new SpriteBatch();
        this.options = options;
        // Подключение основных систем
        map = new MAP(options.get("map"));
        hud = new HUD(MCont);
        MCont = new MapController(map); // Управляет картой
        // Система контроля
        input = new InputController(map,hud,MCont);
        // Создаём коректировку для нужных разрешений
        int h = Constants.heigth;
        int w = Constants.width;
        int h1 = Constants.resolutionWindowH;
        int w1 = Constants.resolutionWindowW;
        float correct = Math.max(1-(float)h/h1,1-(float)w/w1)+0.05f;
        if(correct<0){
            correct = 0;
        }
        map.cameraMap.zoom = 1f-correct;
        // Переключение на систему управления игрой
        Gdx.input.setInputProcessor(new GestureDetector(input));
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // update

        map.update(delta);
        hud.update(delta);
        // render
        batch.begin();
        Gdx.gl.glClearColor(0f,0f,0f,0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.render(batch);
        hud.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        view.update(width,height);
        map.resize(width,height);
        hud.resize(width,height);
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
