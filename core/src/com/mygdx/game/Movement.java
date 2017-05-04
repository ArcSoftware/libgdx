package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

/**
 * Created by Jake on 5/4/17.
 */
public class Movement {
    private static final float MAX_VELOCITY = 100;

    public static void moveMainPlayer(Player player) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.yv = MAX_VELOCITY;
            player.update("forward");
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.yv = (MAX_VELOCITY * -1);
            player.update("backward");
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.xv = MAX_VELOCITY;
            player.update("right");
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.xv = (MAX_VELOCITY * -1);
            player.update("left");
        }


        int b = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            b = 3;
            MyGdxGame.currentLevel.pauseMusic();
            player.getBoost().loop();
            player.update("boost");
        } else {
            b = 1;
            MyGdxGame.currentLevel.playMusic();
            player.getBoost().stop();
        }


        if (player.getCurrentTexture() == null) {
            player.update("default");
        }
//
//        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
////            MyGdxGame.dispose();
//        }
//		if (Intersector.overlaps(p.getBoundingCircle(), a.getBoundingCircle())) {

//		}
//
        //v = d/t == v * t = d
        player.y += b * player.yv * Gdx.graphics.getDeltaTime();
        player.x += b * player.xv * Gdx.graphics.getDeltaTime();

        player.yv = decelerate(player.yv);
        player.xv = decelerate(player.xv);

        if (player.x > 650) {player.x = -40; }
        if (player.x < -50) {player.x = 640; }
        if (player.y > 500) {player.y = -60; }
        if (player.y < -70) {player.y = 490; }
    }
    public static float decelerate(float velocity) {
        float deceleration = 0.75f;
        velocity *= deceleration;
        if (Math.abs(velocity) < 1) {
            velocity = 0;
        }
        return velocity;
    }

}


