package com.arli.som.GameElement;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Map;

public class MapController implements MapCon { // Управляет картой
MAP map;
    public MapController(MAP map) {
        this.map = map;
    }

    @Override
    public boolean createObject(int idCell, Map<String, String> option) {
        return false;
    }

    @Override
    public boolean removeObject(int idCell) {
        return false;
    }

    @Override
    public Map<String, String> getObjectInfo(int idCell) {
        return null;
    }

    @Override
    public boolean editObject(int idCell, Map<String, String> edits) {
        return false;
    } // Отвечает за все реализации взаимодействия Ии и игроков с игрой
}
