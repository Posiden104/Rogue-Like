package com.joel.RogueLike.map;

import com.joel.RogueLike.map.Tile.Position;

public class Hall {

	private int w;
	private int h;

	private boolean vert;

	private Tile[][] hall;
	public Room r;

	private TileSet floor;
	private TileSet walls;

	public Hall(int size, TileSet floor, TileSet walls, boolean vert) {
		if (vert) {
			this.w = 3;
			this.h = size;
			hall = new Tile[h][3];
		} else {
			this.w = size;
			this.h = 3;
			hall = new Tile[3][w];
		}

		this.vert = vert;

		this.floor = floor;
		this.walls = walls;

		generate();

	}

	public void generate() {

		if (vert) {
			generateVertHall();
		} else {
			generateHorzHall();
		}
	}

	private void generateVertHall() {
		buildEdges(3, h);
		buildWalls(3, h);
	}

	private void generateHorzHall() {
		buildEdges(w, 3);
		buildWalls(w, 3);
	}

	private void buildEdges(int w, int h) {
		if (vert) { // horizontal edges
			for (int i = 0; i < h; i++) {
				hall[i][1] = new Tile(floor.getTile(1, 1), i, 1); // top edge
			}
		} else { // vertical edges
			for (int i = 0; i < w; i++) {
				hall[1][i] = new Tile(floor.getTile(1, 1), 1, i); // left edge
			}
		}
	}

	private void buildWalls(int w, int h) {
		if (vert) { // Vertical sides
			for (int i = 0; i < h; i++) {
				hall[i][0] = new Tile(walls.getTile(1, 0), i, 0); // Left
				hall[i][0].setPosition(Position.WEST);
				hall[i][0].setSolid(true);

				hall[i][2] = new Tile(walls.getTile(1, 0), i, 2); // Right
				hall[i][2].setPosition(Position.EAST);
				hall[i][2].setSolid(true);
			}

		} else { // Horizontal sides
			for (int i = 0; i < w; i++) {
				hall[0][i] = new Tile(walls.getTile(0, 1), 0, i); // Top
				hall[0][i].setPosition(Position.NORTH);
				hall[0][i].setSolid(true);

				hall[2][i] = new Tile(walls.getTile(0, 1), 2, i); // Bottom
				hall[2][i].setPosition(Position.SOUTH);
				hall[2][i].setSolid(true);
			}

		}
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

	public boolean isVert() {
		return vert;
	}

	public Tile[][] getHall() {
		return hall;
	}

}
