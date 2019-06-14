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
		new LwjglApplication(new main(), config);
	}
}
