package com.arli.som.GameElement.MapFiles;

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
    Sprite texture;
    Object myObj = new Object();
    public Cell(int cellID,int x,int y,int width,int heigh,Sprite texture){
        this.cellID = cellID;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigh = heigh;
        this.texture = texture;
        // Для определения нажатий
        xC =(int) 1/4*width+x;
        yC = y;
        widthC = (int) 2/4*width;
        heighC = heigh;
    }
    public void setCellTexture(Sprite texture){
        this.texture = texture;
    }
    public void setObject(Object obj){
        myObj.dispose();
        myObj = obj;
    }
    public int getCellID(){
        return cellID;
    }
    public int[] clickMe(int x,int y){
        int[] a = new int[2];
        if(x>xC&&x<widthC&&y>yC&&y<heighC){
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
        myObj.dispose();
        myObj = new Object();
    }
    public void render(SpriteBatch batch){
        texture.setBounds(x,y,width,heigh);
        texture.draw(batch);
    }
}
