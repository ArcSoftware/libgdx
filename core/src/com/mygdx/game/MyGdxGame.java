package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;

public class MyGdxGame extends ApplicationAdapter {
	public static float time;
//	public static float x, y, xv, yv;
	Movement playerMovement;
	SpriteBatch batch;
//	Sound sound;
	Player p;
	Object hogger, flag;
	public static Level currentLevel;
	private HashMap<String, Level> levels;
	final int WIDTH = 15;
	final int HEIGHT = 17;
	final int DRAW_WIDTH = WIDTH * 3;
	final int DRAW_HEIGHT = HEIGHT * 3;

	@Override
	public void create() {
		levels = new HashMap<>();
		levels.put("ironforge", new Level("ironforge.png", "ironforge.mp3"));
		currentLevel = levels.get("ironforge");
		batch = new SpriteBatch();
		p = new Player();
		hogger = new Object();
		flag = new Object();
		playerMovement = new Movement();
	}

	@Override
	public void render() {
		time += Gdx.graphics.getDeltaTime();
		playerMovement.moveMainPlayer(p);
		background();

		batch.begin();
		batch.draw(currentLevel.getMap(), -120, -100, 1340, 870);
		batch.draw(flag.getFlag(), 10, 300, 150, 160);
		batch.draw(p.getCurrentTexture(), p.x, p.y, DRAW_WIDTH, DRAW_HEIGHT);
		batch.draw(hogger.getHogger(), 200, 120, 150, 100);
//		batch.draw(w.getWall(), 100, 100, 40, 40);
		batch.end();

	}

	@Override
	public void dispose() {
		batch.dispose();
//		sound.dispose();

	}


	void background() {
		Gdx.gl.glClearColor(250/255f, 240/255f, 230/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
