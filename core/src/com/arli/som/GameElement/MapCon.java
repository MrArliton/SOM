package com.arli.som.GameElement;

import com.arli.som.GameElement.Elements.Element;
import com.arli.som.GameElement.MapFiles.Cell;
import com.arli.som.GameElement.MapFiles.Object;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Map;

public interface MapCon { // Основные методы взаимодействия
     boolean createObject(int idCell, Object obj);
     boolean removeObject(int idCell);
     Map<String,String> getObjectInfo(int idCell);
     boolean editObject(int idCell,Map<String,String> edits);
     int getCellID(int x,int y);
     Cell getCell(int idCell);
     boolean clickMap(int x,int y);
     void clearDefaultWindows();
     void createWindow(int idCell, Element element);
     void removeElementWindow();
     void update(float delta);
}
