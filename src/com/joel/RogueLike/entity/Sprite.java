package com.joel.RogueLike.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.joel.RogueLike.handlers.Animation;

/**
 * Attaches animated sprites to box2d bodies
 */
public class Sprite{
	
	protected Entity entity;
	protected Animation animation;
	protected float width;
	protected float height;
	
	public Sprite(Entity entity) {
		this.entity = entity;
		animation = new Animation();
	}
	
	public void setAnimation(TextureRegion reg, float delay) {
		setAnimation(new TextureRegion[] { reg }, delay);
	}
	
	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}
	
	public void update(float dt) {
		animation.update(dt);
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(animation.getFrame(), entity.getX(), entity.getY());
		sb.end();
	}
	
	public Entity getEntity() { return entity; }
	public int getX() { return entity.getX(); }
	public int getY() { return entity.getY(); }
	public float getWidth() { return width; }
	public float getHeight() { return height; }
	
}
