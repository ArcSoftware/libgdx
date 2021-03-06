package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;
import java.util.HashMap;

public class MyGdxGame extends ApplicationAdapter {
	public static float time;
	Movement playerMovement;
	SpriteBatch batch;
	Player p;
	Object hogger, flag;
	public static Level currentLevel;
	private HashMap<String, Level> levels;
	final int WIDTH = 15;
	final int HEIGHT = 17;
	final int DRAW_WIDTH = WIDTH * 3;
	final int DRAW_HEIGHT = HEIGHT * 3;
	TmxMapLoader mapLoader;
    float unitScale = 1 / 32f;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
	@Override
	public void create() {
		levels = new HashMap<>();
		levels.put("ironforge", new Level("ironForge.tmx", "ironforge.mp3"));
		currentLevel = levels.get("ironforge");
		batch = new SpriteBatch();
		p = new Player();
		hogger = new Object();
		flag = new Object();
		playerMovement = new Movement();

		mapLoader = new TmxMapLoader();
        renderer = new OrthogonalTiledMapRenderer(currentLevel.getMap(), unitScale);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,27, 21);
        renderer.setView(camera);


    }

	@Override
	public void render() {
		time += Gdx.graphics.getDeltaTime();
		background();
		renderer.render();

		batch.begin();
		playerMovement.moveMainPlayer(p);
		batch.draw(p.getCurrentTexture(), p.x, p.y, DRAW_WIDTH, DRAW_HEIGHT);



		batch.draw(flag.getFlag(), 10, 300, 150, 160);

		batch.draw(hogger.getHogger(), 200, 120, 150, 100);
//		batch.draw(w.getWall(), 100, 100, 40, 40);
//        currentLevel.getMap();
//        currentLevel.getLayer();

		batch.end();

	}

	@Override
	public void dispose() {
		batch.dispose();
		renderer.dispose();

//		sound.dispose();

	}


	void background() {
		Gdx.gl.glClearColor(250/255f, 240/255f, 230/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
