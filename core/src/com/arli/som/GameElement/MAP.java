package com.arli.som.GameElement;

import com.arli.som.Constants;
import com.arli.som.GameElement.MapFiles.Cell;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MAP { // Карта
    // Тут  обрабатываем все изменения в карте
    OrthographicCamera cameraMap;
    String test = "1,1,2;1,4,2";
    Array<Cell> cells = new Array<Cell>();
    Viewport view = new FitViewport(Constants.width,Constants.heigth);
    public MAP(String mapPath) { // Тут Получаем карту в txt формате
        cameraMap = new OrthographicCamera(Constants.width,Constants.heigth);
        cameraMap.setToOrtho(false,Constants.width,Constants.heigth);

        view.setCamera(cameraMap);
        view.apply();

        generateCells(test); // Текст из txt
    }
    public void generateCells(String map){ // генерируем карту
        String[] buffer = map.split(";");
        int buff1 = 1;
        for(int i = 0;i<buffer.length;i++){
            for(int o = 0;o<Integer.parseInt(buffer[i].split(",")[2]);o++){
                if(Integer.parseInt(buffer[i].split(",")[1])%2!=0)
                if(o%2==0)
                cells.add(new Cell(buff1,30+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,100+Constants.cellH/Constants.cellDvigY+Constants.cellH*Integer.parseInt(buffer[i].split(",")[0]),Constants.cellW,Constants.cellH,new Sprite(new Texture("MAP/cell_defaut.png"))));
            else
                    cells.add(new Cell(buff1,30+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,100+Constants.cellH*Integer.parseInt(buffer[i].split(",")[0]),Constants.cellW,Constants.cellH,new Sprite(new Texture("MAP/cell_defaut.png"))));
                else
                if(o%2!=0)
                    cells.add(new Cell(buff1,30+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,100+Constants.cellH/Constants.cellDvigY+Constants.cellH*i,Constants.cellW,Constants.cellH,new Sprite(new Texture("MAP/cell_defaut.png"))));
                else
                    cells.add(new Cell(buff1,30+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,100+Constants.cellH*Integer.parseInt(buffer[i].split(",")[0]),Constants.cellW,Constants.cellH,new Sprite(new Texture("MAP/cell_defaut.png"))));
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

    public void update(float delta){}


    public void resize(int width,int height){
        view.update(width,height);
        view.apply();
    }
    public void setZoom(float initialDistance, float distance){ // Устанавливает зоом

    }
    public void dispose(){

    }
}
