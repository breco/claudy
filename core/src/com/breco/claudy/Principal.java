package com.breco.claudy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.MainMenu;

public class Principal extends Game {

	public SpriteBatch batch;
    public static int WIDTH, HEIGHT;
    public static Preferences prefs;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		Preferences prefs = Gdx.app.getPreferences("Preferences");
		prefs.putInteger("stage",1);
		prefs.putInteger("lifes",5);
		prefs.putBoolean("new",false);
		prefs.flush();
		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
