package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;

public class MyGdxGame extends ApplicationAdapter {
	public static float time;
	public static float x, y, xv, yv;
	SpriteBatch batch;
	Texture map, flag;
	Sound sound;
	Music music;
	Integer b;
	Player p;
	Object a;

	final float MAX_VELOCITY = 100;
	final int WIDTH = 15;
	final int HEIGHT = 17;
	final int DRAW_WIDTH = WIDTH * 3;
	final int DRAW_HEIGHT = HEIGHT * 3;

	@Override
	public void create() {
		batch = new SpriteBatch();
		map = new Texture("ironforge.png");
		flag = new Texture("ironforgeFlag.png");

		sound = Gdx.audio.newSound(Gdx.files.internal("godlike.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("ironforge.mp3"));
		p = new Player();
		a = new Object();
	}

	@Override
	public void render() {
		time += Gdx.graphics.getDeltaTime();
		moveMainPlayer();
		bounderies();
		background();

		batch.begin();
		batch.draw(map, -120, -100, 1340, 870);
		batch.draw(flag, 10, 300, 150, 160);
		batch.draw(p.getCurrentTexture(), x, y, DRAW_WIDTH, DRAW_HEIGHT);
//		batch.draw(a.getWall(), 100, 100, 30, 30);
		batch.end();

	}

	@Override
	public void dispose() {
		batch.dispose();
		sound.dispose();

	}

	float decelerate(float velocity) {
		float deceleration = 0.75f;
		velocity *= deceleration;
		if (Math.abs(velocity) < 1) {
			velocity = 0;
		}
		return velocity;
	}
	void moveMainPlayer() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			yv = MAX_VELOCITY * b;
			p.update("forward");
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = (MAX_VELOCITY * -1) * b;
			p.update("backward");
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY * b;
			p.update("right");
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = (MAX_VELOCITY * -1) * b;
			p.update("left");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			b = 3;
			music.pause();
			sound.loop();
			p.update("boost");
		} else {
			b = 1;
			music.play();
			sound.stop();
		}

		if (p.getCurrentTexture() == null) {
			p.update("default");
		}

		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			dispose();
		}

		//v = d/t == v * t = d
		y += yv * Gdx.graphics.getDeltaTime();
		x += xv * Gdx.graphics.getDeltaTime();

		yv = decelerate(yv);
		xv = decelerate(xv);
	}
	void bounderies() {
		if (x > 650) { x = -40; }
		if (x < -50) { x = 640; }
		if (y > 500) { y = -60; }
		if (y < -70) { y = 490; }

	}
	void background() {
		Gdx.gl.glClearColor(250/255f, 240/255f, 230/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
