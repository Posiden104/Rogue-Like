package com.joel.RogueLike.handlers;


public class MyInput {
	
	public static int x;
	public static int y;

	public static boolean[] keys;
	public static boolean[] pkeys;
	
	public static final int NUM_KEYS = 6;
	public static final int KEY_Z = 0;
	public static final int KEY_X= 1;
	public static final int KEY_UP = 2;
	public static final int KEY_DOWN = 3;
	public static final int KEY_LEFT = 4;
	public static final int KEY_RIGHT = 5;
	
	static {
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			pkeys[i] = keys[i];
		}
	}
	
	public static void setKey(int i, boolean b) { keys[i] = b; }
	public static boolean isDown(int i) { return keys[i]; }
	public static boolean isPressed(int i) { return keys[i] && !pkeys[i]; }
	
}
















