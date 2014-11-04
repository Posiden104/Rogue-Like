package com.joel.RogueLike.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor extends InputAdapter {
	
	public boolean keyDown(int k) {
		if(k == Keys.Z) {
			MyInput.setKey(MyInput.KEY_Z, true);
		}
		if(k == Keys.X) {
			MyInput.setKey(MyInput.KEY_X, true);
		}
		if(k == Keys.UP) {
			MyInput.setKey(MyInput.KEY_UP, true);
		}
		if(k == Keys.DOWN) {
			MyInput.setKey(MyInput.KEY_DOWN, true);
		}
		if(k == Keys.LEFT) {
			MyInput.setKey(MyInput.KEY_LEFT, true);
		}
		if(k == Keys.RIGHT) {
			MyInput.setKey(MyInput.KEY_RIGHT, true);
		}
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.Z) {
			MyInput.setKey(MyInput.KEY_Z, false);
		}
		if(k == Keys.X) {
			MyInput.setKey(MyInput.KEY_Z, false);
		}
		if(k == Keys.UP) {
			MyInput.setKey(MyInput.KEY_UP, false);
		}
		if(k == Keys.DOWN) {
			MyInput.setKey(MyInput.KEY_DOWN, false);
		}
		if(k == Keys.LEFT) {
			MyInput.setKey(MyInput.KEY_LEFT, false);
		}
		if(k == Keys.RIGHT) {
			MyInput.setKey(MyInput.KEY_RIGHT, false);
		}
		return true;
	}
	
}
