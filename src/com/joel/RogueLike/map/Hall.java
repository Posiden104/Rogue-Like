package com.joel.RogueLike.map;

public class Hall {

	private int w;
	private int h;

	private boolean vert;

	private Tile[][] hall;

	
	private TileSet floor;
	private TileSet walls;

	public Hall(int w, int h, TileSet floor, TileSet walls, boolean vert) {
		hall = new Tile[h][w];

		this.w = w;
		this.h = h;

		this.vert = vert;

		this.floor = floor;
		this.walls = walls;

	}

	public void generate() {
		Room r = new Room(w, h, floor, walls, -1);
		loadHall(r);

		if (vert) {
			generateVertHall();
		} else {
			generateHorzHall();
		}
	}
	
	private void generateVertHall() {
		for(int i = 1; i < hall.length -1; i++) {
			hall[i][0] = new Tile(floor.getTile(1, 0), i, 0); // top edge
			// bottom edge
			hall[i][hall[i].length] = new Tile(floor.getTile(1, 2), i, hall[i].length);
		
		}
	}

	private void generateHorzHall() {

	}

	public void loadHall(Room r) {
		// Load the floor
		Tile[][] room = r.getRoom();
		for (int i = 1; i < room.length - 1; i++) {
			for (int j = 1; j < room[i].length - 1; j++) {
				if (hall[i][j] == null) {
					hall[i][j] = room[i][j];
				}
			}
		}

		// Load the walls
		Tile[][] wall = r.getWalls();
		for (int i = 0; i < wall.length; i++) {
			for (int j = 0; j < wall[i].length; j++) {
				if (hall[i][j] == null) {
					hall[i][j] = wall[i][j];
					hall[i][j].setSolid(true);
				}
			}
		}
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public boolean isVert() {
		return vert;
	}

}
