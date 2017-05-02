package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.xml.soap.Text;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	TextureRegion up, up2, down, down2, left, left2, right, right2;
	Animation walkForward, walkBackward, walkLeft, walkRight;
	TextureRegion currentTexture;
    OrthographicCamera camera;


	float x, y, xv, yv;
	float time;

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

        camera = new OrthographicCamera(1280 ,720);
        camera.update();

//        player = new Player();
//        player.setPos((int)cam.position.x, (int)cam.position.y);

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

		walkForward = new Animation(0.2f, up, up2);
		walkBackward = new Animation(0.2f, down, down2);
		walkLeft = new Animation(0.2f, left, left2);
		walkRight = new Animation(0.2f, right, right2);

		if(y > 100) {
            camera.translate(100,0);
        }
	}

	@Override
	public void render () {
		time += Gdx.graphics.getDeltaTime();
		move();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

//		if (currentTexture, x > 100) {
//		    camera.translate(200, 200);
//        }

		batch.draw(currentTexture, x, y, DRAW_WIDTH, DRAW_HEIGHT);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	float decelerate(float velocity) {
		float deceleration = 0.75f; // the closer to 1, the slower the deceleration
		velocity *= deceleration;
		if (Math.abs(velocity) < 1) {
			velocity = 0;
		}
		return velocity;
	}

	void move() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			yv = MAX_VELOCITY;
			currentTexture = walkForward.getKeyFrame(time, true);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_VELOCITY * -1;
			currentTexture = walkBackward.getKeyFrame(time, true);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY;
			currentTexture = walkRight.getKeyFrame(time, true);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_VELOCITY * -1;
			currentTexture = walkLeft.getKeyFrame(time, true);
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
