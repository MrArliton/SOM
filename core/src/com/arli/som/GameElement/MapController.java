package com.arli.som.GameElement;

import com.arli.som.Constants;
import com.arli.som.GameElement.Elements.Element;
import com.arli.som.GameElement.Elements.WindowBuild;
import com.arli.som.GameElement.MapFiles.Cell;
import com.arli.som.GameElement.MapFiles.Object;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Map;

public class MapController implements MapCon { // Управляет картой
MAP map;
HUD hud;
int idInfoCell = -1;
int idWindow = -1;
Array<Element> bufferWin = new Array<Element>();
    public MapController(MAP map,HUD hud)
    {
        this.map = map;
        this.hud = hud;
    }

    @Override
    public boolean createObject(int idCell, Object obj) {

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
            }else if(cell!=null&&cell.obj){ // Если у ячейки есть объект
                cell.activateIllumination();
                idInfoCell = map.activateWindowObject(a,1);

            }
        }else{ // Если клик прошёл по карте
            if(idInfoCell!=-1) { // Если активно окно то проверим его
                try {
                    if (Integer.parseInt(map.getInfoWindow(idInfoCell).get("press")) == 1) { // Активируем окно строительства для данной клетки
                        if(map.getInfoWindow(idInfoCell).get("buttonEffect").equalsIgnoreCase("upgrade")){ // Выполняем улучшение
                            map.getCell(Integer.parseInt(map.getInfoWindow(idInfoCell).get("cell"))).getObject().activate(1); // Активируем улучшение объекта
                        }else if(map.getInfoWindow(idInfoCell).get("buttonEffect").equalsIgnoreCase("build")){
                          createWindow(idInfoCell,new WindowBuild(getCell(idInfoCell),map.infoCountry,this)); // пЕРЕ
                        }
                        clearDefaultWindows();
                    }
                } catch (NumberFormatException exp){}
            }else if(idWindow!=-1){ // Если активно окно

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
    public void createWindow(int idCell,Element element) {
                if(idWindow == -1){
                    idWindow = hud.activateElement(element);
                }else{
                    bufferWin.add(element);
                }
    }

    @Override
    public void removeElementWindow() {
        hud.removeElement(idWindow);
        idWindow = -1;
    }

    @Override
    public void update(float delta) {
        if(bufferWin.size>0){
            if(idWindow==-1){
                idWindow = hud.activateElement(bufferWin.pop());

            }
        }
    }
}
