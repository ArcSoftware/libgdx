package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Jake on 5/4/17.
 */
public class Level {
    private Music music;
    private Texture map;

    public Level(String textureFileName, String musicFileName) {
        map = new Texture(textureFileName);
        music = Gdx.audio.newMusic(Gdx.files.internal(musicFileName));
    }

    public Music getMusic() {
        return music;
    }

    public Texture getMap() {
        return map;
    }

    public void pauseMusic() {
        music.pause();
    }

    public void playMusic() {
        music.play();
    }

}
