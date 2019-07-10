package com.arli.som.GameElement.MapFiles.objects;

import com.arli.som.Constants;
import com.arli.som.GameElement.Elements.CentralElement;
import com.arli.som.GameElement.Elements.Element;
import com.arli.som.GameElement.InfoCountry;
import com.arli.som.GameElement.MapFiles.Cell;
import com.arli.som.GameElement.MapFiles.Object;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class CentralSystem extends Object {
    Sprite central;
    int idControll;
    // Параметры объекта
    float health;
    float shield;
    float regenerationHealth;
    int level = 0;
    // Добыча ресурсов
    float extractionEnergy; // Добыча энергии
    float extractionMatter; // Добыча материи
    int researchPoints; // Исследовательские поинты
    float transformationMatter; // Зависит скорость строительства и востонавления объектов
    float transformation;
    boolean transformationOn;
    int upgradeMonths;
    int buffer;
    BitmapFont texter = new BitmapFont(Gdx.files.internal("myFont.fnt"));
    Cell cell;
    ObjectsLoader loader;
    InfoCountry infoCountry;
    int lastMonth = 0;
    public CentralSystem(Map<String,String> options, Cell cell, ObjectsLoader loader, InfoCountry infoCountry) {
        this.cell = cell;
        this.loader = loader;
        this.infoCountry = infoCountry;
        idControll = Integer.parseInt(options.get("idControll"));
        health = Integer.parseInt(options.get("health"));
        shield = Integer.parseInt(options.get("shield"));
        regenerationHealth= Integer.parseInt(options.get("regenerationHealth"));
        extractionEnergy = Integer.parseInt(options.get("extractionEnergy"));
        extractionMatter = Integer.parseInt(options.get("extractionMatter"));
        researchPoints = Integer.parseInt(options.get("researchPoints"));
        transformationMatter = Integer.parseInt(options.get("transformationMatter"));
        level = Integer.parseInt(options.get("level"));
        upgradeMonths = Integer.parseInt(options.get("month"));
        central = new Sprite(new Texture("Map/"+options.get("sprite")));
        infoRes = infoCountry;
        transformation = transformationMatter;
    }
    @Override
    public Element getElement(int id){
        Map<String,String> infoElement = new HashMap<String, String>();
        //
        infoElement.put("eU", Constants.UpgEnergyZ +"");
        infoElement.put("exU",Constants.UpgMaterZ+"");
        infoElement.put("tU",Constants.UpProZ+"");
        //
                infoElement.put("h",health+"");
                        infoElement.put("s",shield+"");
                                infoElement.put("r",regenerationHealth+"");
                                    infoElement.put("e",extractionEnergy+"");
                                                infoElement.put("ex",extractionMatter+"");
                                                        infoElement.put("re",researchPoints+"");
                                                                infoElement.put("t",transformation+"");
                                                                        infoElement.put("l",level+"");
        if(isUpgrade()){
            infoElement.put("upgrade","true");
        }else{
            infoElement.put("upgrade","false");
        }
        if(transformationOn){
            infoElement.put("upgradeTime",upgradeMonths-buffer+"");
        }else{
            infoElement.put("upgradeTime",-1+"");
        }
        element = new CentralElement(loader,new TextureRegion(new Texture("MAP/WICBAtlas.png")).split(100,30)[0],this,infoElement,cell,id,cell.getCellID());
        return element;
    }
    public void activateUpgrade(){
        transformation = transformationMatter;
        transformationMatter =transformationMatter -(Constants.UpProZ * ((level+1) * Constants.levelUpObjRes));
        System.out.println("transformationMatter - "+transformationMatter);
        transformationOn = true;
        buffer = 0;
        lastMonth = Integer.parseInt(infoCountry.getInfoCountry(-1,"month"));
        infoCountry.putInfoCountryPlus(1, "energy", -(Constants.UpgEnergyZ * ((level+1) * Constants.levelUpObjRes)) + "");
        infoCountry.putInfoCountryPlus(1, "matter", -(Constants.UpgMaterZ * ((level+1) * Constants.levelUpObjRes)) + "");
        // Обновляем информацию
        info.put("e",extractionEnergy+"");
        info.put("m",extractionMatter+"");
        info.put("r",researchPoints+"");
        info.put("t",transformationMatter+"");
        //
    }
    @Override
    public void activate(int num){ // Если апгрейд
        if(num == 1){
            if(isUpgrade())
                // Временно
            activateUpgrade();
        }
    }
     public boolean isUpgrade(){
        if(Float.parseFloat(infoCountry.getInfoCountry(1,"energy"))>(Constants.UpgEnergyZ*((level+1)*Constants.levelUpObjRes))){
            if(Float.parseFloat(infoCountry.getInfoCountry(1,"matter"))>(Constants.UpgMaterZ*((level+1)*Constants.levelUpObjRes))){
                if(Float.parseFloat(infoCountry.getInfoCountry(1,"transformation"))>(Constants.UpProZ*((level+1)*Constants.levelUpObjRes))){
                    if(!transformationOn)
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void render(SpriteBatch bacth) {
        super.render(bacth);
        central.setBounds(cell.x,cell.y,cell.width,cell.heigh);
        central.draw(bacth);
    }
public void upgradeFinish(){}
    @Override
    public void update() {
        super.update();
        info.put("e",extractionEnergy+"");
        info.put("m",extractionMatter+"");
        info.put("r",researchPoints+"");
        info.put("t",transformationMatter+"");
        if(transformationOn){
            if(buffer>upgradeMonths*(level+1)){
                buffer = 0;
                transformationOn = false;
                transformationMatter = transformation;
                upgradeFinish();
                    level += 1;
                    extractionEnergy *= Constants.levelUpObj;
                    extractionMatter *= Constants.levelUpObj;
                    researchPoints *= Constants.levelUpObj;
                    transformationMatter *= Constants.levelUpObj;
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }else{
                if(lastMonth!=Integer.parseInt(infoCountry.getInfoCountry(-1,"month"))){
                    buffer++;
                    lastMonth = Integer.parseInt(infoCountry.getInfoCountry(-1,"month"));
                }
            }
        }
    }
    @Override
    public void dispose() { // Уничтожает особые ресырсы именно данного объект
        super.dispose();
        texter.dispose();
    } // Главная клетка каждой страны
}
