package com.arli.som.GameElement.Elements;

import com.arli.som.GameElement.Elements.dop.winBuildButton;
import com.arli.som.GameElement.Elements.dop.winBuildElement;
import com.arli.som.GameElement.InfoCountry;
import com.arli.som.GameElement.MapCon;
import com.arli.som.GameElement.MapFiles.Cell;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Map;

public class WindowBuild extends Element {
    Array<winBuildButton> buttons = new Array<winBuildButton>(); // Массив с кнопками строений и тд
    winBuildButton exit; // Кнопка выхода
    Array<winBuildElement> elementsShield = new Array<winBuildElement>(); // Массив с 1 страницей
    Array<winBuildElement> elementsAttack = new Array<winBuildElement>(); // Массив с 2 страницей
    Array<winBuildElement> elementsResourse = new Array<winBuildElement>(); // Массив с 3 страницей
    Cell cell;
    int list = 1;
    InfoCountry infoC;
    MapCon con;
    public WindowBuild(Cell cell, InfoCountry infoC, MapCon con) {
        this.cell = cell;
        this.infoC = infoC;
        this.con = con;
       info.put("type","window");
       info.put("level","1");
       info.put("cell",cell.getCellID()+"");
        info.put("res","button");
    }
    public void buildObject(int id){

    }
    @Override
    public void resourse(Map<String, String> res) {
        super.resourse(res);
    }
    private void removeThis(){
        con.removeElementWindow();
    }
    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        if(list == 1){

        }else if(list == 2){

        }else if(list == 3){

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
