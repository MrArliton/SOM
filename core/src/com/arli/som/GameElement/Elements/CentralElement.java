package com.arli.som.GameElement.Elements;

import com.arli.som.Constants;
import com.arli.som.GameElement.MapFiles.Cell;
import com.arli.som.GameElement.MapFiles.objects.CentralSystem;
import com.arli.som.GameElement.MapFiles.objects.ObjectsLoader;
import com.arli.som.language;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.Map;

public class CentralElement extends Element {
    TextButton upgrade;
    Sprite fone = new Sprite(new Texture("MAP/foneWIC.png"));
    TextureRegion[] buttonT;
    ObjectsLoader loader;
    Cell cell;
    CentralSystem system;
    int x;
    int y;
    int heigh;
    int width;
    boolean upgradeOn = false; // Обязательно false
    BitmapFont texter = new BitmapFont(Gdx.files.internal("myFont.fnt"));
    // Показывает эффекты от столицы а также что требуется для улучшения
    public CentralElement(ObjectsLoader loader, TextureRegion[] buttonT, CentralSystem system, Map<String,String> information, Cell cell, int id) {
        this.buttonT =buttonT;
        this.loader = loader;
        this.cell =cell;
        this.system = system;
        this.info = information;
        if(info.get("upgrade").equalsIgnoreCase("true")){
            upgradeOn = true;
        }
        info.put("id",id+"");
        info.put("type","window");
        info.put("level","1");
        // Кнопка
        info.put("res","button"); // Запрос ресурсов для кнопки
        info.put("buttonEffect","upgrade");
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = texter;
        Drawable up = new SpriteDrawable(new Sprite(buttonT[0]));
        style.up = up;
        Drawable down = new SpriteDrawable(new Sprite(buttonT[0]));
        style.down = down;
        upgrade = new TextButton(language.getText("upgrade",1),style);
        upgrade.setWidth(Constants.cellBW-25); // Улучшение
        upgrade.setHeight(Constants.cellBH-10);
        upgrade.setPosition(cell.getCentr()[0]+(Constants.cellInfoW-Constants.cellBW)/2+50,cell.getCentr()[1]+10);
        info.put("x",cell.getCentr()[0]+"");
        x = Integer.parseInt(info.get("x"));
        info.put("y",cell.getCentr()[1]+"");
        y = Integer.parseInt(info.get("y"));
        info.put("w",cell.getCentr()[0]+Constants.cellInfoW+"");
        width = Integer.parseInt(info.get("w"));
        info.put("h",cell.getCentr()[1]+Constants.cellInfoH+"");
        heigh = Integer.parseInt(info.get("h"))-y;
    }
    @Override
    public void resourse(Map<String, String> res) {
        super.resourse(res);
        if (res.containsKey("button")) { // Нажатие на кнопку
            System.out.println(upgrade.getX()); // Проверяем было ли нажатие на кнопку
            if (upgradeOn&Integer.parseInt(res.get("button").split(",")[0]) > upgrade.getX() && Integer.parseInt(res.get("button").split(",")[0]) < upgrade.getX() + upgrade.getWidth() && Integer.parseInt(res.get("button").split(",")[1]) > upgrade.getY() && Integer.parseInt(res.get("button").split(",")[1]) < upgrade.getY() + upgrade.getHeight()) {
                info.put("press", "1");
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        fone.setBounds(cell.getCentr()[0],cell.getCentr()[1],Constants.cellInfoW,Constants.cellInfoH);
        fone.draw(batch);
        //
        texter.getData().setScale(0.4f);
        upgrade.draw(batch,1f);
        // Icons
        texter.getData().setScale(0.42f);
        loader.iconE.setBounds(x+Constants.xIE,y+heigh-Constants.yIE,Constants.widthIcon,Constants.heigthIcon);
        loader.iconE.draw(batch);
        texter.draw(batch,(int)Float.parseFloat(info.get("e"))+"=>",x+Constants.xIE+Constants.tX,y+heigh-Constants.yIE+Constants.tY);
        loader.iconM.setBounds(x+Constants.xIE,y+heigh-Constants.yIE-1*Constants.yIS,Constants.widthIcon,Constants.heigthIcon);
        loader.iconM.draw(batch);
        texter.draw(batch,(int)Float.parseFloat(info.get("ex"))+"=>",x+Constants.xIE+Constants.tX,y+heigh-Constants.yIE-1*Constants.yIS+Constants.tY);
        loader.iconP.setBounds(x+Constants.xIE,y+heigh-Constants.yIE-2*Constants.yIS,Constants.widthIcon,Constants.heigthIcon);
        loader.iconP.draw(batch);
        texter.draw(batch,(int)Float.parseFloat(info.get("t"))+"=>",x+Constants.xIE+Constants.tX,y+heigh-Constants.yIE-2*Constants.yIS+Constants.tY);
        loader.iconR.setBounds(x+Constants.xIE,y+heigh-Constants.yIE-3*Constants.yIS,Constants.widthIcon,Constants.heigthIcon);
        loader.iconR.draw(batch);
        texter.draw(batch,(int)Float.parseFloat(info.get("re"))+"=>",x+Constants.xIE+Constants.tX,y+heigh-Constants.yIE-3*Constants.yIS+Constants.tY);
        // 2 Icons
        loader.iconE.setBounds(x+Constants.xIE+Constants.xIS,y+heigh-Constants.yIE,Constants.widthIcon,Constants.heigthIcon);
        loader.iconE.draw(batch);
        texter.draw(batch,(int)(Float.parseFloat(info.get("e"))*Constants.levelUpObj)+"",x+Constants.xIE+Constants.tX+Constants.xIS,y+heigh-Constants.yIE+Constants.tY);
        loader.iconM.setBounds(x+Constants.xIE+Constants.xIS,y+heigh-Constants.yIE-1*Constants.yIS,Constants.widthIcon,Constants.heigthIcon);
        loader.iconM.draw(batch);
        texter.draw(batch,(int)(Float.parseFloat(info.get("ex"))*Constants.levelUpObj)+"",x+Constants.xIE+Constants.tX+Constants.xIS,y+heigh-Constants.yIE+Constants.tY-1*Constants.yIS);
        loader.iconP.setBounds(x+Constants.xIE+Constants.xIS,y+heigh-Constants.yIE-2*Constants.yIS,Constants.widthIcon,Constants.heigthIcon);
        loader.iconP.draw(batch);
        texter.draw(batch,(int)(Float.parseFloat(info.get("t"))*Constants.levelUpObj)+"",x+Constants.xIE+Constants.tX+Constants.xIS,y+heigh-Constants.yIE+Constants.tY-2*Constants.yIS);
        loader.iconR.setBounds(x+Constants.xIE+Constants.xIS,y+heigh-Constants.yIE-3*Constants.yIS,Constants.widthIcon,Constants.heigthIcon);
        loader.iconR.draw(batch);
        texter.draw(batch,(int)(Float.parseFloat(info.get("re"))*Constants.levelUpObj)+"",x+Constants.xIE+Constants.tX+Constants.xIS,y+heigh-Constants.yIE+Constants.tY-3*Constants.yIS);
        //
        texter.setColor(Constants.color);
        texter.getData().setScale(Constants.WHFont);
        texter.draw(batch,"",x+Constants.xR,y+Constants.hR+heigh);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void dispose() {
        super.dispose();
        fone.getTexture().dispose();
        buttonT[0].getTexture().dispose();
        texter.dispose();
    }

    @Override
    public Map<String, String> getInfo() {

      return super.getInfo();
    }
}
