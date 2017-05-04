package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;

import java.util.ArrayList;

/**
 * Created by Jake on 5/3/17.
 */
public class Object {
    private TextureRegion wall;
    private Texture tiles = new Texture("tiles.png");
    private TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
    private Circle boundingCircle;
    private Texture hogger, flag;
//    private Texture flag;
//    ArrayList objects = new ArrayList();
//    objects.


    public Object() {
        hogger = new Texture("hogger.png");
        flag = new Texture("ironforgeFlag.png");
        wall = grid[5][0];
        boundingCircle = new Circle();
//        boundingCircle.set(MyGdxGame.x + 9, MyGdxGame.y + 6, 1.5f);
    }

    public Texture getHogger() {return hogger;}
    public Texture getFlag() {return flag;}
    public TextureRegion getWall() {
        return wall;
    }
    public Circle getBoundingCircle() { return boundingCircle;}
}
