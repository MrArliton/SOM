package com.arli.som.desktop;

import com.arli.som.Constants;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.arli.som.main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.width;
		config.height = Constants.heigth;
        System.setProperty("user.name","\\u0410\\u0434\\u043c\\u0438\\u043d");
        new LwjglApplication(new main(), config);
	}
}
