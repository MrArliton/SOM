package com.arli.som.GameElement.Elements;

import com.arli.som.Constants;
import com.arli.som.GameElement.MapCon;
import com.arli.som.GameElement.MapFiles.Cell;
import com.arli.som.language;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.Map;

public class CellInformation extends Element {// Класс вызывается при нажатие на пустую клетку для вывода информации о ней
    // Если клетка ничья или другой страны то идёт информация о ней или её объекте
    // Если собственная то информация + предложение о строительстве
    int x;
    int y;
    int width;
    int heigh;
    TextButton button;
    boolean buttonA;
    Cell cell;
    BitmapFont texter = new BitmapFont(Gdx.files.internal("myFont.fnt"));
    TextureRegion[] texture;
    Sprite fone;
    float whT = 2f;
    @Override
    public void resourse(Map<String, String> res) {
        super.resourse(res);
        if(res.containsKey("button")){ // Нажатие на кнопку
            System.out.println(button.getX()); // Проверяем было ли нажатие на кнопку
            if(Integer.parseInt(res.get("button").split(",")[0])>button.getX()&&Integer.parseInt(res.get("button").split(",")[0])<button.getX()+button.getWidth()&&Integer.parseInt(res.get("button").split(",")[1])>button.getY()&&Integer.parseInt(res.get("button").split(",")[1])<button.getY()+button.getHeight()){
               System.out.println("Pressed");
                info.put("press","1");
            }
        }
    }

    public CellInformation(Sprite fone, String textButton, TextureRegion[] buttonD, Cell cell, int id){
        info.put("id",id+"");
        info.put("type","window");
        info.put("level","1");
        this.cell = cell;
        texture = buttonD;
        this.fone = fone;
        if(cell.getIdControll()==1){ // Является ячейкой нашей територии значит создаём кнопку// стройки
            info.put("res","button");
            buttonA = true;
            TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
            style.font =texter;
            SpriteDrawable a = new SpriteDrawable();
            a.setSprite(new Sprite(buttonD[0]));
            style.up = a;
            SpriteDrawable b = new SpriteDrawable();
            b.setSprite(new Sprite(buttonD[1]));
            style.down = b;
            this.button = new TextButton(language.getText(textButton,1),style);
            info.put("press","0");
            button.setWidth(Constants.cellBW);
            button.setHeight(Constants.cellBH);
            button.setPosition(cell.getCentr()[0]+(Constants.cellInfoW-Constants.cellBW)/2,cell.getCentr()[1]+10);
        }
        x = cell.getCentr()[0];
        y = cell.getCentr()[1];
        width = Constants.cellInfoW;
        heigh = Constants.cellInfoH;
        // Создаём координаты для проверки на нажатие по окну
        info.put("x",""+x);
        info.put("y",""+y);
        info.put("w",""+width);
        info.put("h",""+heigh);
    }
    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        fone.setBounds(cell.getCentr()[0],cell.getCentr()[1],Constants.cellInfoW,Constants.cellInfoH);
        fone.draw(batch);
        if(buttonA){
            button.draw(batch,1f);
        }
        texter.setColor(Constants.color);
        texter.getData().setScale(Constants.WHFont);
        texter.draw(batch,cell.getCellInfo()+language.getText(cell.getIdControll()+"",2),x+Constants.xR,y+Constants.hR+heigh);
    }

    @Override
    public void update() {
        super.update();
        }
    @Override
    public void dispose(){
            texture[0].getTexture().dispose();
            texter.dispose();
    }
    @Override
    public Map<String, String> getInfo() {
        return super.getInfo();
    }

}
