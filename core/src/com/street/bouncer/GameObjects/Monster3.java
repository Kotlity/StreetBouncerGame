package com.street.bouncer.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Monster3{


    private Texture[] monster3RightTextureArray;
    private Texture[] monster3LeftTextureArray;
    private Rectangle monster3Rectangle;
    private float monster3Speed;

    private float startTimeBetweenEachMonster3RightAnimation = 0f;
    private float startTimeBetweenEachMonster3LeftAnimation = 0f;

    private int currentHealthMonster3Right = 2;
    private int currentHealthMonster3Left = 2;

    public Monster3(float xPositionMonster, float yPositionMonster, float monsterWidth, float monsterHeight, float monster3Speed) {
        initMonster3RightTextureArray();
        initMonster3LeftTextureArray();
        monster3Rectangle = new Rectangle(xPositionMonster - monsterWidth / 2f, yPositionMonster - monsterHeight / 2f, monsterWidth, monsterHeight);
        this.monster3Speed = monster3Speed;
    }

    private void initMonster3RightTextureArray() {
        monster3RightTextureArray = new Texture[4];
        monster3RightTextureArray[0] = new Texture("monster31right.png");
        monster3RightTextureArray[1] = new Texture("monster32right.png");
        monster3RightTextureArray[2] = new Texture("monster33right.png");
        monster3RightTextureArray[3] = new Texture("monster34right.png");
    }

    private void initMonster3LeftTextureArray() {
        monster3LeftTextureArray = new Texture[4];
        monster3LeftTextureArray[0] = new Texture("monster31left.png");
        monster3LeftTextureArray[1] = new Texture("monster32left.png");
        monster3LeftTextureArray[2] = new Texture("monster33left.png");
        monster3LeftTextureArray[3] = new Texture("monster34left.png");
    }

    public void startMonster3RightAnimation(float delta) {
        startTimeBetweenEachMonster3RightAnimation += delta;
        if (startTimeBetweenEachMonster3RightAnimation >= 0.36f) {
            startTimeBetweenEachMonster3RightAnimation = 0f;
        }
    }

    public void startMonster3LeftAnimation(float delta) {
        startTimeBetweenEachMonster3LeftAnimation += delta;
        if (startTimeBetweenEachMonster3LeftAnimation >= 0.36f) {
            startTimeBetweenEachMonster3LeftAnimation = 0f;
        }
    }

    public void drawMonster3Right(SpriteBatch batch) {
        if (startTimeBetweenEachMonster3RightAnimation >= 0 && startTimeBetweenEachMonster3RightAnimation < 0.12f) {
            batch.draw(monster3RightTextureArray[0], monster3Rectangle.x, monster3Rectangle.y, monster3Rectangle.width, monster3Rectangle.height);
        }
        if (startTimeBetweenEachMonster3RightAnimation >= 0.12f && startTimeBetweenEachMonster3RightAnimation < 0.24f) {
            batch.draw(monster3RightTextureArray[1], monster3Rectangle.x, monster3Rectangle.y, monster3Rectangle.width, monster3Rectangle.height);
        }
        if (startTimeBetweenEachMonster3RightAnimation >= 0.24f && startTimeBetweenEachMonster3RightAnimation < 0.36f) {
            batch.draw(monster3RightTextureArray[2], monster3Rectangle.x, monster3Rectangle.y, monster3Rectangle.width, monster3Rectangle.height);
        }
    }

    public void drawMonster3Left(SpriteBatch batch) {
        if (startTimeBetweenEachMonster3LeftAnimation >= 0 && startTimeBetweenEachMonster3LeftAnimation < 0.12f) {
            batch.draw(monster3LeftTextureArray[0], monster3Rectangle.x, monster3Rectangle.y, monster3Rectangle.width, monster3Rectangle.height);
        }
        if (startTimeBetweenEachMonster3LeftAnimation >= 0.12f && startTimeBetweenEachMonster3LeftAnimation < 0.24f) {
            batch.draw(monster3LeftTextureArray[1], monster3Rectangle.x, monster3Rectangle.y, monster3Rectangle.width, monster3Rectangle.height);
        }
        if (startTimeBetweenEachMonster3LeftAnimation >= 0.24f && startTimeBetweenEachMonster3LeftAnimation < 0.36f) {
            batch.draw(monster3LeftTextureArray[2], monster3Rectangle.x, monster3Rectangle.y, monster3Rectangle.width, monster3Rectangle.height);
        }
    }

    public boolean isMonster3Intersects(Rectangle rectangle) {
        return monster3Rectangle.overlaps(rectangle);
    }

    public void minusHealthMonster3Right() {
        currentHealthMonster3Right--;
    }

    public void minusHealthMonster3Left() {
        currentHealthMonster3Left--;
    }

    public boolean monster3RightListIteratorRemove() {
        return currentHealthMonster3Right == 0;
    }

    public boolean monster3LeftListIteratorRemove() {
        return currentHealthMonster3Left == 0;
    }

    public void checkSpeedMonster3Right(float delta) {
        switch (currentHealthMonster3Right) {
            case 2:
                monster3Rectangle.x += monster3Speed * delta;
                break;
            case 1:
                monster3Rectangle.x += (monster3Speed - 100) * delta;
                break;
        }
    }

    public void checkSpeedMonster3Left(float delta) {
        switch (currentHealthMonster3Left) {
            case 2:
                monster3Rectangle.x -= monster3Speed * delta;
                break;
            case 1:
                monster3Rectangle.x -= (monster3Speed - 100) * delta;
                break;
        }
    }

    public Rectangle getMonster3Rectangle() {
        return monster3Rectangle;
    }
}
