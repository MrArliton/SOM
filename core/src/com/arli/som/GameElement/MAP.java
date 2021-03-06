package com.arli.som.GameElement;

import com.arli.som.Constants;
import com.arli.som.GameElement.Elements.CellInformation;
import com.arli.som.GameElement.Elements.Element;
import com.arli.som.GameElement.MapFiles.Cell;
import com.arli.som.GameElement.MapFiles.objects.CentralSystem;
import com.arli.som.GameElement.MapFiles.objects.ObjectsLoader;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class MAP { // Карта
    // Тут  обрабатываем все изменения в карте
    Sprite foneWIC = new Sprite(new Texture("MAP/foneWIC.png"));
   public OrthographicCamera cameraMap;
    Map<String,String> buffer = new HashMap<String, String>();
    String test = "1,1,16;30,4,2";
   public Array<Cell> cells = new Array<Cell>();
   public Viewport view = new ScreenViewport();
   Array<Element> elements = new Array<Element>();
   Array<Integer> ids = new Array<Integer>();
   ObjectsLoader loaderObjectsT  = new ObjectsLoader();
   // Атласы
    Sprite fone = new Sprite(new Texture("MAP/Fone.jpg"));
    TextureRegion[] textureCells = new TextureRegion(new Texture("MAP/MapAtlas.png")).split(Constants.cellTW,Constants.cellTH)[0];
    TextureRegion[] textureCellsColors = new TextureRegion(new Texture("MAP/ColorAtlasMap.png")).split(Constants.cellTW,Constants.cellTH)[0];
    InfoCountry infoCountry;
    public MAP(String mapPath,InfoCountry infoCountry) { // Тут Получаем карту в txt формате
        cameraMap = new OrthographicCamera(Constants.width,Constants.heigth);
        cameraMap.setToOrtho(false,Constants.width,Constants.heigth);
            this.infoCountry = infoCountry;
        view.setCamera(cameraMap);

        generateCells(mapPath); // Текст из txt
        cameraMap.zoom = 0.8f;
    }
    public void generateDefaultObjects(){ // Генерация начальных объектов карты
        // Также захват стандартных територий

    }
    private boolean notIdsElement(int id){
        for(int i = 0;i<ids.size;i++){
            if(ids.get(i)==id){
                return false;
            }
        }
        return true;
    }
    public int activateWindowInfoCell(int idCell,int country){ // Активирует окно для игрока относительно карты
        int id = 0;
            while (!notIdsElement(id)) {
                id = (int) (Math.random() * 100);
            }
            ids.add(id);
            elements.add(new CellInformation(foneWIC, "build", new TextureRegion(new Texture("MAP/WICBAtlas.png")).split(Constants.cellBW, Constants.cellBH)[0], getCell(idCell), id));
        return id;
    }
    public int activateWindowObject(int idCell,int country){
        int id = 0;
            while (!notIdsElement(id)) {
                id = (int) (Math.random() * 100);
            }
            if(getCell(idCell).obj) {
                elements.add(getCell(idCell).getObject().getElement(id));
                ids.add(id);
            }else{
                id =-1;
            }
        return id;
    }
    public int activateElement(Element element){ // Активирует элемент
        int id = 0;
        while (!notIdsElement(id)) {
            id = (int) (Math.random() * 100);
        }
            element.setId(id);
            elements.add(element);
            ids.add(id);
        return id;
    }
    public Map<String,String> getInfoWindow(int id){
        for(int i = 0;i<elements.size;i++){
            try {
                if (Integer.parseInt(elements.get(i).getInfo().get("id")) == id) {
                    return elements.get(i).getInfo();
                }
            }catch (Exception e){}
            return null;
        }
        return null;
    }

    public boolean removeWindow(int id){
        for(int i = 0;i<elements.size;i++){
            if(Integer.parseInt(elements.get(i).getInfo().get("id"))==id){
                elements.get(i).dispose();
                elements.removeIndex(i);
                ids.removeValue(id,true);
                return true;
            }
        }
        return false;
    }
    public boolean clickWindow(int x,int y){ //Проверка было ли нажатие на одно из окон на карте
        for(int i = 0;i<elements.size;i++){
            try {
                if (elements.get(i).getInfo().get("type").equalsIgnoreCase("window")) { // Если тип окно то
                    try {
                        if (Integer.parseInt(elements.get(i).getInfo().get("x")) < x && Integer.parseInt(elements.get(i).getInfo().get("w")) + Integer.parseInt(elements.get(i).getInfo().get("x")) > x && Integer.parseInt(elements.get(i).getInfo().get("y")) < y && Integer.parseInt(elements.get(i).getInfo().get("h")) + Integer.parseInt(elements.get(i).getInfo().get("y")) > y) { // Проверяем условия
                            if (elements.get(i).getInfo().containsKey("res")) {
                                String[] b = elements.get(i).getInfo().get("res").split(",");
                                for (String a : b) {
                                    if (a.equalsIgnoreCase("button")) {
                                        buffer.clear();
                                        buffer.put("button", +x + "," + y);
                                        elements.get(i).resourse(buffer);
                                    }
                                }
                            }
                            return true;
                        }
                    } catch (NumberFormatException exp) {
                    }
                }
            }catch(NullPointerException exception){
                if(Constants.debug)
                exception.printStackTrace();
            }
        }
        return false;
    }
    public Map<String,String> getEffects(int country){
        Map<String,String> effects = new HashMap<String, String>();
        effects.put("m","0");
        effects.put("e","0");
        effects.put("t","0");
        effects.put("r","0");
        for(int i = 0;i<cells.size;i++){
            if(cells.get(i).getIdControll()==country){
                if(cells.get(i).obj){
                    try{
                        effects.put("m",Float.parseFloat(effects.get("m"))+Float.parseFloat(cells.get(i).getObject().info.get("m"))+"");
                        effects.put("e",Float.parseFloat(effects.get("e"))+Float.parseFloat(cells.get(i).getObject().info.get("e"))+"");
                        effects.put("t",Float.parseFloat(effects.get("t"))+Float.parseFloat(cells.get(i).getObject().info.get("t"))+"");
                        effects.put("r",Integer.parseInt(effects.get("r"))+Integer.parseInt(cells.get(i).getObject().info.get("r"))+"");
                    }catch(NullPointerException exc){}
                }
            }
        }


        return effects;
    }
    public void generateCells(String map){ // генерируем карту
        FileHandle file = Gdx.files.internal("Maps/"+map);
        map = file.readString();
        String[] buffer = map.split(";");
        int buff1 = 1;
        for(int i = 0;i<buffer.length;i++){
            for(int o = 0;o<Integer.parseInt(buffer[i].split(",")[2]);o++){
                if(Integer.parseInt(buffer[i].split(",")[1])%2!=0)
                if(o%2==0)
                cells.add(new Cell(buff1,3+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,100+Constants.cellH/Constants.cellDvigY+Constants.cellH*Integer.parseInt(buffer[i].split(",")[0]),Constants.cellW,Constants.cellH,new Sprite(textureCells[0]),new Sprite(textureCells[1]),textureCellsColors));
            else
                    cells.add(new Cell(buff1,3+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,100+Constants.cellH*Integer.parseInt(buffer[i].split(",")[0]),Constants.cellW,Constants.cellH,new Sprite(textureCells[0]),new Sprite(textureCells[1]),textureCellsColors));
                else
                if(o%2!=0)
                    cells.add(new Cell(buff1,3+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,100+Constants.cellH/Constants.cellDvigY+Constants.cellH*Integer.parseInt(buffer[i].split(",")[0]),Constants.cellW,Constants.cellH,new Sprite(textureCells[0]),new Sprite(textureCells[1]),textureCellsColors));
                else
                    cells.add(new Cell(buff1,3+Constants.cellDvigX*Integer.parseInt(buffer[i].split(",")[1])+Constants.cellDvigX*o,100+Constants.cellH*Integer.parseInt(buffer[i].split(",")[0]),Constants.cellW,Constants.cellH,new Sprite(textureCells[0]),new Sprite(textureCells[1]),textureCellsColors));
                buff1+=1;
            }

        }
        // debug
        if(Constants.debug) {
            cells.get(5).setIdControll(1);
            Map<String, String> options = new HashMap<String, String>();
            options.put("idControll", "" + 1);
            options.put("health", "" + 10);
            options.put("shield", "" + 10);
            options.put("regenerationHealth", "" + 10);
            options.put("extractionEnergy", "" + 5);
            options.put("extractionMatter", "" + 5);
            options.put("researchPoints", "" + 1);
            options.put("transformationMatter", "" + 3);
            options.put("level", "" + 0);
            options.put("month", "" + 1);
            options.put("sprite", "Objects/central.png");
            cells.get(5).setObject(new CentralSystem(options, cells.get(5), loaderObjectsT, infoCountry));

            cells.get(6).setIdControll(1);

        }
        //
    }
    public void render(SpriteBatch batch){
        batch.setProjectionMatrix(cameraMap.combined);
        fone.setBounds(-100,-100,Constants.width+200,Constants.heigth+100);
        fone.setAlpha(0.7f);
        fone.draw(batch);
        for(int i =0;i<cells.size;i++){
            cells.get(i).render(batch);
        }
        for(int i = 0;i<elements.size;i++){
            elements.get(i).render(batch);
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
        for(int i = 0;i<elements.size;i++){
            elements.get(i).update();

        }

    }
    private void ElementRes(Element element){

    }

    public void resize(int width,int height){
        view.update(width,height);
        view.apply();
    }
    public void setZoom(float initialDistance, float distance){ // Устанавливает зоом
           float a = initialDistance/distance - 1f + cameraMap.zoom;
           float correct = Math.max(1-(float)Constants.heigth/Constants.resolutionWindowH,1-(float)Constants.width/Constants.resolutionWindowW)+0.05f;
           if(correct<0){
               correct = 0;
           }
           a = Math.max(0.3f,Math.min(a,1f-correct));
           cameraMap.zoom=a;
           cameraMap.update();
    }
    public void dispose(){
        textureCells[0].getTexture().dispose();
        textureCellsColors[0].getTexture().dispose();
    }
}
