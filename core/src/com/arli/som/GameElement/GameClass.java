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
    int day = 1;
    int month = 1;
    float buffer = 0;
    public GameClass(Map<String,String> options) { // Создаём основные части игры
        batch = new SpriteBatch();
        infoCountry = new InfoCountry();
        infoCountry.addNewInfo(-1); // Общая информация
        infoCountry.addNewInfo(1); // Информация о стране игрока
            ///                 Сделать для разных игроков и  ботов                                             ///
        infoCountry.putInfoCountry(1,"energy","0");
        infoCountry.putInfoCountry(1,"matter","0");
        infoCountry.putInfoCountry(1,"research","0");
        infoCountry.putInfoCountry(1,"transformation","0");
        this.options = options;
        // Подключение основных систем
        map = new MAP(options.get("map"),infoCountry);
        hud = new HUD(MCont,infoCountry);
        MCont = new MapController(map,hud); // Управляет картой
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
    private void update(float delta){
        updateMonthEffects();
        MCont.update(delta);
        if(buffer<Constants.timeDayCycle){
            buffer+=delta;
        }else {
            if (day + 1 < 30) {
                day += 1;
            } else {
                day = 0;
                month += 1;
                activateMonthEffects();
            }
            buffer = 0;
        }
        // Используется для строительства
        Map<String,String> effects = map.getEffects(1);
        infoCountry.putInfoCountry(1, "transformation",effects.get("t"));

        infoCountry.putInfoCountry(-1,"day",day+"");
        infoCountry.putInfoCountry(-1,"month",month+"");

    }
    public void updateMonthEffects(){
        Map<String,String> effects = map.getEffects(1); // Для игрока
        infoCountry.putInfoCountry(1, "energyE", effects.get("e"));
        infoCountry.putInfoCountry(1, "matterE", effects.get("m"));
        infoCountry.putInfoCountry(1, "researchE", effects.get("r"));
    }
    public void activateMonthEffects(){
           Map<String,String> effects = map.getEffects(1); // Для игрока
            infoCountry.putInfoCountryPlus(1, "energy", effects.get("e"));
            infoCountry.putInfoCountryPlus(1, "matter", effects.get("m"));
            infoCountry.putInfoCountryPlus(1, "research", effects.get("r"));
            infoCountry.putInfoCountry(1, "transformation",effects.get("t"));
    }
    @Override
    public void render(float delta) {
        // update
        update(delta);
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
