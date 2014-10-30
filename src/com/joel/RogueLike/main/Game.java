package com.joel.RogueLike.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.joel.RogueLike.handlers.GameStateManager;
import com.joel.RogueLike.handlers.MyInputProcessor;

public class Game implements ApplicationListener {

	public static final float STEP = 1 / 60f;
	private float accum;
	
	private GameStateManager gsm;
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	
	public void create() {
		Texture.setEnforcePotImages(false);
		
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		hudCam = new OrthographicCamera();

		Gdx.input.setInputProcessor(new MyInputProcessor());
		gsm = new GameStateManager(this);

	}
	
	public void render() {
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
			// update
			// render
			// UPDATE INPUT
		}
	}
	
	public void dispose() {}
	
	public SpriteBatch getSpriteBatch() { return sb; }
	public OrthographicCamera getCamera() { return cam; }
	public OrthographicCamera getHUDCamera() { return hudCam; }
	
	public void resize(int w, int h) {}
	public void pause() {}
	public void resume() {}

	
}
