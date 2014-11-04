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
		
		animation.setFrames(sprites, 1 / 2f);
		
		width = sprites[0].getRegionWidth();
		height = sprites[0].getRegionHeight();
		
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(animation.getFrame(), Game.V_WIDTH / 2, Game.V_HEIGHT / 2);
		sb.end();
	}

	public Vector2 getOffset() {

		Vector2 ret = new Vector2(entity.getX(), entity.getY());
	
		return ret;
	}
	
}
