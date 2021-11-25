package com.street.bouncer.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Monster1{

    private Texture[] monster1RightTextureArray;
    private Texture[] monster1LeftTextureArray;
    private Rectangle monster1Rectangle;
    private float monster1Speed;

    private float startTimeBetweenEachMonster1RightAnimation = 0f;
    private float startTimeBetweenEachMonster1LeftAnimation = 0f;

    private int currentHealthMonster1Right = 3;
    private int currentHealthMonster1Left = 3;

    public Monster1(float xPositionMonster, float yPositionMonster, float monsterWidth, float monsterHeight, float monster1Speed) {
        initMonster1RightTextureArray();
        initMonster1LeftTextureArray();
        monster1Rectangle = new Rectangle(xPositionMonster - monsterWidth / 2f, yPositionMonster - monsterHeight / 2f, monsterWidth, monsterHeight);
        this.monster1Speed = monster1Speed;
    }

    private void initMonster1RightTextureArray() {
        monster1RightTextureArray = new Texture[4];
        monster1RightTextureArray[0] = new Texture("monster11right.png");
        monster1RightTextureArray[1] = new Texture("monster12right.png");
        monster1RightTextureArray[2] = new Texture("monster13right.png");
        monster1RightTextureArray[3] = new Texture("monster14right.png");
    }

    private void initMonster1LeftTextureArray() {
        monster1LeftTextureArray = new Texture[4];
        monster1LeftTextureArray[0] = new Texture("monster11left.png");
        monster1LeftTextureArray[1] = new Texture("monster12left.png");
        monster1LeftTextureArray[2] = new Texture("monster13left.png");
        monster1LeftTextureArray[3] = new Texture("monster14left.png");
    }

    public void startMonster1RightAnimation(float delta) {
        startTimeBetweenEachMonster1RightAnimation += delta;
        if (startTimeBetweenEachMonster1RightAnimation >= 0.6f) {
            startTimeBetweenEachMonster1RightAnimation = 0f;
        }
    }

    public void startMonster1LeftAnimation(float delta) {
        startTimeBetweenEachMonster1LeftAnimation += delta;
        if (startTimeBetweenEachMonster1LeftAnimation >= 0.6f) {
            startTimeBetweenEachMonster1LeftAnimation = 0f;
        }
    }

    public void updateMonster1Left(float delta) {
        monster1Rectangle.x -= monster1Speed * delta;
    }

    public void drawMonster1Right(SpriteBatch batch) {
        if (startTimeBetweenEachMonster1RightAnimation >= 0 && startTimeBetweenEachMonster1RightAnimation < 0.15f) {
            batch.draw(monster1RightTextureArray[0], monster1Rectangle.x, monster1Rectangle.y, monster1Rectangle.width, monster1Rectangle.height);
        }
        if (startTimeBetweenEachMonster1RightAnimation >= 0.15f && startTimeBetweenEachMonster1RightAnimation < 0.3f) {
            batch.draw(monster1RightTextureArray[1], monster1Rectangle.x, monster1Rectangle.y, monster1Rectangle.width, monster1Rectangle.height);
        }
        if (startTimeBetweenEachMonster1RightAnimation >= 0.3f && startTimeBetweenEachMonster1RightAnimation < 0.45f) {
            batch.draw(monster1RightTextureArray[2], monster1Rectangle.x, monster1Rectangle.y, monster1Rectangle.width, monster1Rectangle.height);
        }
        if (startTimeBetweenEachMonster1RightAnimation >= 0.45f && startTimeBetweenEachMonster1RightAnimation < 0.6f) {
            batch.draw(monster1RightTextureArray[3], monster1Rectangle.x, monster1Rectangle.y, monster1Rectangle.width, monster1Rectangle.height);
        }
    }

    public void drawMonster1Left(SpriteBatch batch) {
        if (startTimeBetweenEachMonster1LeftAnimation >= 0 && startTimeBetweenEachMonster1LeftAnimation < 0.15f) {
            batch.draw(monster1LeftTextureArray[0], monster1Rectangle.x, monster1Rectangle.y, monster1Rectangle.width, monster1Rectangle.height);
        }
        if (startTimeBetweenEachMonster1LeftAnimation >= 0.15f && startTimeBetweenEachMonster1LeftAnimation < 0.3f) {
            batch.draw(monster1LeftTextureArray[1], monster1Rectangle.x, monster1Rectangle.y, monster1Rectangle.width, monster1Rectangle.height);
        }
        if (startTimeBetweenEachMonster1LeftAnimation >= 0.3f && startTimeBetweenEachMonster1LeftAnimation < 0.45f) {
            batch.draw(monster1LeftTextureArray[2], monster1Rectangle.x, monster1Rectangle.y, monster1Rectangle.width, monster1Rectangle.height);
        }
        if (startTimeBetweenEachMonster1LeftAnimation >= 0.45f && startTimeBetweenEachMonster1LeftAnimation < 0.6f) {
            batch.draw(monster1LeftTextureArray[3], monster1Rectangle.x, monster1Rectangle.y, monster1Rectangle.width, monster1Rectangle.height);
        }
    }

    public boolean isMonster1Intersects(Rectangle rectangle) {
        return monster1Rectangle.overlaps(rectangle);
    }

    public void minusHealthMonster1Right() {
        currentHealthMonster1Right--;
    }

    public void minusHealthMonster1Left() {
        currentHealthMonster1Left--;
    }

    public boolean monster1RightListIteratorRemove() {
        return currentHealthMonster1Right == 0;
    }

    public boolean monster1LeftListIteratorRemove() {
        return currentHealthMonster1Left == 0;
    }

    public void checkSpeedMonster1Right(float delta) {
        switch (currentHealthMonster1Right) {
            case 3:
                monster1Rectangle.x += monster1Speed * delta;
                break;
            case 2:
                monster1Rectangle.x += (monster1Speed - 30) * delta;
                break;
            case 1:
                monster1Rectangle.x += (monster1Speed - 60) * delta;
                break;
        }
    }

    public Rectangle getMonster1Rectangle() {
        return monster1Rectangle;
    }

}
