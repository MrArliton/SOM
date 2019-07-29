package com.arli.som.GameElement.Elements.dop;

import com.arli.som.Constants;
import com.arli.som.GameElement.MapFiles.objects.ObjectsLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
    Sprite fone = new Sprite(new Texture("HUD/winBuildObj.png")); // Текстура
    ObjectsLoader icons = new ObjectsLoader();
    Sprite iconObject; // Обявляется исходя из информации о объекте
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
        x = Integer.parseInt(opt.get("x"));
        y = Integer.parseInt(opt.get("y"));
        width = Integer.parseInt(opt.get("w"));
        height = Integer.parseInt(opt.get("h"));
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
       iconObject = new Sprite(new Texture(opt.get("icon")));
            texter.getData().setScale(Constants.scaleTextE);
            texter.setColor(Color.BLACK);
    }

    //
    public void render(SpriteBatch batch){
        fone.setBounds(x,y,width,height);
        fone.draw(batch);
        iconObject.setBounds(x+width/100*2,y+height/16,height-height/8,height-height/8);
        iconObject.draw(batch);
        texter.getData().setScale(Constants.scaleTextE);
        texter.draw(batch,name,x+100,y+80);
        texter.getData().setScale(Constants.scaleTextE-0.3f);
        texter.draw(batch,information,x+100,y+50);
        // Затраты на строительство
        icons.iconE.setBounds(x+width-width/2.7f,y+height-height/6-Constants.iconH,Constants.iconW,Constants.iconH);
        icons.iconE.draw(batch);
        texter.getData().setScale(Constants.scaleTextE-0.33f);
        texter.draw(batch,conEnergy+"",x+width-width/2.7f+22,y+height-height/6-Constants.iconH+15);
        icons.iconM.setBounds(x+width-width/2.7f+Constants.iconW*2.3f,y+height-height/6-Constants.iconH,Constants.iconW,Constants.iconH);
        icons.iconM.draw(batch);
        texter.getData().setScale(Constants.scaleTextE-0.33f);
        texter.draw(batch,conMater+"",x+width-width/2.7f+Constants.iconW*2.2f+22,y+height-height/6-Constants.iconH+15);
        icons.iconP.setBounds(x+width-width/2.7f+Constants.iconW*4.6f,y+height-height/6-Constants.iconH,Constants.iconW,Constants.iconH);
        icons.iconP.draw(batch);
        texter.getData().setScale(Constants.scaleTextE-0.33f);
        texter.draw(batch,conTrans+"",x+width-width/2.7f+Constants.iconW*4.4f+22,y+height-height/6-Constants.iconH+15);
        icons.iconR.setBounds(x+width-width/2.7f+Constants.iconW*6.9f,y+height-height/6-Constants.iconH,Constants.iconW,Constants.iconH);
        icons.iconR.draw(batch);
        texter.getData().setScale(Constants.scaleTextE-0.33f);
        texter.draw(batch,conReser+"",x+width-width/2.7f+Constants.iconW*6.6f+22,y+height-height/6-Constants.iconH+15);
        // Производство и затраты
        icons.iconE.setBounds(x+width-width/2.7f,y+height-height/6-Constants.iconH*3,Constants.iconW,Constants.iconH);
        icons.iconE.draw(batch);
        texter.getData().setScale(Constants.scaleTextE-0.33f);
        texter.draw(batch,proEnergy+"",x+width-width/2.7f+22,y+height-height/6-Constants.iconH*3+15);
        icons.iconM.setBounds(x+width-width/2.7f+Constants.iconW*2.3f,y+height-height/6-Constants.iconH*3,Constants.iconW,Constants.iconH);
        icons.iconM.draw(batch);
        texter.getData().setScale(Constants.scaleTextE-0.33f);
        texter.draw(batch,proMater+"",x+width-width/2.7f+Constants.iconW*2.2f+22,y+height-height/6-Constants.iconH*3+15);
        icons.iconP.setBounds(x+width-width/2.7f+Constants.iconW*4.6f,y+height-height/6-Constants.iconH*3,Constants.iconW,Constants.iconH);
        icons.iconP.draw(batch);
        texter.getData().setScale(Constants.scaleTextE-0.33f);
        texter.draw(batch,proTrans+"",x+width-width/2.7f+Constants.iconW*4.4f+22,y+height-height/6-Constants.iconH*3+15);
        icons.iconR.setBounds(x+width-width/2.7f+Constants.iconW*6.9f,y+height-height/6-Constants.iconH*3,Constants.iconW,Constants.iconH);
        icons.iconR.draw(batch);
        texter.getData().setScale(Constants.scaleTextE-0.33f);
        texter.draw(batch,proReser+"",x+width-width/2.7f+Constants.iconW*6.6f+22,y+height-height/6-Constants.iconH*3+15);
    }
    public void update(){

    }
    public boolean isClicked(int x,int y){ // Говорит было ли нажатие
        if(x>this.x&&x<this.x+width&&this.y<y&&this.y+height>y){
            return true;
        }
        return false;

    }
    public int getIdCreateObject(){ // Выдаёт id объекта для его создания
        return id;
    }
    public void dispose() {
        fone.getTexture().dispose();
        texter.dispose();
        iconObject.getTexture().dispose();
        icons.dispose();
    }
}
