package com.arli.som.GameElement.Elements.dop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

public class winBuildElement { // Элемент страницы окна строительсва
    int x; // x относительно окна
     int y; // y относительно окна
    int width;
    int height;
    Sprite fone = new Sprite(new Texture("")); // Текстура
    Sprite iconObject ; // Обявляется исходя из информации о объекте
    BitmapFont texter = new BitmapFont(Gdx.files.internal("myFont.fnt"));
    String name;
    String information;
      float proEnergy;
    float proMater;
    float proTrans;
    float proReser;
    float conEnergy;
    float conMater;
    float conTrans;
    float conReser;
    int id; // Ид объекта для его орбозначени
    Map<String,String> opt;// Тут храним информаицю

    public winBuildElement(int id, Map<String, String> opt) {
        this.id = id;
        this.opt = opt;
       name = opt.get("name");
       information = opt.get("info");
       proEnergy = Float.parseFloat(opt.get("pe"));
       proMater= Float.parseFloat(opt.get("pm"));
       proTrans = Float.parseFloat(opt.get("pt"));
       proReser= Float.parseFloat(opt.get("pr"));
       conEnergy= Float.parseFloat(opt.get("ce"));
       conMater= Float.parseFloat(opt.get("cm"));
       conTrans= Float.parseFloat(opt.get("ct"));
       conReser= Float.parseFloat(opt.get("cr"));

    }

    //
    public void render(SpriteBatch batch){

    }
    public void update(){

    }
    public boolean isClicked(int x,int y){ // Говорит было ли нажатие
        return false;

    }
    public int getIdCreateObject(){ // Выдаёт id объекта для его создания
        return -1;
    }
    public void dispose(){
        fone.getTexture().dispose();
        texter.dispose();
    }
}
