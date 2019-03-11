package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyHeliGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		new LwjglApplication(new MyHeliGame(), config);
		config.title = MyHeliGame.TITLE;
		config.height = MyHeliGame.HEIGHT;
		config.width = MyHeliGame.WIDTH;

	}
}
