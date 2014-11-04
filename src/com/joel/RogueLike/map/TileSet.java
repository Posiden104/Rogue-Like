package com.joel.RogueLike.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.joel.RogueLike.main.Game;
import com.joel.RogueLike.states.Play;

public class TileSet extends Sprite{


	public static TileSet stoneFloor = new TileSet(Game.res.getTexture("grey_floor"), 16, 16);
	public static TileSet stoneWall = new TileSet(Game.res.getTexture("grey_wall"), 16, 16);
	
	private TextureRegion[][] tiles;
	
	public TileSet() {
		
	}
	
	public TileSet(Texture tex, int w, int h) {
		tiles = TextureRegion.split(tex, w, h);
	}
	
	public TextureRegion getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public void render(SpriteBatch sb) {
		
		sb.begin();
		for (int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				sb.draw(tiles[i][j], j * 17 - Play.getPlayerOffset().x, i * -17 - Play.getPlayerOffset().y);
//				HUD.drawString(sb, i + " " + j, j * 17 - Play.getPlayerOffset().x, i * -17 - Play.getPlayerOffset().y);
			}
		}
		sb.end();
	}
	
}
