package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;


	public static TextureRegion mainPlayer;
    OrthographicCamera camera;
    Texture img;
	Sound sound;
	Music music;

	float x, y, xv, yv;
	float time;
	Integer b;

	static final float MAX_VELOCITY = 100;
	static final int WIDTH = 15;
	static final int HEIGHT = 17;
	static final int DRAW_WIDTH = WIDTH * 3;
	static final int DRAW_HEIGHT = HEIGHT * 3;

	@Override
	public void create () {
		batch = new SpriteBatch();

		img = new Texture("LoZ.png");
		sound = Gdx.audio.newSound(Gdx.files.internal("godlike.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("ironforge.mp3"));

		Player.mainPlayer();
	}

	@Override
	public void render () {
		time += Gdx.graphics.getDeltaTime();
		move();
		bounderies();
		background();

		batch.begin();
		batch.draw(img, 20, 60, 600, 357);
		batch.draw(mainPlayer, x, y, DRAW_WIDTH, DRAW_HEIGHT);
		batch.end();
	}
	
	@Override
	public void dispose () {
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

	void move() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			yv = MAX_VELOCITY * b;
			mainPlayer = Player.walkForward.getKeyFrame(time, true);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = (MAX_VELOCITY * -1) * b;
			mainPlayer = Player.walkBackward.getKeyFrame(time, true);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY * b;
			mainPlayer = Player.walkRight.getKeyFrame(time, true);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = (MAX_VELOCITY * -1) * b;
			mainPlayer = Player.walkLeft.getKeyFrame(time, true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			b = 3;
			music.pause();
			sound.loop();

			mainPlayer = Player.boostTime.getKeyFrame(time, true);
		} else {
			b = 1;
			music.play();
			sound.stop();
		}


		if (mainPlayer == null) {
			mainPlayer = Player.right;
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
