package com.arli.som.GameElement.Elements;

import com.arli.som.Constants;
import com.arli.som.GameElement.Elements.dop.winBuildButton;
import com.arli.som.GameElement.Elements.dop.winBuildElement;
import com.arli.som.GameElement.InfoCountry;
import com.arli.som.GameElement.MapCon;
import com.arli.som.GameElement.MapFiles.Cell;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Map;

public class WindowBuild extends Element {
    Sprite fone = new Sprite(new Texture("HUD/WinBuild.png"));
    Array<winBuildButton> buttons = new Array<winBuildButton>(); // Массив с кнопками строений и тд
    Sprite exit = new Sprite(new Texture("HUD/ButtonE.png"));
    Sprite SB = new Sprite(new Texture("HUD/ButtonS.png"));
    Sprite AB = new Sprite(new Texture("HUD/ButtonA.png"));
    Sprite RB = new Sprite(new Texture("HUD/ButtonB.png"));
    Sprite fD1 = new Sprite(new Texture("HUD/WinBuildD1.png"));
    Sprite fD2 = new Sprite(new Texture("HUD/WinBuildD2.png"));
    Sprite fD3 = new Sprite(new Texture("HUD/WinBuildD3.png"));
    Array<winBuildElement> elementsShield = new Array<winBuildElement>(); // Массив с 1 страницей
    Array<winBuildElement> elementsAttack = new Array<winBuildElement>(); // Массив с 2 страницей
    Array<winBuildElement> elementsResourse = new Array<winBuildElement>(); // Массив с 3 страницей
    Cell cell;
    int list = 2;
    InfoCountry infoC;
    MapCon con;
    int x = (Constants.width-Constants.wSP)/2;
    int y = (Constants.heigth-Constants.hSP)/2;
    int h = Constants.hSP;
    int w = Constants.wSP;
    public WindowBuild(Cell cell, InfoCountry infoC, MapCon con) {
        this.cell = cell;
        this.infoC = infoC;
        this.con = con;
       info.put("type","window");
       info.put("level","1");
       System.out.println(cell);
       info.put("cell",cell.getCellID()+"");
        info.put("res","button");
        info.put("x",x+"");
        info.put("y",y+"");
        info.put("w",w+"");
        info.put("h",h+"");
        fD3.setBounds(x,y,w,h);
        fD2.setBounds(x,y,w,h);
        fD1.setBounds(x,y,w,h);
        AB.setBounds(Constants.xAB,y+Constants.yALL,Constants.wWB,Constants.hWB);
        SB.setBounds(Constants.xSB,y+Constants.yALL,Constants.wWB,Constants.hWB);
        RB.setBounds(Constants.xRB,y+Constants.yALL,Constants.wWB,Constants.hWB);
        exit.setBounds(Constants.xExitWB,Constants.yExitWB,Constants.wExitWB,Constants.hExitWB);
    }
    public void buildObject(int id){

    }
    @Override
    public void resourse(Map<String, String> res) { // Когда поступают ресурсы проверяем координатьы
        super.resourse(res);
        if(res.containsKey("button")){
            String[] coordinate = res.get("button").split(",");
            if(AB.getX()< Integer.parseInt(coordinate[0])&&AB.getWidth()+AB.getX()>Integer.parseInt(coordinate[0])&&AB.getY()<Integer.parseInt(coordinate[1])&&AB.getHeight()+AB.getY()> Integer.parseInt(coordinate[1])){
                list = 1;
            }else if(SB.getX()< Integer.parseInt(coordinate[0])&&SB.getWidth()+SB.getX()>Integer.parseInt(coordinate[0])&&SB.getY()<Integer.parseInt(coordinate[1])&&SB.getHeight()+SB.getY()> Integer.parseInt(coordinate[1])){
                list = 2;
            }else if(RB.getX()< Integer.parseInt(coordinate[0])&&RB.getWidth()+RB.getX()>Integer.parseInt(coordinate[0])&&RB.getY()<Integer.parseInt(coordinate[1])&&RB.getY()+RB.getHeight()> Integer.parseInt(coordinate[1])){
                list = 3;
            }else if(exit.getX()< Integer.parseInt(coordinate[0])&&exit.getWidth()+exit.getX()>Integer.parseInt(coordinate[0])&&exit.getY()<Integer.parseInt(coordinate[1])&&exit.getY()+exit.getHeight()> Integer.parseInt(coordinate[1])){
                removeThis();
            }
        }
    }
    private void removeThis(){
        con.removeElementWindow();
    }
    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        fone.setBounds(x,y,w,h);
        fone.draw(batch);

        AB.draw(batch);
        SB.draw(batch);
        RB.draw(batch);
        if(list == 1){
            fD1.draw(batch);
        }else if(list == 2){
            fD2.draw(batch);
        }else if(list == 3){
            fD3.draw(batch);
        }
    exit.draw(batch);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void dispose() {
        super.dispose();
        exit.getTexture().dispose();
        fone.getTexture().dispose();
        AB.getTexture().dispose();
        RB.getTexture().dispose();
        SB.getTexture().dispose();
    }

    @Override
    public Map<String, String> getInfo() {
        return super.getInfo();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }
}
