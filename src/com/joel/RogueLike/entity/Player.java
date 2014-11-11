package com.joel.RogueLike.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.joel.RogueLike.main.Game;

public class Player extends Sprite {
	
	public Entity player;
	
	
	public Player(Entity e) { 
		super(e);

		player = e;
		
		Texture tex = Game.res.getTexture("dragon");
		TextureRegion[] sprites = new TextureRegion[2];
		for(int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 16, 0, 16, 16);
		}
		
		animation.setFrames(sprites, 1 / 3f);
		
		width = sprites[0].getRegionWidth();
		height = sprites[0].getRegionHeight();
		
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(animation.getFrame(), Game.V_WIDTH / 2 - 8, Game.V_HEIGHT / 2 - 8);
		sb.end();
	}

	
	/*
	 * Offset for the rest of the game's rendering
	 */
	public Vector2 getRenderOffset() { 
		Vector2 ret = new Vector2(player.getX() - Game.V_WIDTH / 2 + 8, player.getY() - Game.V_HEIGHT / 2 + 8); 
		return ret;
	}
	
	public Vector2 getActualOffset() {
		Vector2 ret = new Vector2(player.getX(), player.getY()); // not sure if this method works correctly
		return ret;
	}
	
	public void move(int dir) {
		switch(dir) {
		case 0: // north
			player.y += 16;
//			printCords();
			break;
		case 1: // east
			player.x -= 16;
//			printCords();
			break;
		case 2: // south
			player.y -= 16;
//			printCords();
			break;
		case 3: // west
			player.x += 16;
//			printCords();
			break;
		}
		
	}

	public void printCords() {
//		System.out.printf("x: %f, y: %f\n", getRenderOffset().x, getRenderOffset().y );
		System.out.printf("x %f, y %f\n", (float) player.x, (float) player.y);
		}
}
