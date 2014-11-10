package com.joel.RogueLike.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Room {

	private int w;
	private int h;
	private int horz; // room.length
	private int vert; // room[0].length

	private Tile[][] room;
	private TileSet floor;
//	private TileSet walls;

	private OrthographicCamera roomCam;

	public Room(int w, int h, TileSet floor, TileSet walls) {
		roomCam = new OrthographicCamera();

		room = new Tile[w][h];
		this.floor = floor;
//		this.walls = walls;
		
		horz = w;
		vert = h;
		
		buildRoom();
	}

	private void buildRoom() {

		buildCorners();
		buildEdges();
		fillMiddle();
	
	}

	private void buildCorners() {
		// top left
		room[0][0] = new Tile(floor.getTile(0, 0), 0, 0);

		// top right
		room[0][vert - 1] = new Tile(floor.getTile(0, 2), 0, vert -1);

		// bottom left
		room[horz - 1][0] = new Tile(floor.getTile(2, 0), horz - 1, 0);

		// bottom right
		room[horz - 1][vert - 1] = new Tile(floor.getTile(2, 2), horz - 1, vert - 1);
	}

	private void buildEdges() {
		// horizontal edges
		for (int i = 1; i < horz - 1; i++) {
			room[0][i] = new Tile(floor.getTile(0, 1), 0, i); // top edge
			room[vert - 1][i] = new Tile(floor.getTile(2, 1), vert - 1, i); // bottom edge
		}

		// vertical edges
		for (int i = 1; i < vert - 1; i++) {
			room[i][0] = new Tile(floor.getTile(1, 0), i, 0); // left edge
			room[i][horz - 1] = new Tile(floor.getTile(1, 2), i, horz - 1); // right edge
		}
	}

	private void fillMiddle() {
		for(int i = 0; i < horz; i++) {
			for(int j = 0; j < vert; j++) {
				if(room[i][j] == null) {
					room[i][j ]= new Tile(floor.getTile(1, 1), i, j);
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
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}

}
