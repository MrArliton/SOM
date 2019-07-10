package com.arli.som.GameElement;

import com.arli.som.Constants;
import com.arli.som.language;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD {
    OrthographicCamera cameraHUD;
    public Viewport view = new StretchViewport(Constants.width, Constants.heigth);
    MapCon controll;
    Sprite text = new Sprite(new Texture("HUD/InfoPanel.png"));
    Sprite iconE = new Sprite(new Texture("All/IconE.png"));
    Sprite iconM= new Sprite(new Texture("All/IconM.png"));
    Sprite iconT= new Sprite(new Texture("All/IconP.png"));
    Sprite iconR= new Sprite(new Texture("All/IconR.png"));
    BitmapFont texter = new BitmapFont(Gdx.files.internal("myFont.fnt"));
    InfoCountry infoCountry;
    public HUD(MapCon conM,InfoCountry infoCountry) {
        this.infoCountry = infoCountry;
        cameraHUD = new OrthographicCamera(Constants.width,Constants.heigth);
        cameraHUD.setToOrtho(false,Constants.width,Constants.heigth);
        view.setCamera(cameraHUD);
            controll = conM;
    }
    private String stringP(int i){
        String otvet = "";
        for(int o = 0;o<i;o++){
            otvet+=" ";
        }
        return otvet;
    }
    public void render(SpriteBatch batch){
        text.setBounds(20,Constants.heigth-60,680,60);
        String buffer = "";
        batch.setProjectionMatrix(cameraHUD.combined);
        text.draw(batch);
        texter.getData().setScale(1.2f);
        iconE.setBounds(30,Constants.heigth-Constants.yIconZdvig,Constants.IconW,Constants.IconH);
        iconE.draw(batch);
        texter.draw(batch, (int)Float.parseFloat(infoCountry.getInfoCountry(1,"energy"))+"",20+Constants.IconW+18,Constants.heigth-Constants.yIconZdvig+35);
        texter.getData().setScale(0.6f);
        texter.draw(batch, stringP(infoCountry.getInfoCountry(1,"energy").length())+" +"+infoCountry.getInfoCountry(1,"energyE"),20+Constants.IconW+21,Constants.heigth-Constants.yIconZdvig+50);
        texter.getData().setScale(1.2f);
        iconM.setBounds(30+Constants.xIconZdvig,Constants.heigth-Constants.yIconZdvig,Constants.IconW,Constants.IconH);
        iconM.draw(batch);
        texter.draw(batch,(int)Float.parseFloat(infoCountry.getInfoCountry(1,"matter"))+"",20+Constants.IconW+18+Constants.xIconZdvig,Constants.heigth-Constants.yIconZdvig+35);
        texter.getData().setScale(0.6f);
        texter.draw(batch, stringP(infoCountry.getInfoCountry(1,"matter").length())+" +"+infoCountry.getInfoCountry(1,"matterE"),20+Constants.IconW+21+Constants.xIconZdvig,Constants.heigth-Constants.yIconZdvig+50);
        texter.getData().setScale(1.2f);
        iconT.setBounds(30+2*Constants.xIconZdvig,Constants.heigth-Constants.yIconZdvig,Constants.IconW,Constants.IconH);
        iconT.draw(batch);
        texter.draw(batch,(int)Float.parseFloat(infoCountry.getInfoCountry(1,"transformation"))+"",20+Constants.IconW+18+2*Constants.xIconZdvig,Constants.heigth-Constants.yIconZdvig+35);
        texter.getData().setScale(1.2f);
        iconR.setBounds(30+3*Constants.xIconZdvig,Constants.heigth-Constants.yIconZdvig,Constants.IconW,Constants.IconH);
        iconR.draw(batch);
        texter.draw(batch,(int)Float.parseFloat(infoCountry.getInfoCountry(1,"research"))+"",20+Constants.IconW+18+3*Constants.xIconZdvig,Constants.heigth-Constants.yIconZdvig+35);
        texter.getData().setScale(0.6f);
        texter.draw(batch, stringP(infoCountry.getInfoCountry(1,"researchE").length())+" +"+infoCountry.getInfoCountry(1,"researchE"),20+Constants.IconW+21+3*Constants.xIconZdvig,Constants.heigth-Constants.yIconZdvig+50);
        texter.getData().setScale(0.5f);
        texter.draw(batch,language.getText("Month",1)+"-"+infoCountry.getInfoCountry(-1,"month"),Constants.IconW+4*Constants.xIconZdvig-10,Constants.heigth-Constants.yIconZdvig+38);
        texter.draw(batch,language.getText("Day", 1)+"-"+infoCountry.getInfoCountry(-1,"day"),Constants.IconW+4*Constants.xIconZdvig-10,Constants.heigth-Constants.yIconZdvig+18);
    }


    public void update(float delta){

    }
    public boolean clickMe(int x,int y){
        return false;
    }
    public void editPositionMap(){ // Действия при сдвиге карты

    }
    public void resize(int width,int height){
        view.update(width,height);
    }

    public void dispose(){
        texter.dispose();
        text.getTexture().dispose();
        iconE.getTexture().dispose();
        iconM.getTexture().dispose();
        iconT.getTexture().dispose();
        iconR.getTexture().dispose();
    }
}
