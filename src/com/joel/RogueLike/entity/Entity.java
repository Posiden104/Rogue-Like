package com.joel.RogueLike.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Entity {

	protected int hp; // Health Points determine life force
	protected int str; // Strength determines damage + carrying capacity
	protected int speed; // Speed determines movement speed

	protected int x;
	protected int y;

	public Entity() {
	}

	public Entity(int x, int y, int hp) {
		this.hp = hp;
		this.x = x;
		this.y = y;
	}

	public Entity(int x, int y, int hp, int str, int speed) {
		this.hp = hp;
		this.str = str;
		this.speed = speed;
		this.x = x;
		this.y = y;
	}

	public void render(SpriteBatch sb) {

	}

	public void update(float dt) {

	}

	public int getHP() {
		return hp;
	}

	public int getSTR() {
		return str;
	}

	public int getSpeed() {
		return speed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
