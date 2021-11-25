package com.street.bouncer.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {

    Texture backgroundTexture;
    private int initialBackgroundPositionX = 0;
    private int backgroundScrollingSpeed = 2;

    public Background() {
        backgroundTexture = new Texture("background.jpg");
    }

    public void drawBackground(SpriteBatch batch) {
        initialBackgroundPositionX += backgroundScrollingSpeed;
        if (initialBackgroundPositionX >= Gdx.graphics.getWidth()) {
            initialBackgroundPositionX = 0;
        }
        batch.draw(backgroundTexture, -initialBackgroundPositionX, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(backgroundTexture, -initialBackgroundPositionX + Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
