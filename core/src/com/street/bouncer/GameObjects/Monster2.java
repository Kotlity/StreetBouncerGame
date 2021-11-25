package com.street.bouncer.GameObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Monster2{

    private Texture[] monster2RightTextureArray;
    private Texture[] monster2LeftTextureArray;
    private Rectangle monster2Rectangle;
    private float monster2Speed;

    private float startTimeBetweenEachMonster2RightAnimation = 0f;
    private float startTimeBetweenEachMonster2LeftAnimation = 0f;

    public Monster2(float xPositionMonster, float yPositionMonster, float monsterWidth, float monsterHeight, float monster2Speed) {
        initMonster2RightTextureArray();
        initMonster2LeftTextureArray();
        monster2Rectangle = new Rectangle(xPositionMonster - monsterWidth / 2f, yPositionMonster - monsterHeight / 2f, monsterWidth, monsterHeight);
        this.monster2Speed = monster2Speed;
    }

    private void initMonster2RightTextureArray() {
        monster2RightTextureArray = new Texture[3];
        monster2RightTextureArray[0] = new Texture("monster21right.png");
        monster2RightTextureArray[1] = new Texture("monster22right.png");
        monster2RightTextureArray[2] = new Texture("monster23right.png");
    }

    private void initMonster2LeftTextureArray() {
        monster2LeftTextureArray = new Texture[3];
        monster2LeftTextureArray[0] = new Texture("monster21left.png");
        monster2LeftTextureArray[1] = new Texture("monster22left.png");
        monster2LeftTextureArray[2] = new Texture("monster23left.png");
    }

    public void startMonster2RightAnimation(float delta) {
        startTimeBetweenEachMonster2RightAnimation += delta;
        if (startTimeBetweenEachMonster2RightAnimation >= 0.27f) {
            startTimeBetweenEachMonster2RightAnimation = 0f;
        }
    }

    public void startMonster2LeftAnimation(float delta) {
        startTimeBetweenEachMonster2LeftAnimation += delta;
        if (startTimeBetweenEachMonster2LeftAnimation > 0.27f) {
            startTimeBetweenEachMonster2LeftAnimation = 0f;
        }
    }

    public void updateMonster2Right(float delta) {
        monster2Rectangle.x += monster2Speed * delta;
    }

    public void updateMonster2Left(float delta) {
        monster2Rectangle.x -= monster2Speed * delta;
    }

    public void drawMonster2Right(SpriteBatch batch) {
        if (startTimeBetweenEachMonster2RightAnimation >= 0 && startTimeBetweenEachMonster2RightAnimation < 0.09f) {
            batch.draw(monster2RightTextureArray[0], monster2Rectangle.x, monster2Rectangle.y, monster2Rectangle.width, monster2Rectangle.height);
        }
        if (startTimeBetweenEachMonster2RightAnimation >= 0.09f && startTimeBetweenEachMonster2RightAnimation < 0.18f) {
            batch.draw(monster2RightTextureArray[1], monster2Rectangle.x, monster2Rectangle.y, monster2Rectangle.width, monster2Rectangle.height);
        }
        if (startTimeBetweenEachMonster2RightAnimation >= 0.18f && startTimeBetweenEachMonster2RightAnimation < 0.27f) {
            batch.draw(monster2RightTextureArray[2], monster2Rectangle.x, monster2Rectangle.y, monster2Rectangle.width, monster2Rectangle.height);
        }
    }

    public void drawMonster2Left(SpriteBatch batch) {
        if (startTimeBetweenEachMonster2LeftAnimation > 0 && startTimeBetweenEachMonster2LeftAnimation < 0.09f) {
            batch.draw(monster2LeftTextureArray[0], monster2Rectangle.x, monster2Rectangle.y, monster2Rectangle.width, monster2Rectangle.height);
        }
        if (startTimeBetweenEachMonster2LeftAnimation > 0.09f && startTimeBetweenEachMonster2LeftAnimation < 0.18f) {
            batch.draw(monster2LeftTextureArray[1], monster2Rectangle.x, monster2Rectangle.y, monster2Rectangle.width, monster2Rectangle.height);
        }
        if (startTimeBetweenEachMonster2LeftAnimation > 0.18f && startTimeBetweenEachMonster2LeftAnimation < 0.27f) {
            batch.draw(monster2LeftTextureArray[2], monster2Rectangle.x, monster2Rectangle.y, monster2Rectangle.width, monster2Rectangle.height);
        }
    }

    public boolean isMonster2Intersects(Rectangle rectangle) {
        return monster2Rectangle.overlaps(rectangle);
    }

    public Rectangle getMonster2Rectangle() {
        return monster2Rectangle;
    }
}
