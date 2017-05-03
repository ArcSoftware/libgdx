package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Jake on 5/3/17.
 */
public class Player {
    public static TextureRegion up, up2, down, down2, left, left2, right, right2, boost, boost2;
    public static  Animation walkForward, walkBackward, walkLeft, walkRight, boostTime;
    public static Texture tiles = new Texture("tiles.png");
    public static TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);


    public static void mainPlayer() {
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



    }
}