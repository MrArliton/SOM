package com.arli.som.GameElement.MapFiles;

import com.arli.som.GameElement.Elements.Element;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    Object myObj = new Object();
    boolean obj = false;
    boolean illumination = false;
    Element element;
    public Cell(int cellID,int x,int y,int width,int heigh,Sprite texture){
        this.cellID = cellID;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigh = heigh;
        this.texture = texture;
        // Для определения нажатий
        xC =(int)width/4+x;
        yC = y;
        widthC = (int) width/2;
        heighC = heigh;
    }
    public void setCellTexture(Sprite texture){
        this.texture = texture;
    }
    public void editIdControll(int nowIdControll){
        idControll = nowIdControll;
    }
    public void setObject(Object obj){
        this.obj = true;
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
        myObj.dispose();
        myObj = new Object();
    }
    public void render(SpriteBatch batch){
        if(illumination){

        }else{
            texture.setBounds(x,y,width,heigh);
            texture.draw(batch);
        }
    }
}
