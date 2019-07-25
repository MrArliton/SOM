package com.arli.som.GameElement.MapFiles;

import com.arli.som.GameElement.Elements.Element;
import com.arli.som.language;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class Cell { // Ячейка
    int cellID;
    public int x;
    public int y;
   public int width;
   public int heigh;
    int xC;
    int yC;
    int widthC;
    int heighC;
    int idControll = 0;
    String info = language.getText("defaultCellInfo",1);
    Sprite texture;
    Sprite textureDown;
    TextureRegion[] colors;
    Sprite color;
    Object myObj;
    public boolean obj = false;
    boolean illumination = false;
    Element element;
    boolean arme = false;
    Array<Integer> effects = new Array<Integer>(); // Номер набора эфектов
    public Cell(int cellID,int x,int y,int width,int heigh,Sprite texture,Sprite textureDown,TextureRegion[] colors){
        this.cellID = cellID;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigh = heigh;
        this.texture = texture;
        this.textureDown = textureDown;
        // Для определения нажатий
        xC =(int)width/4+x;
        yC = y;
        widthC = (int) width/2;
        heighC = heigh;
        this.colors = colors;
        color = new Sprite(colors[0]);
    }
    public void setArme(boolean b){
        arme = b;
    }
    public void setCellInfo(String info){
        this.info = info;
    }
    public String getCellInfo(){
        return info;
    }
    public void setIdControll(int idControll){
         this.idControll = idControll;
         if(idControll<4)
          color = new Sprite(colors[idControll]);
    }
    public int[] getCentr(){
        int[] otvet = new int[2];
        otvet[0] = x+width/2;
        otvet[1] = y+heigh/2;
        return otvet;
    }
    public int getIdControll(){return idControll;}
    public void addEffect(int idEffect){
        effects.add(idEffect);
    }
    public void removeEffect(int idEffect){
        effects.removeValue(idEffect,true);
    }
    public Map<String,String> getEffectsCell(){ // Собирает все эффекты с клетки и возвращает их
        Map<String,String> effectList = new HashMap<String, String>();


        return effectList;
    }
    public void setCellTexture(Sprite texture){
        this.texture = texture;
    }
    public void setCellTextureDown(Sprite texture){
        this.textureDown = texture;
    }
    public void setObject(Object obj){
        this.obj = true;
        if(myObj!=null)
        myObj.dispose();
        myObj = obj;
    }
    public void activateIllumination(){ // Активирует подстветку
        illumination = true;
    }
    public void deactivateIllumination(){ // Деактивирует подсветку
        illumination = false;
    }

    public int getCellID(){
        return cellID;
    }
    public int[] clickMe(int x,int y){
        int[] a = new int[2];
        System.out.println("Cell x:"+xC+" y"+yC+" w"+(widthC+xC)+" h"+(heighC+yC));
        if(x>xC&&x<widthC+xC&&y>yC&&y<heighC+yC){
            a[0] = 1;
            a[1] = cellID;
            return a;
        }
        a[0] = 0;
        a[1] = cellID;
        return a;
    }
    public Object getObject(){
        return myObj;
    }
    public void removeObj(){
        obj = false;
        if(myObj!=null)
        myObj.dispose();
        myObj = null;
    }
    public void render(SpriteBatch batch){
        if(illumination){
            textureDown.setBounds(x,y,width,heigh);
            textureDown.draw(batch);
        }else{
            texture.setBounds(x,y,width,heigh);
            texture.draw(batch);
        }
        if(idControll<5){ // Если принадлежит другой стране то перекрасить края клетки
        color.setBounds(x,y,width,heigh);
        color.draw(batch);
        }
        if(myObj!=null&&obj){
            myObj.update();
            myObj.render(batch);
        }
    }
    @Override
    public String toString(){
        return "id:"+cellID;
    }
}
