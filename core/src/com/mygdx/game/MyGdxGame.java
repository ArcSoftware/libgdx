package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	TextureRegion up, up2, down, down2, left, left2, right, right2, boost, boost2;
	Animation walkForward, walkBackward, walkLeft, walkRight, boostTime;
	TextureRegion currentTexture;
    OrthographicCamera camera;
    Texture img;
	Sound sound;
	Music music;


	float x, y, xv, yv;
	float time;
	Integer b;


	static final float MAX_VELOCITY = 100;
	static final int WIDTH = 18;
	static final int HEIGHT = 26;
	static final int DRAW_WIDTH = WIDTH * 3;
	static final int DRAW_HEIGHT = HEIGHT * 3;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture tiles = new Texture("tiles.png");
		TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
		img = new Texture("LoZ.png");
		sound = Gdx.audio.newSound(Gdx.files.internal("godlike.wav"));
//		music = Gdx.audio.newMusic(Gdx.files.internal("lozmusic.m4a"));

        camera = new OrthographicCamera(1280 ,720);
        camera.update();


		down = grid[6][0];
		up = grid[6][1];
		right = grid[6][3];
		left = new TextureRegion(right);
		left.flip(true, false);

		up2 = new TextureRegion(up);
		up2.flip(true,false);
		right2 = grid[6][2];
		down2 = new TextureRegion(down);
		down2.flip(true, false);
		left2 = new TextureRegion(right2);
		left2.flip(true, false);
		boost = grid [7][5];
		boost2 = grid [7][4];

		walkForward = new Animation(0.2f, up, up2);
		walkBackward = new Animation(0.2f, down, down2);
		walkLeft = new Animation(0.2f, left, left2);
		walkRight = new Animation(0.2f, right, right2);
		boostTime = new Animation(0.15f, boost, boost2);

	}

	@Override
	public void render () {
		time += Gdx.graphics.getDeltaTime();
		move();

		if (x > 650) { x = -40; }
		if (x < -50) { x = 640; }
        if (y > 500) { y = -60; }
        if (y < -70) { y = 490; }

		Gdx.gl.glClearColor(250/255f, 240/255f, 230/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.draw(img, 20, 60, 600, 357);
		batch.draw(currentTexture, x, y, DRAW_WIDTH, DRAW_HEIGHT);
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
			currentTexture = walkForward.getKeyFrame(time, true);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = (MAX_VELOCITY * -1) * b;
			currentTexture = walkBackward.getKeyFrame(time, true);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY * b;
			currentTexture = walkRight.getKeyFrame(time, true);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = (MAX_VELOCITY * -1) * b;
			currentTexture = walkLeft.getKeyFrame(time, true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			b = 3;
			sound.play();
			currentTexture = boostTime.getKeyFrame(time, true);
		} else {
			b = 1;
//			music.play();
		}


		if (currentTexture == null) {
			currentTexture = right;
		}

		//v = d/t == v * t = d
		y += yv * Gdx.graphics.getDeltaTime();
		x += xv * Gdx.graphics.getDeltaTime();

		yv = decelerate(yv);
		xv = decelerate(xv);
	}
}
