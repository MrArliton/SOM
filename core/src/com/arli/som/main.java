package com.arli.som;

import com.arli.som.GameElement.GameClass;
import com.badlogic.gdx.Game;

import java.util.HashMap;
import java.util.Map;


public class main extends Game { // Управляющий класс
	
	@Override
	public void create () { // Сразу переходим на Screen с игрой
		// map - pathMap
		// players - Количество игроков/ботов
		// multiplayer - Включён ли мультиплеер
		// multiplayer. ... - Настройки мультиплеер надо доделать
		// res. ... - начальные ресуры игрока/бота
		Map<String,String> configGame = new HashMap<String, String>();
		configGame.put("map","map1.txt");
		setScreen(new GameClass(configGame));
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
