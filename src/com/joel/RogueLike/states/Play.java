package com.joel.RogueLike.states;

import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.joel.RogueLike.entity.Entity;
import com.joel.RogueLike.entity.HUD;
import com.joel.RogueLike.entity.Player;
import com.joel.RogueLike.handlers.BoundedCamera;
import com.joel.RogueLike.handlers.GameStateManager;
import com.joel.RogueLike.handlers.MyInput;
import com.joel.RogueLike.main.Game;

public class Play extends GameState {

	private BoundedCamera boundedCam;
	private Player player;
	// private Array<Entity> entities;
	private HUD hud;
	public static int level;

	public Play(GameStateManager gsm) {
		super(gsm);

		// set up bounded camera
		boundedCam = new BoundedCamera();
		boundedCam.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);
		boundedCam.setBounds(0, Game.V_WIDTH, 0, Game.V_HEIGHT);

		Entity e = new Entity(100, 200, 200);

		player = new Player(e);

		// create hud
		hud = new HUD(player);
	}

	public void handleInput() {
		if (MyInput.isDown(MyInput.KEY_UP)) {
			player.move(0);
		}
		if (MyInput.isDown(MyInput.KEY_LEFT)) {
			player.move(1);
		}
		if (MyInput.isDown(MyInput.KEY_DOWN)) {
			player.move(2);
		}
		if (MyInput.isDown(MyInput.KEY_RIGHT)) {
			player.move(3);
		}
	}

	public void update(float dt) {
		handleInput();
		player.update(dt);
	}

	public void render() {

		// camera follow player
		boundedCam.setPosition(player.getX() + Game.V_WIDTH / 4, Game.V_HEIGHT / 2);
		boundedCam.update();

		sb.setProjectionMatrix(boundedCam.combined);
		player.render(sb);

		sb.setProjectionMatrix(hudCam.combined);
		hud.render(sb, this);

		// draw tilemap
		// tmRenderer.setView(cam);
		// tmRenderer.render();

	}

	public Vector2 getPlayerOffset() {
		return player.getOffset();
	}

	public void dispose() {
		// everything is in the resource manager handlers.Content
	}

}