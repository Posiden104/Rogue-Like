package com.joel.RogueLike.map;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Map {

	private boolean roomLoaded = false;

	public Tile[][] map; // array of tiles for map
	public Tile[][] walls;

	// random # = random.nextInt(max - minimum + 1) + minimum
	Random rand;

	public Map() {

		Room room1 = new Room(8, 6, TileSet.stoneFloor, TileSet.stoneWall, 0);
		map = room1.getRoom();
		walls = room1.getWalls();

		rand = new Random(0L);
		// testMap();
	}

	public Map(int w, int h, long seed) {
		map = new Tile[h][w];
		rand = new Random(seed);

		 generateMap();
		// testMap();
//		hall();
	}

	public void generateMap() {
		// Room size: 5 - 10
		for (int i = 0; i < 30; i++) {
			Room r = new Room(rand.nextInt(6) + 5, rand.nextInt(6) + 5, TileSet.stoneFloor, TileSet.stoneWall, i);
			if (!roomLoaded) {
				loadRoom(r, map, 1, 1, true);
				continue;
			}

//			Vector2 loc = pickLocation();
//			if (loc == null) {
//				continue;
//			}
			// 5 to 95
//			loadRoom(r, map, (int) loc.x, (int) loc.y, true);
			loadRoom(r, map, rand.nextInt(91) + 5, rand.nextInt(91) + 5, true);

		}

	}

	public Vector2 pickLocation() {
		int x;
		int y;

		int count = 0;
		boolean cont = true;

		do {
			x = rand.nextInt(map.length);
			y = rand.nextInt(map[0].length);
			count++;
			if (map[y][x] != null && map[y][x].isSolid()) {
				map[y][x] = new Tile(TileSet.stoneWall.getTile(0, 2), y, x);
				cont = false;
			}
			if (cont && count >= 100)
				return null;
		} while (cont);

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

	public void loadRoom(Room r, Tile[][] map, int x, int y, boolean isRoom) {
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

		if (isRoom) {
			Tile[][] bufferWall = new Tile[map.length][map[0].length];

			// Load the walls
			Tile[][] wall = r.getWalls();
			for (int i = 0; i < r.getHeight() + 2; i++) {
				for (int j = 0; j < r.getWidth() + 2; j++) {
					if (wall[i][j] != null) {
						int yp = y - 1 + i;
						int xp = x - 1 + j;

						if (yp >= map.length) {
							loadRoom(r, map, x, y - 1, true);
							return;
						} else if (yp < 0) {
							loadRoom(r, map, x, y + 1, true);
							return;
						} else if (xp >= map[yp].length) {
							loadRoom(r, map, x - 1, y, true);
							return;
						} else if (xp < 0) {
							loadRoom(r, map, x + 1, y, true);
							return;
						}

						bufferWall[yp][xp] = wall[i][j];
						bufferWall[yp][xp].setSolid(true);
					}
				}
			}
			bufferMap(bufferWall);
		}

		bufferMap(bufferMap);
		roomLoaded = true;

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

	public void loadHall(Hall h, Tile[][] map, int x, int y) {
		// Used to buffer the hall
		Tile[][] bufferMap = new Tile[map.length][map[0].length];

		Tile[][] hall = h.getHall();
		
		// Load the floor
		for (int i = y; i < h.getHeight() + y; i++) {
			for (int j = x; j < h.getWidth() + x; j++) {
				if (i >= map.length || i < 0)
					return;
				else if (j >= map[i].length || j < 0)
					return;

				if (map[i][j] == null) {
					bufferMap[i][j] = hall[i - y][j - x];
				} else {
					return;
				}
			}
		}
		
		bufferMap(bufferMap);
	}

	public void hall() {
		Hall h = new Hall(10, TileSet.stoneFloor, TileSet.stoneWall, false);
		loadHall(h, map, 1, 1);
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
