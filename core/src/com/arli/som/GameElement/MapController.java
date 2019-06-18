package com.arli.som.GameElement;

import com.arli.som.GameElement.MapFiles.Cell;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Map;

public class MapController implements MapCon { // Управляет картой
MAP map;
int idInfoCell = -1;
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
    public Cell getCell(int idCell) {
      return map.getCell(idCell);
    }

    public int createWindowInfoCell(int idCell,int country){
     int id = map.activateWindowInfoCell(idCell,country);
     return id;
    }

    @Override
    public boolean clickMap(int x, int y) { // Действия при нажатии на кнопку
        // Активация подсветки клетки
        // Также активируется окно информации о клетке
        if(!map.clickWindow(x,y)) {
            clearDefaultWindows();
            int a = getCellID(x, y);
            Cell cell = map.getCell(a);
            if (cell != null&&!cell.obj) {
                cell.activateIllumination();
                idInfoCell = createWindowInfoCell(a, 1); // 1 country - player id
                return true;
            }
        }else{ // Если клик прошёл по карте
            if(idInfoCell!=-1) { // Если активно окно то проверим его
                try {
                    if (Integer.parseInt(map.getInfoWindow(idInfoCell).get("press")) == 1) { // Активируем окно строительства для данной клетки

                        clearDefaultWindows();
                    }
                } catch (NumberFormatException exp){}
            }
            return true;
        }
        return false;
    }

    @Override
    public void clearDefaultWindows() { // Скрывает все окна
        // Выключение подсветки на всех клетках
        for(int i = 0;i<map.cells.size;i++){
            map.cells.get(i).deactivateIllumination();
            if(idInfoCell!=-1) {
                map.removeWindow(idInfoCell);
                idInfoCell = -1;
            }
        }
    }

    @Override
    public void createWindowObject(int idCell) {

    }
}
