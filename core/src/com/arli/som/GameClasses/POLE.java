package com.arli.som.GameClasses;

import com.arli.som.GameClasses.Pole.Object;
import com.arli.som.GameClasses.Pole.cell;
import com.badlogic.gdx.utils.Array;

// Отвечает за прорисовку поля
// Взаимодействие стран и объектов
// Также регулирует ресурсы всех стран
public class POLE  {
Array<Object> objects = new Array<Object>(); // Объекты
Array<cell> cells = new Array<cell>();

     public POLE(int map){ // Тут указывваем номер карты и запускаем её

     }
     public void createObject(int idObj,int cellID){ // Создаёт объект на ячейке

     }
public void updateDays(){ // Обновляет все объекты за день

}
public void updateSeason(){ // Обновляет сезон даёт плюси и минусы к производствам

}
public void update(){

}
public void render(){

}
public void dispose(){

}

}
