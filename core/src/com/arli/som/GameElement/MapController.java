package com.arli.som.GameElement;

import com.arli.som.GameElement.MapFiles.Cell;
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

    @Override
    public int getCellID(int x, int y) { // Находит id нажатой клетки
        for(int i = 0;i<map.cells.size;i++){
            if(map.cells.get(i).clickMe(x,y)[0]==1){
               return map.cells.get(i).clickMe(x,y)[1];
            }
        }
        return 0;
    }

    @Override
    public boolean clickMap(int x, int y) { // Действия при нажатии на кнопку
        // Активация подсветки клетки
        // Также активируется окно информации о клетке
        int a = getCellID(x,y);
        Cell cell = map.getCell(a);
        if(cell!=null){
            clearDefaultWindows();
            cell.activateIllumination();
        }
        return false;
    }

    @Override
    public void clearDefaultWindows() { // Скрывает все окна
        // Выключение подсветки на всех клетках
        for(int i = 0;i<map.cells.size;i++){
            map.cells.get(i).deactivateIllumination();
        }
    }
}
