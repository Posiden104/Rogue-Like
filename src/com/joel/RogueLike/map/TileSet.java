package com.joel.RogueLike.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileSet extends Sprite{


//	public static TileSet stoneFloor = new TileSet(Game.res.getTexture("stone_floor"), 16, 16);
//	public static TileSet stoneWall = new TileSet(Game.res.getTexture("stone_wall"), 16, 16);
	
	private TextureRegion[][] tiles;
	
	public TileSet() {
		
	}
	
	public TileSet(Texture tex, int w, int h) {
		tiles = TextureRegion.split(tex, w, h);
	}
	
	public TextureRegion getTile(int x, int y) {
		return tiles[x][y];
	}
	
}
