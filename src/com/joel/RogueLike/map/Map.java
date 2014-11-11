package com.joel.RogueLike.map;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Map {

	public Tile[][] map; // array of tiles for map
	public Tile[][] walls;

	// random # = random.nextInt(max - minimum + 1) + minimum
	Random rand;

	public Map() {

		Room room1 = new Room(8, 6, TileSet.stoneFloor, TileSet.stoneWall, 0);
		map = room1.getRoom();
		walls = room1.getWalls();

		rand = new Random();
		// testMap();
	}

	public Map(int w, int h, long seed) {
		map = new Tile[h][w];

		generateMap(seed);
	}

	public void generateMap(long seed) {
		// Room size: 5 - 10
		for (int i = 0; i < 30; i++) {
			Room r = new Room(rand.nextInt(6) + 5, rand.nextInt(6) + 5, TileSet.stoneFloor, TileSet.stoneWall, i);
			Vector2 loc = pickLocation();
			// 5 to 95
			loadRoom(r, (int) loc.x, (int) loc.y);
		}

	}

	public Vector2 pickLocation() {
		int x;
		int y;
		
		do {
			x = rand.nextInt(map.length);
			y = rand.nextInt(map[0].length);
		} while (!map[y][x].isSolid());
		
		return new Vector2(x, y);
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
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j] != null)
						map[i][j].render(sb, i, j);
				}
			}
		}

		if (walls != null) {
			for (int i = 0; i < walls.length; i++) {
				for (int j = 0; j < walls[i].length; j++) {
					if (walls[i][j] != null)
						walls[i][j].render(sb, i - 1, j - 1);
				}
			}
		}

		TileSet.stoneWall.render(sb);
	}

	public void loadRoom(Room r, int x, int y) {
		// Used to buffer the room
		Tile[][] bufferMap = new Tile[map.length][map[0].length];
		// Load the floor
		Tile[][] room = r.getRoom();
		for (int i = y; i < r.getHeight() + y; i++) {
			for (int j = x; j < r.getWidth() + x; j++) {
				if (i >= map.length || i < 0)
					return;
				else if (j >= map[i].length || j < 0)
					return;

				if (map[i][j] == null) {
					bufferMap[i][j] = room[i - y][j - x];
				} else {
					return;
				}
			}
		}

		bufferMap(bufferMap);

		// Load the walls
		Tile[][] wall = r.getWalls();
		for (int i = 0; i < r.getHeight() + 2; i++) {
			for (int j = 0; j < r.getWidth() + 2; j++) {
				if (wall[i][j] != null) {
					int yp = y - 1 + i;
					int xp = x - 1 + j;
					map[yp][xp] = wall[i][j];
					map[yp][xp].setSolid(true);
				}
			}
		}
		System.out.printf("Room %d loaded\n", r.getRoomNumber());
	}

	public void bufferMap(Tile[][] bMap) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Tile t = bMap[i][j];
				if (t != null) {
					map[i][j] = t;
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
		map[0][0] = new Tile(TileSet.stoneFloor.getTile(0, 0), 0, 0); // TL
		map[0][4] = new Tile(TileSet.stoneFloor.getTile(0, 2), 0, 4); // TR
		map[4][0] = new Tile(TileSet.stoneFloor.getTile(2, 0), 4, 0); // BL
		map[4][4] = new Tile(TileSet.stoneFloor.getTile(2, 2), 4, 4); // BR

		// Sides
		map[0][1] = new Tile(TileSet.stoneFloor.getTile(0, 1), 0, 1); //
		map[0][2] = new Tile(TileSet.stoneFloor.getTile(0, 1), 0, 2); // Top
		map[0][3] = new Tile(TileSet.stoneFloor.getTile(0, 1), 0, 3); // wall

		map[4][1] = new Tile(TileSet.stoneFloor.getTile(2, 1), 4, 1); //
		map[4][2] = new Tile(TileSet.stoneFloor.getTile(2, 1), 4, 2); // Bottom
		map[4][3] = new Tile(TileSet.stoneFloor.getTile(2, 1), 4, 3); // wall

		map[1][0] = new Tile(TileSet.stoneFloor.getTile(1, 0), 1, 0); //
		map[2][0] = new Tile(TileSet.stoneFloor.getTile(1, 0), 2, 0); // Left
		map[3][0] = new Tile(TileSet.stoneFloor.getTile(1, 0), 3, 0); // wall

		map[1][4] = new Tile(TileSet.stoneFloor.getTile(1, 2), 1, 4); //
		map[2][4] = new Tile(TileSet.stoneFloor.getTile(1, 2), 2, 4); // Right
		map[3][4] = new Tile(TileSet.stoneFloor.getTile(1, 2), 3, 4); // wall

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
		walls[0][0] = new Tile(TileSet.stoneWall.getTile(0, 0), -1, -1); // TL
		walls[0][6] = new Tile(TileSet.stoneWall.getTile(0, 2), -1, 5); // TR
		walls[6][0] = new Tile(TileSet.stoneWall.getTile(2, 0), 5, -1); // BL
		walls[6][6] = new Tile(TileSet.stoneWall.getTile(2, 2), 5, 5); // BR

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
