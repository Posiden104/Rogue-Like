package com.joel.RogueLike.states;

import com.badlogic.gdx.math.Vector2;
import com.joel.RogueLike.entity.Entity;
import com.joel.RogueLike.entity.HUD;
import com.joel.RogueLike.entity.Player;
import com.joel.RogueLike.handlers.BoundedCamera;
import com.joel.RogueLike.handlers.GameStateManager;
import com.joel.RogueLike.handlers.MyInput;
import com.joel.RogueLike.main.Game;
import com.joel.RogueLike.map.Map;
import com.joel.RogueLike.map.TileSet;

public class Play extends GameState {

	private BoundedCamera boundedCam;

	public static Player player;

	// private Array<Entity> entities;

	private HUD hud;

	public static int level;
	public static boolean debug = false;
	
	public static Map map;

	public Play(GameStateManager gsm) {
		super(gsm);

		// set up bounded camera
		boundedCam = new BoundedCamera();
		boundedCam.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);
		boundedCam.setBounds(0, Game.V_WIDTH, 0, Game.V_HEIGHT);

		// create player
		Entity e = new Entity(1, -1, 200); 
		player = new Player(e);

		// create hud
		hud = new HUD(player);

		// create map
		map = new Map(50, 50, 0L);
	}

	public void handleInput() {
		if (MyInput.isDown(MyInput.KEY_UP) && (MyInput.isPressed(MyInput.KEY_UP))) { // north
			player.move(0);
			map.up();
		}
		if (MyInput.isDown(MyInput.KEY_LEFT) && (MyInput.isPressed(MyInput.KEY_LEFT))) { // west
			player.move(1);
			map.left();
		}
		if (MyInput.isDown(MyInput.KEY_DOWN) && (MyInput.isPressed(MyInput.KEY_DOWN))) { // south
			player.move(2);
			map.down();
		}
		if (MyInput.isDown(MyInput.KEY_RIGHT) && (MyInput.isPressed(MyInput.KEY_RIGHT))) { // east
			player.move(3);
			map.right();
		}
	}

	public void update(float dt) {
		// update input
		handleInput();

		// update player
		player.update(dt);

		// update the map
		map.update(dt);
	}

	public void render() {

		// camera follow player
		boundedCam.setPosition(player.getX() + Game.V_WIDTH / 2, Game.V_HEIGHT / 2);
		boundedCam.update();

		// render map
		map.render(sb);

		// render tilesets
		if (debug) {
			TileSet.stoneFloor.render(sb, 100);
			TileSet.stoneWall.render(sb, 350);
		}
		
		// render player
		sb.setProjectionMatrix(boundedCam.combined);
		player.render(sb);

		// render HUD
		sb.setProjectionMatrix(hudCam.combined);
		hud.render(sb);

	}

	public static Vector2 getPlayerOffset() {
		return player.getRenderOffset();
	}

	public void dispose() {
		// everything is in the resource manager handlers.Content
	}

}