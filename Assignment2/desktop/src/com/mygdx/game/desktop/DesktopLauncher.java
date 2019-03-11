package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyHeliGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyHeliGame(), config);
		config.width = MyHeliGame.WIDTH;
		config.height = MyHeliGame.HEIGHT;
		config.title = MyHeliGame.TITLE;
	}
}
