package com.arli.som;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class main extends Game { // Управляющий класс
	SpriteBatch batch;
	
	@Override
	public void create () { // Сразу переходим на Screen с игрой
		com.arli.som.Game game = new com.arli.som.Game();
		setScreen(game);
	}

	@Override
	public void render () {

	}
	
	@Override
	public void dispose () {

	}
}
