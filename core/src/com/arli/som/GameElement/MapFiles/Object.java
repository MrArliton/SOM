package com.arli.som.GameElement.MapFiles;

import com.arli.som.GameElement.Elements.Element;
import com.arli.som.GameElement.InfoCountry;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

public abstract class Object { // Данный класс содержит основу для объектов
    int idObject;
   public Element element;
   public InfoCountry infoRes;
    Map<String,String> effects = new HashMap<String, String>();
   public Map<String,String> info = new HashMap<String, String>();
   public Element getElement(int id){return element;}
    public void render(SpriteBatch batch){}
    public void update(){}
    public void activate(int num){}
    public Map<String,String> getEffects(){
       return effects;
    }
    public void dispose(){}
}
