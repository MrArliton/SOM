package com.arli.som.GameElement.Elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

public abstract class  Element { // Данный класс является общим для создания элементов прорисовки на экране
    Map<String,String> info = new HashMap<String, String>();
    public void resourse(Map<String,String> res){}
    public void render(SpriteBatch batch){}
    public void update(){}
    public void dispose(){}
    public Map<String,String> getInfo(){
        return info;
    }
}
