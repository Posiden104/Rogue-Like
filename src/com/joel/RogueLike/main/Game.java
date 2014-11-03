package com.joel.RogueLike.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.joel.RogueLike.handlers.BoundedCamera;
import com.joel.RogueLike.handlers.Content;
import com.joel.RogueLike.handlers.GameStateManager;
import com.joel.RogueLike.handlers.MyInput;
import com.joel.RogueLike.handlers.MyInputProcessor;

public class Game implements ApplicationListener {
	
	public static final String TITLE = "Rogue-Like";
	public static final int V_WIDTH = 320;
	public static final int V_HEIGHT = 240;
	public static final int SCALE = 2;
	public static final float STEP = 1 / 60f;
	
	private SpriteBatch sb;
	private BoundedCamera cam;
	private OrthographicCamera hudCam;
	
	private GameStateManager gsm;
	
	public static Content res;
	
	public void create() {
		
		Texture.setEnforcePotImages(false);
		Gdx.input.setInputProcessor(new MyInputProcessor());
		
		res = new Content(); // load all sprites and music
		
//		res.loadTexture("res/images/menu.png");
//		res.loadSound("res/sfx/jump.wav");
//		
//		res.loadMusic("res/music/bbsong.ogg");
//		res.getMusic("bbsong").setLooping(true);
//		res.getMusic("bbsong").setVolume(0.5f);
//		res.getMusic("bbsong").play();
		
		cam = new BoundedCamera(); // used to limit camera 
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		
		sb = new SpriteBatch();
		
		gsm = new GameStateManager(this);
		
	}
	
	public void render() {
		
		Gdx.graphics.setTitle(TITLE + " -- FPS: " + Gdx.graphics.getFramesPerSecond());
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
		MyInput.update();
		
	}
	
	public void dispose() {
		res.removeAll();
	}
	
	public void resize(int w, int h) {}
	
	public void pause() {}
	
	public void resume() {}
	
	public SpriteBatch getSpriteBatch() { return sb; }
	public BoundedCamera getCamera() { return cam; }
	public OrthographicCamera getHUDCamera() { return hudCam; }
	
}
