package com.joel.RogueLike.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.joel.RogueLike.main.Game;
import com.joel.RogueLike.states.Play;

public class HUD {
	
//	private Player player;
	
	private TextureRegion[] font;
	
	public HUD(Player player) {
		
//		this.player = player;
		
		Texture tex = Game.res.getTexture("hud");
		
		
		font = new TextureRegion[11];
		for(int i = 0; i < 6; i++) {
			font[i] = new TextureRegion(tex, 32 + i * 9, 16, 9, 9);
		}
		for(int i = 0; i < 5; i++) {
			font[i + 6] = new TextureRegion(tex, 32 + i * 9, 25, 9, 9);
		}
		
	}
	
	public void render(SpriteBatch sb) {
		
		sb.begin();
		
		// draw crystal amount
//		drawString(sb, player.getNumCrystals() + " / " + player.getTotalCrystals(), 132, 211);
		drawString(sb, "100 / 100", 132 - Play.getPlayerOffset().x, 211 - Play.getPlayerOffset().y);
		
		sb.end();
		
	}
	
//	@SuppressWarnings("unused")
	private void drawString(SpriteBatch sb, String s, float x, float y) {
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == '/') c = 10;
			else if(c >= '0' && c <= '9') c -= '0';
			else continue;
			sb.draw(font[c], x + i * 9, y);
		}
	}
	
}
