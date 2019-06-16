package com.arli.som.GameElement;

import com.arli.som.Constants;
import com.arli.som.GameElement.MapFiles.Cell;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MAP { // Карта
    // Тут  обрабатываем все изменения в карте
   public OrthographicCamera cameraMap;
    String test = "1,2,2;2,4,2";
   public Array<Cell> cells = new Array<Cell>();
   public Viewport view = new ScreenViewport();
    public MAP(String mapPath) { // Тут Получаем карту в txt формате
        cameraMap = new OrthographicCamera(Constants.width,Constants.heigth);
        cameraMap.setToOrtho(false,Constants.width,Constants.heigth);

        view.setCamera(cameraMap);

        generateCells(test); // Текст из txt
        cameraMap.zoom = 0.8f;
    }
    public void generateDefaultObjects(){ // Генерация начальных объектов карты
        // Также захват стандартных територий

    }
    public void generateCells(String map){ // генерируем карту
        String[] buffer = map.split(";");
        int buff1 = 1;
        for(int i = 0;i<buffer.length;i++){
            for(int o = 0;o<Integer.parseInt(buffer[i].split(",")[2]);o++){
                if(Integer.parseInt(buffer[i].split(",")[1])%2!=0)
                if(o%2==0)
                cells.add(new Cell(buff1,10+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,50+Constants.cellH/Constants.cellDvigY+Constants.cellH*Integer.parseInt(buffer[i].split(",")[0]),Constants.cellW,Constants.cellH,new Sprite(new Texture("MAP/cell_defaut.png"))));
            else
                    cells.add(new Cell(buff1,10+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,50+Constants.cellH*Integer.parseInt(buffer[i].split(",")[0]),Constants.cellW,Constants.cellH,new Sprite(new Texture("MAP/cell_defaut.png"))));
                else
                if(o%2!=0)
                    cells.add(new Cell(buff1,10+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,50+Constants.cellH/Constants.cellDvigY+Constants.cellH*Integer.parseInt(buffer[i].split(",")[0]),Constants.cellW,Constants.cellH,new Sprite(new Texture("MAP/cell_defaut.png"))));
                else
                    cells.add(new Cell(buff1,10+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,50+Constants.cellH*Integer.parseInt(buffer[i].split(",")[0]),Constants.cellW,Constants.cellH,new Sprite(new Texture("MAP/cell_defaut.png"))));
                buff1+=1;
            }

        }
        for(int i = 0;i<cells.size;i++){
           System.out.println( cells.get(i).getCellID());
        }
    }
    public void render(SpriteBatch batch){
        batch.setProjectionMatrix(cameraMap.combined);
        for(int i =0;i<cells.size;i++){
            cells.get(i).render(batch);
        }
    }
    public Cell getCell(int id){
        for(int i = 0;i<cells.size;i++){
            if(cells.get(i).getCellID()==id) {
                return cells.get(i);
            }
        }
        return null;
    }
    public void update(float delta){
        Vector2 min = new Vector2(cameraMap.viewportWidth-cameraMap.viewportWidth+Constants.width,cameraMap.viewportHeight);
        min.scl(cameraMap.zoom/2);
        Vector2 max = new Vector2(Constants.width,Constants.heigth);
        max.sub(min);
        float x = Math.min(max.x, Math.max(cameraMap.position.x, min.x));
        float y = Math.min(max.y, Math.max(cameraMap.position.y, min.y));

        cameraMap.position.set(x,y,0);
        cameraMap.update();
    }


    public void resize(int width,int height){
        view.update(width,height);
        view.apply();
    }
    public void setZoom(float initialDistance, float distance){ // Устанавливает зоом
           float a = initialDistance/distance - 1f + cameraMap.zoom;
           a = Math.max(0.3f,Math.min(a,1f));
           cameraMap.zoom=a;
           cameraMap.update();
    }
    public void dispose(){

    }
}
