package com.arli.som;

import com.arli.som.GameElement.GameClass;
import com.badlogic.gdx.Game;




public class main extends Game { // Управляющий класс
	
	@Override
	public void create () { // Сразу переходим на Screen с игрой
		setScreen(new GameClass());
	}

	@Override
	public void render () {
            super.render();
	}
	
	@Override
	public void dispose () {
            super.dispose();
	}
}
