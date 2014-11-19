package com.joel.RogueLike.map;

import com.badlogic.gdx.math.Vector2;
import com.joel.RogueLike.map.Tile.Position;

public class Hall extends Room {

	private int w;
	private int h;

	private boolean vert;

	private Tile[][] hall;
	public Room r;
	
	private Vector2 endPt;

	private TileSet floor;
	private TileSet walls;

	public Hall(int size, TileSet floor, TileSet walls, boolean vert) {
		super();
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
			buildHall(3, h);
		} else {
			buildHall(w, 3);
		}
	}

	private void buildHall(int w, int h) {
		if (vert) { // Vertical sides
			for (int i = 0; i < h; i++) {
				hall[i][0] = new Tile(walls.getTile(1, 0)); // Left wall
				hall[i][0].setPosition(Position.WEST);
				hall[i][0].setSolid(true);

				hall[i][1] = new Tile(floor.getTile(1, 3)); // Middle
				hall[i][1].setPosition(Position.MIDDLE);
				hall[i][1].setSolid(false);

				hall[i][2] = new Tile(walls.getTile(1, 0)); // Right wall
				hall[i][2].setPosition(Position.EAST);
				hall[i][2].setSolid(true);
			}
			
			hall[0][1] = new Tile(floor.getTile(0, 3)); // North tile
			hall[0][1].setPosition(Position.NORTH);
			hall[0][1].setSolid(false);
			
			hall[h - 1][1] = new Tile(floor.getTile(2, 3)); // South tile
			hall[h - 1][1].setPosition(Position.SOUTH);
			hall[h - 1][1].setSolid(false);
			
			endPt = new Vector2(h - 1, 1);

		} else { // Horizontal sides
			for (int i = 0; i < w; i++) {
				hall[0][i] = new Tile(walls.getTile(0, 1)); // Top wall
				hall[0][i].setPosition(Position.NORTH);
				hall[0][i].setSolid(true);

				hall[1][i] = new Tile(floor.getTile(1, 5)); // Middle
				hall[1][i].setPosition(Position.MIDDLE);
				hall[1][i].setSolid(false);

				hall[2][i] = new Tile(walls.getTile(0, 1)); // Bottom wall
				hall[2][i].setPosition(Position.SOUTH);
				hall[2][i].setSolid(true);
			}
			
			hall[1][0] = new Tile(floor.getTile(1, 4)); // East tile
			hall[1][0].setPosition(Position.WEST);
			hall[1][0].setSolid(false);
			
			hall[1][w - 1] = new Tile(floor.getTile(1, 6)); // West tile
			hall[1][w - 1].setPosition(Position.EAST);
			hall[1][w - 1].setSolid(false);

			endPt = new Vector2(1, w - 1);
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

	public Tile[][] getRoom() {
		return hall;
	}

	public Vector2 getEndPt() {
		return endPt;
	}
	
}
