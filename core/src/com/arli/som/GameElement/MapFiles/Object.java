package com.arli.som.GameElement.MapFiles;

import com.arli.som.GameElement.Elements.Element;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Object { // Данный класс содержит основу для объектов
    int idObject;
   public Element element;

   public Element getElement(int id){return element;}
    public void render(SpriteBatch batch){}
    public void update(){}

    public void dispose(){}
}
