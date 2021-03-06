package com.joel.RogueLike.handlers;

import java.util.Stack;

import com.joel.RogueLike.main.Game;
import com.joel.RogueLike.states.GameState;
import com.joel.RogueLike.states.Menu;
import com.joel.RogueLike.states.Play;

public class GameStateManager {
	
	private Game game;
	
	private Stack<GameState> gameStates;
	
	public static final int MENU = 83774392;
	public static final int PLAY = 388031654;
	
	public GameStateManager(Game game) {
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(PLAY);
	}
	
	public void update(float dt) {
		gameStates.peek().update(dt);
	}
	
	public void render() {
		gameStates.peek().render();
	}
	
	public Game game() { return game; }
	
	private GameState getState(int state) {
		if(state == MENU) return new Menu(this);
		if(state == PLAY) return new Play(this);
		return null;
	}
	
	public void setState(int state) {
		popState();
		pushState(state);
	}
	
	public void pushState(int state) {
		gameStates.push(getState(state));
	}
	
	public void popState() {
		GameState g = gameStates.pop();
		g.dispose();
	}
	
}
