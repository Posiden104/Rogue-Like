package com.joel.RogueLike.map;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.joel.RogueLike.map.Tile.Position;

public class Map {

	private boolean roomLoaded = false;

	public Tile[][] map; // array of tiles for map
	public Tile[][] walls;

	// random # = random.nextInt(max - minimum + 1) + minimum
	Random rand;
	private boolean loadHall = false;
	private int numRooms = 0;

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
		// hall();
	}

	public void generateMap() {
		Random random = new Random(1L);
		Room r;
		Vector2 loc = new Vector2(-1, -1);
		boolean isHall;

		for (int i = 0; i < 60; i++) {
			if (loadHall) { // for Halls size 5 - 20
				r = new Room();
				isHall = true;
			} else { // for Rooms size: 5 - 10
				r = new Room();
				isHall = true;
			}

			if (!roomLoaded) {
				r = new Room(rand.nextInt(6) + 5, rand.nextInt(6) + 5, TileSet.stoneFloor, TileSet.stoneWall, i);
				loadRoom(r, 1, 1);
				continue;
			}

			if (loc == null || (loc.x == -1 && loc.y == -1)) {
				loc = pickLocation();
				if (loc == null || (loc.x == -1 && loc.y == -1)) {
					continue;
				}
			}

			if (map[(int) loc.y][(int) loc.x] == null)
				return;

			switch (map[(int) loc.y][(int) loc.x].getPos()) {
			case NORTH:
				if (!isHall) {
					loc.y += r.getHeight();
					loc.x -= random.nextInt(r.getWidth());
				} else {
					loc.y += r.getHeight();
					loc.x -= 1;
					r = new Hall(rand.nextInt(15) + 5, TileSet.stoneFloor, TileSet.stoneWall, true);
				}
				break;
			case SOUTH:
				if (!isHall) {
					loc.y -= 1;
					loc.x -= random.nextInt(r.getWidth());
				} else {
					loc.y -= 1;
					loc.x -= 1;
					r = new Hall(rand.nextInt(15) + 5, TileSet.stoneFloor, TileSet.stoneWall, true);
				}
				break;
			case EAST:
				if (!isHall) {
					loc.x += 1;
					loc.y -= random.nextInt(r.getHeight());
				} else {
					loc.x += 1;
					loc.y -= 1;
					r = new Hall(rand.nextInt(15) + 5, TileSet.stoneFloor, TileSet.stoneWall, false);
				}
				break;
			case WEST:
				if (!isHall) {
					loc.x -= r.getWidth();
					loc.y -= random.nextInt(r.getHeight());
				} else {
					loc.x -= r.getWidth();
					loc.y -= 1;
					r = new Hall(rand.nextInt(15) + 5, TileSet.stoneFloor, TileSet.stoneWall, false);
				}
				break;
			case CORNER:
				continue;
			case MIDDLE:
				continue;
			default:
				continue;

			}

			if (r instanceof Hall) {
				loc = loadHall((Hall) r, map, (int) loc.x, (int) loc.y);
				if (loc == null) {
					loadHall = true;
				}
			} else {
				loadRoom(r, (int) loc.x, (int) loc.y);
				loc = new Vector2(-1, -1);
			}
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
			Tile t = map[y][x];
			if (t != null && t.isSolid() && t.getPos() != Position.CORNER && t.getPos() != Position.MIDDLE) {
				cont = false;
			}
			if (cont && count >= 200)
				return new Vector2(-1, -1);
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
					try {
						bufferMap[i][j] = room[i - y][j - x];
					} catch (NullPointerException e) {
						System.out.println(room);
						System.out.printf("i: %d, j: %d\ny: %d, x: %d\n", i, j, x, y);
						e.printStackTrace();
						System.exit(1);
					}
				} else {
					return;
				}
			}
		}

			Tile[][] bufferWall = new Tile[map.length][map[0].length];

			// Load the walls
			Tile[][] wall = r.getWalls();
			for (int i = 0; i < r.getHeight() + 2; i++) {
				for (int j = 0; j < r.getWidth() + 2; j++) {
					if (wall[i][j] != null) {
						int yp = y - 1 + i;
						int xp = x - 1 + j;

						if (yp >= map.length) {
							loadRoom(r, x, y - 1);
							return;
						} else if (yp < 0) {
							loadRoom(r, x, y + 1);
							return;
						} else if (xp >= map[yp].length) {
							loadRoom(r, x - 1, y);
							return;
						} else if (xp < 0) {
							loadRoom(r, x + 1, y);
							return;
						}

						bufferWall[yp][xp] = wall[i][j];
						bufferWall[yp][xp].setSolid(true);
					}
				}
			}
			bufferMap(bufferWall);

		bufferMap(bufferMap);
		roomLoaded = true;
		loadHall = true;

		System.out.printf("Room %d loaded\n", r.getRoomNumber());
		System.out.printf("at postion x: %d, y: %d\n", x, y);
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

	public Vector2 loadHall(Hall h, Tile[][] map, int x, int y) {
		Vector2 ret = null;
		// Used to buffer the hall
		Tile[][] bufferMap = new Tile[map.length][map[0].length];

		Tile[][] hall = h.getHall();

		// Load the floor
		for (int i = y; i < h.getHeight() + y; i++) {
			for (int j = x; j < h.getWidth() + x; j++) {
				if (i >= map.length || i < 0) {
					bufferMap = null;
					return null;
				} else if (j >= map[i].length || j < 0) {
					bufferMap = null;
					return null;
				}
				if (map[i][j] == null) {
					bufferMap[i][j] = hall[i - y][j - x];
				} else {
					bufferMap = null;
					return null;
				}
			}
		}

		ret = new Vector2(h.getEndPt().y + x, h.getEndPt().x + y);

		bufferMap(bufferMap);

		Room r = new Room(rand.nextInt(6) + 5, rand.nextInt(6) + 5, TileSet.stoneFloor, TileSet.stoneWall, ++numRooms);
		loadRoom(r, (int) ret.x, (int) ret.y);

		loadHall = false;

		System.out.printf("Hall location: x: %d, y: %d\n", (int) ret.x, (int) ret.y);
		System.out.println("is orientation: " + map[(int) ret.y][(int) ret.x].getPos());

		return ret;
	}

	public void hall() {
		Hall h = new Hall(10, TileSet.stoneFloor, TileSet.stoneWall, true);
		loadHall(h, map, 1, 1);
	}

	public void testMap() {

		// Map
		map = new Tile[5][5];

		// Corners
		map[0][0] = new Tile(TileSet.stoneFloor.getTile(0, 0)); // TL
		map[0][4] = new Tile(TileSet.stoneFloor.getTile(0, 2)); // TR
		map[4][0] = new Tile(TileSet.stoneFloor.getTile(2, 0)); // BL
		map[4][4] = new Tile(TileSet.stoneFloor.getTile(2, 2)); // BR

		// Sides
		map[0][1] = new Tile(TileSet.stoneFloor.getTile(0, 1)); //
		map[0][2] = new Tile(TileSet.stoneFloor.getTile(0, 1)); // Top
		map[0][3] = new Tile(TileSet.stoneFloor.getTile(0, 1)); // wall

		map[4][1] = new Tile(TileSet.stoneFloor.getTile(2, 1)); //
		map[4][2] = new Tile(TileSet.stoneFloor.getTile(2, 1)); // Bottom
		map[4][3] = new Tile(TileSet.stoneFloor.getTile(2, 1)); // wall

		map[1][0] = new Tile(TileSet.stoneFloor.getTile(1, 0)); //
		map[2][0] = new Tile(TileSet.stoneFloor.getTile(1, 0)); // Left
		map[3][0] = new Tile(TileSet.stoneFloor.getTile(1, 0)); // wall

		map[1][4] = new Tile(TileSet.stoneFloor.getTile(1, 2)); //
		map[2][4] = new Tile(TileSet.stoneFloor.getTile(1, 2)); // Right
		map[3][4] = new Tile(TileSet.stoneFloor.getTile(1, 2)); // wall

		// Middle
		map[1][1] = new Tile(TileSet.stoneFloor.getTile(1, 1)); //
		map[1][2] = new Tile(TileSet.stoneFloor.getTile(1, 1)); // Middle
		map[1][3] = new Tile(TileSet.stoneFloor.getTile(1, 1)); //

		map[2][1] = new Tile(TileSet.stoneFloor.getTile(1, 1)); //
		map[2][2] = new Tile(TileSet.stoneFloor.getTile(1, 1)); // Middle
		map[2][3] = new Tile(TileSet.stoneFloor.getTile(1, 1)); //

		map[3][1] = new Tile(TileSet.stoneFloor.getTile(1, 1)); //
		map[3][2] = new Tile(TileSet.stoneFloor.getTile(1, 1)); // Middle
		map[3][3] = new Tile(TileSet.stoneFloor.getTile(1, 1)); //

		// Walls
		walls = new Tile[7][7];

		// corners
		walls[0][0] = new Tile(TileSet.stoneWall.getTile(0, 0)); // TL
		walls[0][6] = new Tile(TileSet.stoneWall.getTile(0, 2)); // TR
		walls[6][0] = new Tile(TileSet.stoneWall.getTile(2, 0)); // BL
		walls[6][6] = new Tile(TileSet.stoneWall.getTile(2, 2)); // BR

		// Top row
		walls[0][1] = new Tile(TileSet.stoneWall.getTile(0, 1));
		walls[0][2] = new Tile(TileSet.stoneWall.getTile(0, 1));
		walls[0][3] = new Tile(TileSet.stoneWall.getTile(0, 1));
		walls[0][4] = new Tile(TileSet.stoneWall.getTile(0, 1));
		walls[0][5] = new Tile(TileSet.stoneWall.getTile(0, 1));

		// bottom row
		walls[6][1] = new Tile(TileSet.stoneWall.getTile(0, 1));
		walls[6][2] = new Tile(TileSet.stoneWall.getTile(0, 1));
		walls[6][3] = new Tile(TileSet.stoneWall.getTile(0, 1));
		walls[6][4] = new Tile(TileSet.stoneWall.getTile(0, 1));
		walls[6][5] = new Tile(TileSet.stoneWall.getTile(0, 1));

		// left side
		walls[1][0] = new Tile(TileSet.stoneWall.getTile(1, 0));
		walls[2][0] = new Tile(TileSet.stoneWall.getTile(1, 0));
		walls[3][0] = new Tile(TileSet.stoneWall.getTile(1, 0));
		walls[4][0] = new Tile(TileSet.stoneWall.getTile(1, 0));
		walls[5][0] = new Tile(TileSet.stoneWall.getTile(1, 0));

		// right side
		walls[1][6] = new Tile(TileSet.stoneWall.getTile(1, 0));
		walls[2][6] = new Tile(TileSet.stoneWall.getTile(1, 0));
		walls[3][6] = new Tile(TileSet.stoneWall.getTile(1, 0));
		walls[4][6] = new Tile(TileSet.stoneWall.getTile(1, 0));
		walls[5][6] = new Tile(TileSet.stoneWall.getTile(1, 0));

	}

}
