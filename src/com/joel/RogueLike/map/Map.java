package com.joel.RogueLike.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {

	public Tile[][] map; // array of tiles for map
	public Tile[][] walls;

	public Map() {

		Room room1 = new Room(8, 6, TileSet.stoneFloor, TileSet.stoneWall);
		map = room1.getRoom();
		walls = room1.getWalls();

//		 testMap();
	}

	public void update(float dt) {
		if (map != null) {
			for (Tile[] t : map) {
				for (Tile t2 : t) {
					if (t2 != null)
						t2.update(dt);
				}
			}
		}

		if (walls != null) {
			for (Tile[] t : walls) {
				for (Tile t2 : t) {
					if (t2 != null)
						t2.update(dt);
				}
			}
		}
	}

	public void render(SpriteBatch sb) {
		if (map != null) {
			for (Tile[] t : map) {
				for (Tile t2 : t) {
					if (t2 != null)
						t2.render(sb);
				}
			}
		}
		if (walls != null) {
			for (Tile[] t : walls) {
				for (Tile t2 : t) {
					if (t2 != null)
						t2.render(sb);
				}
			}
		}
	}

	public void testMap2() {
		map = new Tile[2][2]; // Corners only
		map[0][0] = new Tile(TileSet.stoneFloor.getTile(0, 0), 0, 0); // TL
		map[0][1] = new Tile(TileSet.stoneFloor.getTile(0, 1), 0, 1); // TR
		
		map[1][0] = new Tile(TileSet.stoneFloor.getTile(1, 0), 1, 0); // BL
		map[1][1] = new Tile(TileSet.stoneFloor.getTile(1, 1), 1, 1); // BR
	}
	
	public void testMap() {
		
		// Map
		map = new Tile[5][5];

		// Corners
		map[0][0] = new Tile(TileSet.stoneFloor.getTile(0, 0), 0, 0); // TL corner
		map[0][4] = new Tile(TileSet.stoneFloor.getTile(0, 2), 0, 4); // TR corner
		map[4][0] = new Tile(TileSet.stoneFloor.getTile(2, 0), 4, 0); // BL corner
		map[4][4] = new Tile(TileSet.stoneFloor.getTile(2, 2), 4, 4); // BR corner
		
		// Sides
		map[0][1] = new Tile(TileSet.stoneFloor.getTile(0, 1), 0, 1); //
		map[0][2] = new Tile(TileSet.stoneFloor.getTile(0, 1), 0, 2); // Top wall
		map[0][3] = new Tile(TileSet.stoneFloor.getTile(0, 1), 0, 3); //
		
		map[4][1] = new Tile(TileSet.stoneFloor.getTile(2, 1), 4, 1); //
		map[4][2] = new Tile(TileSet.stoneFloor.getTile(2, 1), 4, 2); // Bottom wall
		map[4][3] = new Tile(TileSet.stoneFloor.getTile(2, 1), 4, 3); //
		
		map[1][0] = new Tile(TileSet.stoneFloor.getTile(1, 0), 1, 0); //
		map[2][0] = new Tile(TileSet.stoneFloor.getTile(1, 0), 2, 0); // Left wall
		map[3][0] = new Tile(TileSet.stoneFloor.getTile(1, 0), 3, 0); //
		
		map[1][4] = new Tile(TileSet.stoneFloor.getTile(1, 2), 1, 4); //
		map[2][4] = new Tile(TileSet.stoneFloor.getTile(1, 2), 2, 4); // Right wall
		map[3][4] = new Tile(TileSet.stoneFloor.getTile(1, 2), 3, 4); //
		
		// Middle
		map[1][1] = new Tile(TileSet.stoneFloor.getTile(1, 1), 1, 1); //
		map[1][2] = new Tile(TileSet.stoneFloor.getTile(1, 1), 1, 2); // Middle
		map[1][3] = new Tile(TileSet.stoneFloor.getTile(1, 1), 1, 3); //

		map[2][1] = new Tile(TileSet.stoneFloor.getTile(1, 1), 2, 1); //
		map[2][2] = new Tile(TileSet.stoneFloor.getTile(1, 1), 2, 2); // Middle
		map[2][3] = new Tile(TileSet.stoneFloor.getTile(1, 1), 2, 3); //

		map[3][1] = new Tile(TileSet.stoneFloor.getTile(1, 1), 3, 1); //
		map[3][2] = new Tile(TileSet.stoneFloor.getTile(1, 1), 3, 2); // Middle
		map[3][3] = new Tile(TileSet.stoneFloor.getTile(1, 1), 3, 3); //

		// Walls
		walls = new Tile[7][7];

		// corners
		walls[0][0] = new Tile(TileSet.stoneWall.getTile(0, 0), -1, -1); // TL corner
		walls[0][6] = new Tile(TileSet.stoneWall.getTile(0, 2), -1, 5); // TR corner
		walls[6][0] = new Tile(TileSet.stoneWall.getTile(2, 0), 5, -1); // BL corner
		walls[6][6] = new Tile(TileSet.stoneWall.getTile(2, 2), 5, 5); // BR corner

		// Top row
		walls[0][1] = new Tile(TileSet.stoneWall.getTile(0, 1), -1, 0);
		walls[0][2] = new Tile(TileSet.stoneWall.getTile(0, 1), -1, 1);
		walls[0][3] = new Tile(TileSet.stoneWall.getTile(0, 1), -1, 2);
		walls[0][4] = new Tile(TileSet.stoneWall.getTile(0, 1), -1, 3);
		walls[0][5] = new Tile(TileSet.stoneWall.getTile(0, 1), -1, 4);

		// bottom row
		walls[6][1] = new Tile(TileSet.stoneWall.getTile(0, 1), 5, 0);
		walls[6][2] = new Tile(TileSet.stoneWall.getTile(0, 1), 5, 1);
		walls[6][3] = new Tile(TileSet.stoneWall.getTile(0, 1), 5, 2);
		walls[6][4] = new Tile(TileSet.stoneWall.getTile(0, 1), 5, 3);
		walls[6][5] = new Tile(TileSet.stoneWall.getTile(0, 1), 5, 4);

		// left side
		walls[1][0] = new Tile(TileSet.stoneWall.getTile(1, 0), 0, -1);
		walls[2][0] = new Tile(TileSet.stoneWall.getTile(1, 0), 1, -1);
		walls[3][0] = new Tile(TileSet.stoneWall.getTile(1, 0), 2, -1);
		walls[4][0] = new Tile(TileSet.stoneWall.getTile(1, 0), 3, -1);
		walls[5][0] = new Tile(TileSet.stoneWall.getTile(1, 0), 4, -1);

		// right side
		walls[1][6] = new Tile(TileSet.stoneWall.getTile(1, 0), 0, 5);
		walls[2][6] = new Tile(TileSet.stoneWall.getTile(1, 0), 1, 5);
		walls[3][6] = new Tile(TileSet.stoneWall.getTile(1, 0), 2, 5);
		walls[4][6] = new Tile(TileSet.stoneWall.getTile(1, 0), 3, 5);
		walls[5][6] = new Tile(TileSet.stoneWall.getTile(1, 0), 4, 5);

	}

}
