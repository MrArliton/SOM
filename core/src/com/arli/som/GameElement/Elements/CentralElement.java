package com.arli.som.GameElement.Elements;

import com.arli.som.Constants;
import com.arli.som.GameElement.MapFiles.Cell;
import com.arli.som.GameElement.MapFiles.objects.CentralSystem;
import com.arli.som.GameElement.MapFiles.objects.ObjectsLoader;
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
    Button upgrade;
    Sprite fone = new Sprite(new Texture("MAP/foneWIC.png"));
    TextureRegion[] buttonT;
    ObjectsLoader loader;
    Cell cell;
    CentralSystem system;
    boolean upgradeOn = false;
    BitmapFont texter = new BitmapFont(Gdx.files.internal("myFont.fnt"));
    // Показывает эффекты от столицы а также что требуется для улучшения
    public CentralElement(ObjectsLoader loader, TextureRegion[] buttonT, CentralSystem system, Map<String,String> info, Cell cell, int id) {
        this.buttonT =buttonT;
        this.loader = loader;
        this.info = info;
        this.cell =cell;
        this.system = system;
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
        upgrade = new Button(style);
        upgrade.setWidth(Constants.cellBW+50); // Улучшение
        upgrade.setHeight(Constants.cellBH);
        upgrade.setPosition(cell.getCentr()[0]+(Constants.cellInfoW-Constants.cellBW)/2-25,cell.getCentr()[1]+10);
        info.put("x",cell.getCentr()[0]+"");
        info.put("y",cell.getCentr()[1]+"");
        info.put("w",cell.getCentr()[0]+Constants.cellInfoW+"");
        info.put("h",cell.getCentr()[1]+Constants.cellInfoH+"");
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
        upgrade.draw(batch,1f);
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
