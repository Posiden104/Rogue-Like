package com.joel.RogueLike.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.joel.RogueLike.handlers.Animation;
import com.joel.RogueLike.states.Play;

public class Tile extends Sprite{

//	private TextureRegion[] tex;
	
	private Animation animation;
	
	private int x;
	private int y; 
	
	public Tile(TextureRegion tex, int x, int y) {
//		this.tex = new TextureRegion[] { tex };
		animation = new Animation();
		setAnimation(tex, 1 / 2f);
	}
	
	public Tile(TextureRegion[] tex, int x, int y) {
//		this.tex = tex;
		animation = new Animation();
		setAnimation(tex, 1 / 2f);
	}
	
	public void setAnimation(TextureRegion reg, float delay) {
		setAnimation(new TextureRegion[] { reg }, delay);
	}
	
	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrames(reg, delay);
	}
	
	public void update(float dt) {
		animation.update(dt);
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(animation.getFrame(), x - Play.getPlayerOffset().x, y - Play.getPlayerOffset().y);
		sb.end();
	}
	
}
