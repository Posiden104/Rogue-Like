package com.joel.RogueLike.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Room {

	private int w;
	private int h;
	private int vert; // room.length
	private int horz; // room[0].length

	private Tile[][] room;
	private Tile[][] wall;
	private TileSet floor;
	private TileSet walls;

	private OrthographicCamera roomCam;

	public Room(int w, int h, TileSet floor, TileSet walls) {
		roomCam = new OrthographicCamera();

		room = new Tile[h][w];
		wall = new Tile[h + 2][w + 2];

		this.floor = floor;
		this.walls = walls;

		vert = h;
		horz = w;

		buildRoom();
		buildWalls(vert + 2, horz + 2);
	}

	private void buildRoom() {

		buildCorners();
		buildEdges();
		fillMiddle();

	}

	private void buildWalls(int vert, int horz) {
		// Corners
		// TL corner
		wall[0][0] = new Tile(walls.getTile(0, 0), -1, -1);
		// TR corner
		wall[0][horz- 1] = new Tile(walls.getTile(0, 2), -1, horz - 2);
		// BL corner
		wall[vert - 1][0] = new Tile(walls.getTile(2, 0), vert - 2, -1);
		// BR corner
		wall[vert - 1][horz - 1] = new Tile(walls.getTile(2, 2), vert - 2, horz - 2);

		// Horizontal sides
		for (int i = 1; i < horz - 1; i++) {
			wall[0][i] = new Tile(walls.getTile(0, 1), -1, i - 1); // Top
			wall[vert - 1][i] = new Tile(walls.getTile(0, 1), vert - 2, i - 1); // Bottom
		}

		// Vertical sides
		for (int i = 1; i < vert - 1; i++) {
			wall[i][0] = new Tile(walls.getTile(1, 0), i - 1, -1); // Left
			wall[i][horz - 1] = new Tile(walls.getTile(1, 0), i - 1, horz - 2); // Right
		}

	}

	private void buildCorners() {
		// top left
		room[0][0] = new Tile(floor.getTile(0, 0), 0, 0);

		// top right
		room[0][horz - 1] = new Tile(floor.getTile(0, 2), 0, horz - 1);

		// bottom left
		room[vert - 1][0] = new Tile(floor.getTile(2, 0), vert - 1, 0);

		// bottom right
		room[vert - 1][horz - 1] = new Tile(floor.getTile(2, 2), vert - 1, horz - 1);
	}

	private void buildEdges() {
		// horizontal edges
		for (int i = 1; i < vert - 1; i++) {
			room[i][0] = new Tile(floor.getTile(1, 0), i ,0); // top edge
			// bottom edge
			room[i][horz - 1] = new Tile(floor.getTile(1, 2), i, horz - 1);
		}

		// vertical edges
		for (int i = 1; i < horz - 1; i++) {
			room[0][i] = new Tile(floor.getTile(0, 1), 0, i); // left edge
			// right edge
			room[vert - 1][i] = new Tile(floor.getTile(2, 1), vert - 1, i);
		}
	}

	private void fillMiddle() {
		for (int i = 0; i < vert; i++) {
			for (int j = 0; j < horz; j++) {
				if (room[i][j] == null) {
					room[i][j] = new Tile(floor.getTile(1, 1), i, j);
				}
			}
		}

	}

	public void render(SpriteBatch sb) {

		sb.setTransformMatrix(roomCam.combined);

		sb.begin();
		sb.end();
	}

	public Tile[][] getRoom() {
		return room;
	}

	public Tile[][] getWalls() {
		return wall;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

}
