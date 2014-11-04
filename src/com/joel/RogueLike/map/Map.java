package com.joel.RogueLike.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	
	public Tile[][] map;

	public Map() {
		
		@SuppressWarnings("unused")
		TileSet t = new TileSet();
		
		map = new Tile[1][5];
		
//		System.out.println(stoneFloor.getTile(0, 0));
//		
//		map[0][0] = new Tile(stoneFloor.getTile(0, 0), 0, 0);
//		map[0][1] = new Tile(stoneFloor.getTile(0, 1), 0, 1);
//		map[0][2] = new Tile(stoneFloor.getTile(0, 1), 0, 2);
//		map[0][3] = new Tile(stoneFloor.getTile(0, 1), 0, 3);
//		map[0][4] = new Tile(stoneFloor.getTile(0, 2), 0, 4);
//
//		map[1][0] = new Tile(TileSet.stoneFloor.getTile(1, 0), 1, 0);
//		map[1][1] = new Tile(TileSet.stoneFloor.getTile(1, 1), 1, 1);
//		map[1][2] = new Tile(TileSet.stoneFloor.getTile(1, 1), 1, 2);
//		map[1][3] = new Tile(TileSet.stoneFloor.getTile(1, 1), 1, 3);
//		map[1][4] = new Tile(TileSet.stoneFloor.getTile(1, 2), 1, 4);
//
//		map[2][0] = new Tile(TileSet.stoneFloor.getTile(1, 0), 2, 0);
//		map[2][1] = new Tile(TileSet.stoneFloor.getTile(1, 1), 2, 1);
//		map[2][2] = new Tile(TileSet.stoneFloor.getTile(1, 1), 2, 2);
//		map[2][3] = new Tile(TileSet.stoneFloor.getTile(1, 1), 2, 3);
//		map[2][4] = new Tile(TileSet.stoneFloor.getTile(1, 2), 2, 4);
//
//		map[3][0] = new Tile(TileSet.stoneFloor.getTile(1, 0), 3, 0);
//		map[3][1] = new Tile(TileSet.stoneFloor.getTile(1, 1), 3, 1);
//		map[3][2] = new Tile(TileSet.stoneFloor.getTile(1, 1), 3, 2);
//		map[3][3] = new Tile(TileSet.stoneFloor.getTile(1, 1), 3, 3);
//		map[3][4] = new Tile(TileSet.stoneFloor.getTile(1, 2), 3, 4);
//
//		map[4][0] = new Tile(TileSet.stoneFloor.getTile(2, 0), 4, 0);
//		map[4][1] = new Tile(TileSet.stoneFloor.getTile(2, 1), 4, 1);
//		map[4][2] = new Tile(TileSet.stoneFloor.getTile(2, 1), 4, 2);
//		map[4][3] = new Tile(TileSet.stoneFloor.getTile(2, 1), 4, 3);
//		map[4][4] = new Tile(TileSet.stoneFloor.getTile(2, 2), 4, 4);
	}
	
	public void update(float dt) {
		for(Tile[] t : map) {
			for(Tile t2 : t) {
//				t2.update(dt);
			}
		}
	}
	
	public void render(SpriteBatch sb) {
		for(Tile[] t : map) {
			for(Tile t2 : t) {
//				t2.render(sb);
			}
		}
	}
	
}
