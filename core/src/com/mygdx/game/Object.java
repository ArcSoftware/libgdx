package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;

/**
 * Created by Jake on 5/3/17.
 */
public class Object {
    private TextureRegion wall;
    private Texture tiles = new Texture("tiles.png");
    private TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
    private Circle boundingCircle;

    public Object() {
        wall = grid[5][0];
        boundingCircle = new Circle();
        boundingCircle.set(MyGdxGame.x + 9, MyGdxGame.y + 6, 6.5f);
    }
    public TextureRegion getWall() {
        return wall;
    }
}
