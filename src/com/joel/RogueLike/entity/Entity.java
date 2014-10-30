package com.joel.RogueLike.entity;

public class Entity {

	private int hp; // Health Points determine life force
	private int str; // Strength determines damage + carrying capacity
	private int speed; // Speed determines movement speed

	public Entity() {}
	
	public Entity(int hp) {
		this.hp = hp;
	}
	
	public Entity(int hp, int str, int speed) {
		this.hp = hp;
		this.str = str;
		this.speed = speed;
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
	
}
