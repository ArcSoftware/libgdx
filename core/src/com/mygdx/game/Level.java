package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import javafx.stage.Screen;

/**
 * Created by Jake on 5/4/17.
 */
public class Level {
    private Music music;
    private TiledMap map;
    private MapLayer layer;

    public Level(String textureFileName, String musicFileName) {
        map = new TmxMapLoader().load(textureFileName);
        music = Gdx.audio.newMusic(Gdx.files.internal(musicFileName));
        layer = map.getLayers().get(0);
    }

    public TiledMap getMap() {
        return map;
    }

    public void pauseMusic() {
        music.pause();
    }

    public void playMusic() {
        music.play();
    }

    public MapLayer getLayer() {return layer;}

    //    @Override
//    public void render(float delta) {
//
//    }
}
