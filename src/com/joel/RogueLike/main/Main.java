package com.joel.RogueLike.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/*
 *
 * @author Posiden104
 * 
 */

public class Main {

	public static final String TITLE = "Rogue Like";
	public static final String VERSION = "v0.1a";
	
	public static final int V_WIDTH = 480;
	public static final int V_HEIGHT = 320;
	public static final int SCALE = 2;
	
	public static void main(String[] args) {
		System.out.println("The first line of the Rogue-Like by Posiden104!");
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = TITLE + " " + VERSION;
		cfg.width = V_WIDTH * SCALE;
		cfg.height = V_HEIGHT * SCALE;
		
		new LwjglApplication(new Game(), cfg);
	}

}
