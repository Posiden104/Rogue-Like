package com.joel.RogueLike.map;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.joel.RogueLike.map.Tile.Position;

public class Map {

	public Tile[][] map; // array of tiles for map

	// random # = random.nextInt(max - minimum + 1) + minimum
	Random rand;
	private boolean loadHall = true;

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

	public void up() {
		// Room r = new Room(7, 10, TileSet.stoneFloor, TileSet.stoneWall, 1);
		// loadRoom(r, 1, 1);
	}

	boolean down = true;

	public void down() {
		if (!down) {
			Hall h = new Hall(10, TileSet.stoneFloor, TileSet.stoneWall, false);
			Vector2 pos = loadHall(h, 10, 5);
			System.out.println(pos);
			// 19, 6
			Room r = new Room(7, 10, TileSet.stoneFloor, TileSet.stoneWall, 2);
			loadRoom(r, (int) pos.x + 1, (int) pos.y - 1 - rand.nextInt(r.getHeight()));
			down = true;
		}
	}

	public void left() {
		// Room r = new Room(7, 10, TileSet.stoneFloor, TileSet.stoneWall, 3);
		// loadRoom(r, 10, 0);
	}

	public void right() {

	}

	//TODO: make generated halls to pick from
	
	public void generateMap() {
		Random random = new Random(1L);
		Room r;
		Vector2 loc = new Vector2(-1, -1);

		for (int i = 0; i < 100; i++) {
			r = new Room();
			if (loadHall) { // for Halls size 5 - 20
				if ((loc = pickLocation()) == null) {
					continue;
				}
				if(map[(int) loc.y][(int) loc.x].getPos() == Position.EAST) {
					loc = pickLocation();
				}
			} else { // for Rooms size: 5 - 10
				r = new Room(rand.nextInt(6) + 5, rand.nextInt(6) + 5, TileSet.stoneFloor, TileSet.stoneWall, i);
				if(loc.x < 0 || loc.y < 0) 
					continue;
				System.out.printf("%d, %d,\n w: %d, h: %d\n", (int) loc.x, (int) loc.y, r.getWidth(), r.getHeight());
//				System.out.println(map[(int) loc.y][(int) loc.x].getPos());
			}

			if (map[(int) loc.y][(int) loc.x] == null) {
				loadHall = true;
				continue;
			}

			switch (map[(int) loc.y][(int) loc.x].getPos()) {
			case NORTH:
				if (!loadHall) {
					loc.y += r.getHeight() + 1;
					loc.x -= random.nextInt(r.getWidth());
				} else {
					loc.y += r.getHeight();
					loc.x -= 1;
					r = new Hall(rand.nextInt(15) + 5, TileSet.stoneFloor, TileSet.stoneWall, true);
				}
				break;
			case SOUTH:
				if (!loadHall) {
					loc.y -= 1;
					loc.x -= random.nextInt(r.getWidth());
				} else {
					loc.y += 1;
					loc.x -= 1;
					r = new Hall(rand.nextInt(15) + 5, TileSet.stoneFloor, TileSet.stoneWall, true);
				}
				break;
			case EAST:
				if (!loadHall) {
					loc.y -= random.nextInt(r.getHeight());
					loc.x += 1;
				} else {
					loc.y -= 1;
					loc.x += 1;
					r = new Hall(rand.nextInt(15) + 5, TileSet.stoneFloor, TileSet.stoneWall, false);
				}
				break;
			case WEST:
				if (!loadHall) {
					loc.x -= r.getWidth() - 1;
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
				System.out.println("hall: " + (int) loc.x + ", " + (int) loc.y); 
				loc = loadHall((Hall) r, (int) loc.x, (int) loc.y);
				if (loc == null) {
					loadHall = true;
				}
			} else {
				loadHall = loadRoom(r, (int) loc.x, (int) loc.y);
				loadHall = true;
			}
		}

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
					System.out.println("north");
					return new Vector2(x, y);
				case SOUTH:
					System.out.println("south");
					return new Vector2(x, y);
				case WEST:
					System.out.println("west");
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
		// Used to buffer the room
		Tile[][] tempMap = copyMap();
		// Load the room
		Tile[][] room = r.getRoom();

		if (y + r.getHeight() >= map.length || y < 0)
			return false;
		else if (x + r.getWidth() >= map[0].length || x < 0)
			return false;

		for (int i = y; i < r.getHeight() + y; i++) {
			for (int j = x; j < r.getWidth() + x; j++) {
				if (tempMap[i][j] == null) {
					tempMap[i][j] = room[i - y][j - x];
				} else {
					return false;
				}
			}
		}

		// at this point, the room is valid, load it
		map = tempMap;

		loadHall = true;

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

			loadHall = false;

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
