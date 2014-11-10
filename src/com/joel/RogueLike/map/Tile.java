package com.joel.RogueLike.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.joel.RogueLike.entity.HUD;
import com.joel.RogueLike.handlers.Animation;
import com.joel.RogueLike.states.Play;

public class Tile extends Sprite {

	private Animation animation;

	private int x;
	private int y;

	public Tile(TextureRegion tex, int x, int y) {
		if (Play.debug) {
			this.x = 32 * y; // adds spacing in the tiles
			this.y = 17 * x;
		} else {
			this.x = tex.getRegionWidth() * y; // swap x and y for 
			this.y = tex.getRegionHeight() * x; // ease of rendering in loops
		}

		animation = new Animation();
		setAnimation(tex, 1 / 2f);
	}

	public Tile(TextureRegion[] tex, int x, int y) {
		if (Play.debug) {
			this.x = 32 * y; // adds spacing in tiles
			this.y = 17 * x;
		} else {
			this.x = tex[0].getRegionWidth() * y; // swap x and y for 
			this.y = -tex[0].getRegionHeight() * x; // ease of rendering
		}
		
		animation = new Animation();
		setAnimation(tex, 1 / 3f); // switch frames three times a second
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
		
		// draw tile
		sb.draw(animation.getFrame(), x - Play.getPlayerOffset().x, -y - Play.getPlayerOffset().y);
		
		if (Play.debug) {
			HUD.drawString(sb, y / 17 + " " + x / 32, x - Play.getPlayerOffset().x, -y - Play.getPlayerOffset().y); // draw the x and y cords 
		}
		sb.end();
	}

}
