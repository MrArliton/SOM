package com.arli.som.GameElement.MapFiles;

import com.arli.som.GameElement.Elements.Element;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cell { // Ячейка
    int cellID;
    int x;
    int y;
    int width;
    int heigh;
    int xC;
    int yC;
    int widthC;
    int heighC;
    int idControll = 0;
    Sprite texture;
    Sprite textureDown;
    TextureRegion[] colors;
    Sprite color;
    Object myObj;
    public boolean obj = false;
    boolean illumination = false;
    Element element;
    int effect = 0; // Номер набора эфектов
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
    public void setEffect(int idEffect){
        effect =idEffect;
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
    public void setElement(){ // Изменяет элемент ячейки

    }
    public void activeElement(){ // Активирует окно с информацией

    }
    public void deactivateElement(){ // Деактивирует окно с информацией

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
        if(idControll<4){ // Если принадлежит другой стране то перекрасить края клетки
        color.setBounds(x,y,width,heigh);
        color.draw(batch);
        }
        if(myObj!=null&&obj){
            myObj.render();
        }
    }
}
