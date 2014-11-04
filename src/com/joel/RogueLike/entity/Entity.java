package com.joel.RogueLike.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Entity {

	private int hp; // Health Points determine life force
	private int str; // Strength determines damage + carrying capacity
	private int speed; // Speed determines movement speed
	
	private int x;
	private int y;

	public Entity() {}
	
	public Entity(int hp, int x, int y) {
		this.hp = hp;
		this.x = x;
		this.y = y;
	}
	
	public Entity(int hp, int str, int speed,  int x, int y) {
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
