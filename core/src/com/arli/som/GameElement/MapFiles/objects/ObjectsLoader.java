package com.arli.som.GameElement.MapFiles.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ObjectsLoader { // Хранит все ресурсы объектов для более простого их отслеживания
    // Также управляет этими ресурсами
  public Sprite iconR = new Sprite(new Texture("All/IconR.png"));
   public Sprite iconP = new Sprite(new Texture("All/IconP.png"));
   public Sprite iconM = new Sprite(new Texture("All/IconM.png"));
   public Sprite iconE = new Sprite(new Texture("All/IconE.png"));

    public void dispose(){}
}
