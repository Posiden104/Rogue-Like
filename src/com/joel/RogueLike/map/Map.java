package com.joel.RogueLike.map;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.joel.RogueLike.map.Tile.Position;

public class Map {

	public Tile[][] map; // array of tiles for map

	// random # = random.nextInt(max - minimum + 1) + minimum
	Random rand;
	Random random = new Random(1L);

	// private int numRooms = 0;

	public Map() {
	}

	public Map(int w, int h, long seed) {
		map = new Tile[h][w];
		rand = new Random(seed);

		Room r = new Room(rand.nextInt(6) + 5, rand.nextInt(6) + 5, TileSet.stoneFloor, TileSet.stoneWall, 0);
		loadRoom(r, 0, 0);

		generateMap();
	}

	// maybe TODO: make generated halls to pick from

	public void generateMap() {
		Vector2 loc = new Vector2(-1, -1);

		for (int i = 0; i < 20; i++) {
			Tile[][] backupMap = copyMap();
			Hall h;
			Room r;
			Tile t;
			Tile hTile;
			Vector2 endPt;

			if ((loc = pickLocation()) == null) {
				continue;
			}

			if (loc.x < 0 || loc.y < 0) // should never be utilized
				continue;

			t = map[(int) loc.y][(int) loc.x];
			if (t == null) { // should never be utilized
				continue;
			}

			// for halls length 5 - 20
			h = new Hall(rand.nextInt(16) + 5, TileSet.stoneFloor, TileSet.stoneWall, (t.getPos() == Position.NORTH || t.getPos() == Position.SOUTH) ? true : false);
			loc = adjHall(h, loc);

			if ((endPt = loadHall(h, (int) loc.x, (int) loc.y)) != null) {
				System.out.printf("%d, %d\n", (int) endPt.x, (int) endPt.y);
				hTile = map[(int) endPt.y][(int) endPt.x];
			} else {
				map = backupMap;
				continue;
			}

			if (hTile == null) {
				map = backupMap;
				continue;
			}

			// for Rooms size: 5 - 10
			r = new Room(rand.nextInt(6) + 5, rand.nextInt(6) + 5, TileSet.stoneFloor, TileSet.stoneWall, i + 1);

			if (adjRoom(endPt, r, hTile.getPos()) == null) {
				System.out.println("loc = null");
				map = backupMap;
				continue;
			}
		}

	}

	public Vector2 adjRoom(Vector2 origin, Room r, Position p) {
		Vector2 loc = new Vector2(origin.x, origin.y);
		int newRan;
		switch (p) {
		case NORTH:
			loc.y += r.getHeight() + 1;

			for (int i = 0; i < 5; i++) {
				newRan = random.nextInt(r.getWidth());
				if (loadRoom(r, (int) loc.x - newRan, (int) loc.y)) {
					System.out.println("random");
					return new Vector2(loc.x - newRan, loc.y);
				}
			}

			for (int i = 0; i < r.getWidth(); i++) {
				loc.x -= 1;
				if (loadRoom(r, (int) loc.x, (int) loc.y)) {
					return loc;
				}
			}
			break;
		case SOUTH:
			loc.y += 1;

			for (int i = 0; i < 5; i++) {
				newRan = random.nextInt(r.getWidth());
				if (loadRoom(r, (int) loc.x - newRan, (int) loc.y)) {
					System.out.println("random");
					return new Vector2(loc.x - newRan, loc.y);
				}
			}

			for (int i = 0; i < r.getWidth(); i++) {
				loc.x -= 1;
				if (loadRoom(r, (int) loc.x, (int) loc.y)) {
					return loc;
				}
			}
			break;
		case EAST:
			loc.x += 1;

			for (int i = 0; i < 5; i++) {
				newRan = random.nextInt(r.getHeight());
				if (loadRoom(r, (int) loc.x, (int) loc.y - newRan)) {
					System.out.println("random");
					return new Vector2(loc.x, loc.y - newRan);
				}
			}

			for (int i = 0; i < r.getHeight(); i++) {
				loc.y -= 1;
				if (loadRoom(r, (int) loc.x, (int) loc.y)) {
					return loc;
				}
			}

			break;
		case WEST:
			loc.x -= r.getWidth() - 1;

			for (int i = 0; i < 5; i++) {
				newRan = random.nextInt(r.getHeight());
				System.out.println("random");
				if (loadRoom(r, (int) loc.x, (int) loc.y - newRan)) {
					return new Vector2(loc.x, loc.y - newRan);
				}
			}

			for (int i = 0; i < r.getHeight(); i++) {
				loc.y -= 1;
				if (loadRoom(r, (int) loc.x, (int) loc.y)) {
					return loc;
				}
			}
			break;
		case CORNER:
			break;
		case MIDDLE:
			break;
		default:
			break;

		}

		return null;
	}

	public Vector2 adjHall(Hall h, Vector2 loc) {
		switch (map[(int) loc.y][(int) loc.x].getPos()) {
		case NORTH:
			loc.y += h.getHeight() + 1;
			loc.x -= 1;
			break;
		case SOUTH:
			loc.y += 1;
			loc.x -= 1;
			break;
		case EAST:
			loc.y -= 1;
			loc.x += 1;
			break;
		case WEST:
			loc.x -= h.getWidth() - 1;
			loc.y -= 1;
			break;
		case CORNER:
			return null;
		case MIDDLE:
			return null;
		default:
			return null;

		}

		return loc;

	}

	public Vector2 pickLocation() {
		int x = -1;
		int y = -1;

		int count = 0;
		boolean flag = true;

		while (flag) {
			x = rand.nextInt(map.length);
			y = rand.nextInt(map[0].length);
			Tile t = map[y][x];

			if (t != null && t.isSolid()) {
				switch (t.getPos()) {
				case CORNER:
					break;
				case EAST:
					return new Vector2(x, y);
				case MIDDLE:
					break;
				case NORTH:
					return new Vector2(x, y);
				case SOUTH:
					return new Vector2(x, y);
				case WEST:
					return new Vector2(x, y);
				default:
					break;

				}
			}

			if (flag && count >= 1000)
				return null;
			count++;
		}
		return null;
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
	}

	public void render(SpriteBatch sb) {
		if (map != null) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j] != null) {
						map[i][j].render(sb, i, j);
					}
				}
			}
		}
	}

	public boolean loadRoom(Room r, int x, int y) {
		// Used to buffer the map
		Tile[][] buffMap = copyMap();
		// Load the room
		Tile[][] room = r.getRoom();

		if (y + r.getHeight() >= buffMap.length || y < 0) {
			return false;
		} else if (x + r.getWidth() >= buffMap[0].length || x < 0) {
			return false;
		}

		for (int i = y; i < r.getHeight() + y; i++) {
			for (int j = x; j < r.getWidth() + x; j++) {
				if (buffMap[i][j] == null) {
					buffMap[i][j] = room[i - y][j - x];
				} else {
					return false;
				}
			}
		}

		// At this point the room is valid, load it
		map = buffMap;

		if (!(r instanceof Hall)) {
			System.out.printf("Room %d loaded\n", r.getRoomNumber());
		}

		return true;
	}

	public Tile[][] copyMap() {
		Tile[][] t = new Tile[map.length][];

		for (int i = 0; i < map.length; i++) {
			t[i] = map[i].clone();
		}

		return t;
	}

	public Vector2 loadHall(Hall h, int x, int y) {
		Vector2 ret = null;

		if (loadRoom(h, x, y)) {

			ret = new Vector2(h.getEndPt().x + x, h.getEndPt().y + y);

			System.out.printf("Hall loaded\n");

			return ret;
		} else {
			return null;
		}
	}

	public void hall() {
		Hall h = new Hall(10, TileSet.stoneFloor, TileSet.stoneWall, true);
		loadHall(h, 1, 1);
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

		// // Walls
		// walls = new Tile[7][7];
		//
		// // corners
		// walls[0][0] = new Tile(TileSet.stoneWall.getTile(0, 0)); // TL
		// walls[0][6] = new Tile(TileSet.stoneWall.getTile(0, 2)); // TR
		// walls[6][0] = new Tile(TileSet.stoneWall.getTile(2, 0)); // BL
		// walls[6][6] = new Tile(TileSet.stoneWall.getTile(2, 2)); // BR
		//
		// // Top row
		// walls[0][1] = new Tile(TileSet.stoneWall.getTile(0, 1));
		// walls[0][2] = new Tile(TileSet.stoneWall.getTile(0, 1));
		// walls[0][3] = new Tile(TileSet.stoneWall.getTile(0, 1));
		// walls[0][4] = new Tile(TileSet.stoneWall.getTile(0, 1));
		// walls[0][5] = new Tile(TileSet.stoneWall.getTile(0, 1));
		//
		// // bottom row
		// walls[6][1] = new Tile(TileSet.stoneWall.getTile(0, 1));
		// walls[6][2] = new Tile(TileSet.stoneWall.getTile(0, 1));
		// walls[6][3] = new Tile(TileSet.stoneWall.getTile(0, 1));
		// walls[6][4] = new Tile(TileSet.stoneWall.getTile(0, 1));
		// walls[6][5] = new Tile(TileSet.stoneWall.getTile(0, 1));
		//
		// // left side
		// walls[1][0] = new Tile(TileSet.stoneWall.getTile(1, 0));
		// walls[2][0] = new Tile(TileSet.stoneWall.getTile(1, 0));
		// walls[3][0] = new Tile(TileSet.stoneWall.getTile(1, 0));
		// walls[4][0] = new Tile(TileSet.stoneWall.getTile(1, 0));
		// walls[5][0] = new Tile(TileSet.stoneWall.getTile(1, 0));
		//
		// // right side
		// walls[1][6] = new Tile(TileSet.stoneWall.getTile(1, 0));
		// walls[2][6] = new Tile(TileSet.stoneWall.getTile(1, 0));
		// walls[3][6] = new Tile(TileSet.stoneWall.getTile(1, 0));
		// walls[4][6] = new Tile(TileSet.stoneWall.getTile(1, 0));
		// walls[5][6] = new Tile(TileSet.stoneWall.getTile(1, 0));

	}

}
