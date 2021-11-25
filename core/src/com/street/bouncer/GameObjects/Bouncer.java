package com.street.bouncer.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bouncer {

    private Texture[] bouncerTextureArrayRight;
    private Texture[] bouncerTextureArrayLeft;
    private Rectangle bouncerRectangle;
    private float movementBouncerSpeedX;

    private OrthographicCamera orthographicCamera;
    private Vector3 currentTouchPosition = new Vector3();
    private final float screenWidth = Gdx.graphics.getWidth();
    private final float screenHeight = Gdx.graphics.getHeight();

    private Texture rightButtonTexture;
    private Texture leftButtonTexture;
    private Sprite rightButtonSprite;
    private Sprite leftButtonSprite;

    private final float xPositionLeftButton = 120f;
    private final float xPositionRightButton = 330f;
    private final float yPositionButtons = 50;
    private float startTimeBetweenEachAnimation = 0f;
    private boolean isRightButtonPressed = false;
    private boolean isLeftButtonPressed = false;

    public Bouncer(float xPositionBouncer, float yPositionBouncer, float widthBouncer, float heightBouncer, float movementBouncerSpeedX) {
        orthographicCamera = new OrthographicCamera(screenWidth, screenHeight);
        orthographicCamera.setToOrtho(false);
        bouncerRectangle = new Rectangle(xPositionBouncer - widthBouncer / 2f, yPositionBouncer - heightBouncer / 2f, widthBouncer, heightBouncer);
        this.movementBouncerSpeedX = movementBouncerSpeedX;
        initBouncerTexturePositionArrays();
        initButtons();
    }

    public void initBouncerTexturePositionArrays() {
        bouncerTextureArrayRight = new Texture[4];
        bouncerTextureArrayRight[0] = new Texture("position1right.png");
        bouncerTextureArrayRight[1] = new Texture("position2right.png");
        bouncerTextureArrayRight[2] = new Texture("position3right.png");
        bouncerTextureArrayRight[3] = new Texture("position4right.png");

        bouncerTextureArrayLeft = new Texture[4];
        bouncerTextureArrayLeft[0] = new Texture("position1left.png");
        bouncerTextureArrayLeft[1] = new Texture("position2left.png");
        bouncerTextureArrayLeft[2] = new Texture("position3left.png");
        bouncerTextureArrayLeft[3] = new Texture("position4left.png");
    }

    private void initButtons() {
        rightButtonTexture = new Texture("rightbutton.png");
        leftButtonTexture = new Texture("leftbutton.png");

        rightButtonSprite = new Sprite(rightButtonTexture);
        leftButtonSprite = new Sprite(leftButtonTexture);

        rightButtonSprite.setPosition(xPositionRightButton, yPositionButtons);
        leftButtonSprite.setPosition(xPositionLeftButton, yPositionButtons);
    }

    public void drawButtons(SpriteBatch batch) {
        rightButtonSprite.draw(batch);
        leftButtonSprite.draw(batch);
    }

    public void drawAndMakeMoveBouncer(SpriteBatch batch) {
        if (Gdx.input.isTouched()) {
            currentTouchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            orthographicCamera.unproject(currentTouchPosition);
            float touchX = currentTouchPosition.x;
            float touchY = currentTouchPosition.y;

            if ((touchX >= leftButtonSprite.getX()) && touchX <= (leftButtonSprite.getX() + leftButtonSprite.getWidth()) && (touchY >= leftButtonSprite.getY())
                    && touchY <= (leftButtonSprite.getY() + leftButtonSprite.getHeight())) {
                isLeftButtonPressed = true;
                startTimeBetweenEachAnimation += Gdx.graphics.getDeltaTime();
                bouncerRectangle.x -= movementBouncerSpeedX * Gdx.graphics.getDeltaTime();
                if (startTimeBetweenEachAnimation > 0 && startTimeBetweenEachAnimation < 0.1) {
                        batch.draw(bouncerTextureArrayLeft[0], bouncerRectangle.x, bouncerRectangle.y, bouncerRectangle.width, bouncerRectangle.height);
                }
                if (startTimeBetweenEachAnimation > 0.1 && startTimeBetweenEachAnimation < 0.2) {
                    batch.draw(bouncerTextureArrayLeft[1], bouncerRectangle.x, bouncerRectangle.y, bouncerRectangle.width, bouncerRectangle.height);
                }
                if (startTimeBetweenEachAnimation > 0.2 && startTimeBetweenEachAnimation < 0.3) {
                    batch.draw(bouncerTextureArrayLeft[2], bouncerRectangle.x, bouncerRectangle.y, bouncerRectangle.width, bouncerRectangle.height);
                }
                if (startTimeBetweenEachAnimation > 0.3 && startTimeBetweenEachAnimation < 0.4) {
                    batch.draw(bouncerTextureArrayLeft[3], bouncerRectangle.x, bouncerRectangle.y, bouncerRectangle.width, bouncerRectangle.height);
                }
                if (startTimeBetweenEachAnimation > 0.4) {
                    startTimeBetweenEachAnimation = 0f;
                }
            }

            if ((touchX >= rightButtonSprite.getX()) && touchX <= (rightButtonSprite.getX() + rightButtonSprite.getWidth()) && (touchY >= rightButtonSprite.getY())
                    && touchY <= (rightButtonSprite.getY() + rightButtonSprite.getHeight())) {
                isRightButtonPressed = true;
                startTimeBetweenEachAnimation += Gdx.graphics.getDeltaTime();
                bouncerRectangle.x += movementBouncerSpeedX * Gdx.graphics.getDeltaTime();
                if (startTimeBetweenEachAnimation >= 0 && startTimeBetweenEachAnimation < 0.1) {
                    batch.draw(bouncerTextureArrayRight[0], bouncerRectangle.x, bouncerRectangle.y, bouncerRectangle.width, bouncerRectangle.height);
                }
                if (startTimeBetweenEachAnimation >= 0.1 && startTimeBetweenEachAnimation < 0.2) {
                    batch.draw(bouncerTextureArrayRight[1], bouncerRectangle.x, bouncerRectangle.y, bouncerRectangle.width, bouncerRectangle.height);
                }
                if (startTimeBetweenEachAnimation >= 0.2 && startTimeBetweenEachAnimation < 0.3) {
                    batch.draw(bouncerTextureArrayRight[2], bouncerRectangle.x, bouncerRectangle.y, bouncerRectangle.width, bouncerRectangle.height);
                }
                if (startTimeBetweenEachAnimation >= 0.3 && startTimeBetweenEachAnimation < 0.4) {
                    batch.draw(bouncerTextureArrayRight[3], bouncerRectangle.x, bouncerRectangle.y, bouncerRectangle.width, bouncerRectangle.height);
                }
                if (startTimeBetweenEachAnimation >= 0.4) {
                    startTimeBetweenEachAnimation = 0f;
                }
            }
        }
        if (!Gdx.input.isTouched()){
            batch.draw(bouncerTextureArrayRight[0], bouncerRectangle.x, bouncerRectangle.y, bouncerRectangle.width, bouncerRectangle.height);
            isRightButtonPressed = false;
            isLeftButtonPressed = false;
        }
    }


    public void checkBouncerBounds() {
        if (bouncerRectangle.x <= 0) {
            bouncerRectangle.x = 0;
        }
        if (bouncerRectangle.x + bouncerRectangle.width >= Gdx.graphics.getWidth()) {
            bouncerRectangle.x = Gdx.graphics.getWidth() - bouncerRectangle.width;
        }
    }

    public Rectangle getBouncerRectangle() {
        return bouncerRectangle;
    }

    public boolean getIsRightButtonPressed() {
        return isRightButtonPressed;
    }

    public boolean getIsLeftButtonPressed() {
        return isLeftButtonPressed;
    }

}
