package com.joel.RogueLike.map;

import com.joel.RogueLike.map.Tile.Position;

public class Room {

	private int w;
	private int h;
	private int vert; // room.length
	private int horz; // room[0].length
	private int roomNum;

	private Tile[][] room;
	private Tile[][] floor;
	private Tile[][] wall;
	private TileSet floors;
	private TileSet walls;

	public Room(int w, int h, TileSet floors, TileSet walls, int num) {
		floor = new Tile[h][w];
		wall = new Tile[h + 2][w + 2];
		room = new Tile[h + 2][w + 2];

		this.roomNum = num;

		this.floors = floors;
		this.walls = walls;

		vert = this.h = h;
		horz = this.w = w;

		this.h += 2;
		this.w += 2;
		
		buildRoom();
		buildWalls(vert + 2, horz + 2);
		combine();
	}

	public Room() {
	} // used for Halls

	private void buildRoom() {

		buildCorners();
		buildEdges();
		fillMiddle();

		for (Tile[] t : floor) {
			for (Tile t2 : t) {
				t2.setSolid(false);
				t2.setPosition(Position.MIDDLE);
			}
		}

	}

	private void buildWalls(int vert, int horz) {
		// Corners
		// TL corner
		wall[0][0] = new Tile(walls.getTile(0, 0));
		wall[0][0].setPosition(Position.CORNER);

		// TR corner
		wall[0][horz - 1] = new Tile(walls.getTile(0, 2));
		wall[0][horz - 1].setPosition(Position.CORNER);

		// BL corner
		wall[vert - 1][0] = new Tile(walls.getTile(2, 0));
		wall[vert - 1][0].setPosition(Position.CORNER);

		// BR corner
		wall[vert - 1][horz - 1] = new Tile(walls.getTile(2, 2));
		wall[vert - 1][horz - 1].setPosition(Position.CORNER);

		// Horizontal sides
		for (int i = 1; i < horz - 1; i++) {
			wall[0][i] = new Tile(walls.getTile(0, 1)); // Top
			wall[0][i].setPosition(Position.NORTH);

			wall[vert - 1][i] = new Tile(walls.getTile(0, 1)); // Bottom
			wall[vert - 1][i].setPosition(Position.SOUTH);
		}

		// Vertical sides
		for (int i = 1; i < vert - 1; i++) {
			wall[i][0] = new Tile(walls.getTile(1, 0)); // Left
			wall[i][0].setPosition(Position.WEST);

			wall[i][horz - 1] = new Tile(walls.getTile(1, 0)); // Right
			wall[i][horz - 1].setPosition(Position.EAST);
		}

		for (Tile[] t : wall) {
			for (Tile t2 : t) {
				if (t2 != null)
					t2.setSolid(true);
			}
		}
	}

	private void buildCorners() {
		// top left
		floor[0][0] = new Tile(floors.getTile(0, 0));

		// top right
		floor[0][horz - 1] = new Tile(floors.getTile(0, 2));

		// bottom left
		floor[vert - 1][0] = new Tile(floors.getTile(2, 0));

		// bottom right
		floor[vert - 1][horz - 1] = new Tile(floors.getTile(2, 2));
	}

	private void buildEdges() {
		// horizontal edges
		for (int i = 1; i < vert - 1; i++) {
			floor[i][0] = new Tile(floors.getTile(1, 0)); // top edge
			// bottom edge
			floor[i][horz - 1] = new Tile(floors.getTile(1, 2));
		}

		// vertical edges
		for (int i = 1; i < horz - 1; i++) {
			floor[0][i] = new Tile(floors.getTile(0, 1)); // left edge
			// right edge
			floor[vert - 1][i] = new Tile(floors.getTile(2, 1));
		}
	}

	private void fillMiddle() {
		for (int i = 0; i < vert; i++) {
			for (int j = 0; j < horz; j++) {
				if (floor[i][j] == null) {
					floor[i][j] = new Tile(floors.getTile(1, 1));
				}
			}
		}

	}

	private void combine() {
		
		room = new Tile[wall.length][];

		for (int i = 0; i < room.length; i++) {
			room[i] = wall[i].clone(); 
		}

		for (int i = 1; i < room.length - 1; i++) {
			for (int j = 1; j < room[i].length - 1; j++) {
				room[i][j] = floor[i - 1][j - 1];
			}
		}
	}

	public void printRoom() {
		for(int i = 0; i < room.length; i++) {
			for (int j = 0; j < room[i].length; j++) {
				if(room[i][j].isSolid()) {
					System.out.print("#");
				} else {
					System.out.print("_");
				}
			}
			System.out.println();
		}
	}
	
	public Tile[][] getRoom() {
		return room;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

	public int getRoomNumber() {
		return roomNum;
	}

}
