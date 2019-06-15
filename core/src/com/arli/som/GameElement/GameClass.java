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

// Основной класс игры
public class GameClass implements Screen {
    MAP map;
    HUD hud;
    SpriteBatch batch;
     InputController input;
    Viewport view = new FitViewport(Constants.width,Constants.heigth);
    MapController MCont;
    public GameClass() { // Создаём основные части игры
        batch = new SpriteBatch();
        map = new MAP("");
        hud = new HUD(MCont);
        MCont = new MapController(map); // Управляет картой
        input = new InputController(map,hud,batch);
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
        Gdx.gl.glClearColor(0.5f,0.8f,1,0.5f);
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
