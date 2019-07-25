package com.arli.som;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Constants {
   public static final int width = 720;
   public  static final int heigth = 1280;
   public static final boolean debug = true;
   // Edit game
   public static String pathObjList = "";

   // Not edit
    // Для объектов с улучшениями
 public static final float levelUpObj = 1.5f;
 public static final float levelUpObjRes = 2f;
 //
    // Для central object
    public static final float UpgEnergyZ = 5;
    public static final float UpgMaterZ = 3;
    public static final float UpProZ = 1;
    //
    // Время
    public static final float timeDayCycle = 0.5f;
    // HUD
    // icons
    public static final int xIconZdvig = 140;
    public static final int yIconZdvig = 55;
    public static final int IconW = 50;
    public static final int IconH = 50;
    //

    //
   public static final int cellDvigX = (int)(58/1.5); // Плюсовка
   public static final int cellDvigY = 2; // Деление
    public static final int cellW = (int)(75/1.5); // Плюсовка
    public static final int cellH = (int)(50/1.5); // Деление
    public static final int cellTW = 36;
    public static final int cellTH = 21;
    public static final int cellInfoW = 200; // Размер окна информации о клетке
    public static final int cellInfoH = 100;
    public static final int cellBW = 100;
    public static final int cellBH = 30;
    public static final int resolutionWindowW = Gdx.app.getGraphics().getWidth();
    public static final int resolutionWindowH = Gdx.app.getGraphics().getHeight();
    // WinBuld
    public static final int yALL = Constants.hSP-40-Constants.hWB;
    public static final int xAB = 112;
    public static final int xSB = 184+117;
    public static final int xRB = 184+117+190;
    public static final int xExitWB = 660;
    public static final int yExitWB = 1135;
    public static final int wExitWB = 50;
    public static final int hExitWB = 50;
    public static final int hWB = 117;
    public static final int wWB = 117;
    public static final int wSP = 600;
    public static final int hSP = 1000;

    //
    // CellWindow
    public static final Color  color = Color.BLACK;
    public static final int xR = 10; // Сдвиги текста
    public static final int hR = -10;
    public static final float WHFont = 0.4f;
    public static final float BST = 0.8f;
    // Central Coordinates
    // Координаты иконок
    public static final int xIS = 50;
    public static final int yIS = 20;
    public static final int yIE = 25;
    public static final int xIE = 10;
    public static final int widthIcon = 15;
    public static final int heigthIcon = 15;
    // Текства
    public static final int tX = 18;
    public static final int tY = 12;
}
