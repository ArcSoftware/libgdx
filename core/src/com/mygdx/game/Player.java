package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jake on 5/3/17.
 */
public class Player {
    private TextureRegion up, up2, down, down2, left, left2, right, right2, boost, boost2, currentTexture;
    private Animation walkForward, walkBackward, walkLeft, walkRight, boostTime;
    private Texture tiles = new Texture("tiles.png");
    private TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
    private Circle boundingCircle;

    public Player() {
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
        walkLeft = new Animation(0.15f, left, left2);
        walkRight = new Animation(0.15f, right, right2);
        boostTime = new Animation(0.15f, boost, boost2);
        boundingCircle = new Circle();
        boundingCircle.set(MyGdxGame.x + 9, MyGdxGame.y + 6, 6.5f);
    }

    public TextureRegion getCurrentTexture() {
        return currentTexture;
    }

    public void update(String o) {
        if (o.equals("forward")) {
            currentTexture = walkForward.getKeyFrame(MyGdxGame.time, true);
        }
        if (o.equals("backward")) {
            currentTexture  = walkBackward.getKeyFrame(MyGdxGame.time, true);
        }
        if (o.equals("right")) {
            currentTexture = walkRight.getKeyFrame(MyGdxGame.time, true);
        }
        if (o.equals("left")) {
            currentTexture  = walkLeft.getKeyFrame(MyGdxGame.time, true);
        }
        if (o.equals("default")) {
            currentTexture  = up;
        }
        if (o.equals("boost")) {
            currentTexture  = boostTime.getKeyFrame(MyGdxGame.time, true);
        }
    }
}
