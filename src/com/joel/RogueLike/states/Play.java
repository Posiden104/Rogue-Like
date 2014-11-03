package com.joel.RogueLike.states;

import static com.joel.RogueLike.handlers.B2DVars.PPM;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.joel.RogueLike.entity.Entity;
import com.joel.RogueLike.entity.HUD;
import com.joel.RogueLike.entity.Player;
import com.joel.RogueLike.handlers.BoundedCamera;
import com.joel.RogueLike.handlers.GameStateManager;
import com.joel.RogueLike.main.Game;

public class Play extends GameState {
	
//	private boolean debug = false;
	
	private BoundedCamera b2dCam;
	
	private Player player;
	
	private TiledMap tileMap;
	private int tileMapWidth;
	private int tileMapHeight;
	private int tileSize;
	private OrthogonalTiledMapRenderer tmRenderer;
	
	private Array<Entity> entities;
	
	private HUD hud;
	
	public static int level;
	
	public Play(GameStateManager gsm) {
		
		super(gsm);
		
		cam.setBounds(0, tileMapWidth * tileSize, 0, tileMapHeight * tileSize);

		// create hud
		hud = new HUD(player);
		
		// set up box2d cam
		b2dCam = new BoundedCamera();
		b2dCam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);
		b2dCam.setBounds(0, (tileMapWidth * tileSize) / PPM, 0, (tileMapHeight * tileSize) / PPM);
		
	}
	
	public void handleInput() {

	}
	
	public void update(float dt) {
	
	}
	
	public void render() {
		
		// camera follow player
		cam.setPosition(player.getPosition().x * PPM + Game.V_WIDTH / 4, Game.V_HEIGHT / 2);
		cam.update();
		
		// draw tilemap
		tmRenderer.setView(cam);
		tmRenderer.render();
		
	}
	
	public void dispose() {
		// everything is in the resource manager handlers.Content
	}
	
}