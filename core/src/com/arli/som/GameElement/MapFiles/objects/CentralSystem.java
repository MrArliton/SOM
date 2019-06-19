package com.arli.som.GameElement.MapFiles.objects;

import com.arli.som.GameElement.MapFiles.Object;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CentralSystem extends Object {
    Sprite central;
    int idControll;
    // Параметры объекта
    int health;
    int shield;
    @Override
    public void render() {
        super.render();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void dispose() { // Уничтожает особые ресырсы именно данного объекта

        super.dispose();
    } // Главная клетка каждой страны
}
