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

	private boolean solid;
	private boolean visible;

	public enum Position {
		NORTH, SOUTH, EAST, WEST, CORNER, MIDDLE;
	}

	private Position pos;

	public Tile(TextureRegion tex) {
		if (Play.debug) {
			this.x = 32 * y; // adds spacing in the tiles
			this.y = 17 * x;
		} else {
			this.x = tex.getRegionWidth() * y; // swap x and y for
			this.y = tex.getRegionHeight() * x; // ease of rendering in loops
		}

		animation = new Animation();
		setAnimation(tex, 1 / 2f);
		pos = Position.MIDDLE;
	}

	public Tile(TextureRegion[] tex) {
		if (Play.debug) {
			this.x = 32 * y; // adds spacing in tiles
			this.y = 17 * x;
		} else {
			this.x = tex[0].getRegionWidth() * y; // swap x and y for
			this.y = -tex[0].getRegionHeight() * x; // ease of rendering
		}

		animation = new Animation();
		setAnimation(tex, 1 / 3f); // switch frames three times a second
		pos = Position.MIDDLE;
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
			// draw the x and y cords
			HUD.drawString(sb, y / 17 + " " + x / 32, x - Play.getPlayerOffset().x, -y - Play.getPlayerOffset().y);
		}
		sb.end();
	}

	public void render(SpriteBatch sb, int i, int j) {
		sb.begin();
		float xp = -i * animation.getFrame().getRegionWidth() - Play.getPlayerOffset().y;
		float yp = j * animation.getFrame().getRegionHeight() - Play.getPlayerOffset().x;
		// draw tile
		sb.draw(animation.getFrame(), yp, xp);
		sb.end();
	}

	public void setPosition(Position p) {
		pos = p;
	}

	public Position getPos() {
		return pos;
	}

	// TODO: implement this
	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
