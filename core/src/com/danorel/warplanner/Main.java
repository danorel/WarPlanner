package com.danorel.warplanner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.danorel.warplanner.screens.MainMenuScreen;

public class Main extends Game {

	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create () {
		font = new BitmapFont();
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
