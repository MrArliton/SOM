package com.arli.som.GameElement.Elements;

import com.arli.som.Constants;
import com.arli.som.GameElement.ElementLoader;
import com.arli.som.GameElement.MapCon;
import com.arli.som.GameElement.MapFiles.Cell;
import com.arli.som.GameElement.Process.Country;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Map;

import javax.swing.Spring;

public class WindowObjects extends Element { // Окно с выбором объекта для строительства
    MapCon controll;
    Cell cell;
    Sprite fone = ElementLoader.foneWinBuild;
    Array<WindowObject> objects = new Array<WindowObject>();
    // Создаёт объект если соблюдены условия для его создания
    public WindowObjects(String pathObj){

    }

    @Override
    public void resourse(Map<String, String> res) {
        super.resourse(res);
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        fone.setBounds(Constants.xWB,Constants.yWB,Constants.width-Constants.wWB,Constants.heigth-Constants.hWB);
        fone.draw(batch);
        for(int i = 0;i<objects.size;i++){ // Рисуем все нужные окна
            objects.get(i).render(batch,i);
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public Map<String, String> getInfo() {
        return super.getInfo();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }
}
