package com.street.bouncer.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BouncerBullet {

    private Texture bulletTexture;
    private Rectangle bulletRectangle;
    private float bulletSpeed;
    private boolean removeBouncerBulletRight = false;
    private boolean removeBouncerBulletLeft = false;

    public BouncerBullet(Texture bulletTexture, float xPositionBullet, float yPositionBullet, float widthBullet, float heightBullet, float bulletSpeed) {
        this.bulletTexture = bulletTexture;
        this.bulletRectangle = new Rectangle(xPositionBullet - widthBullet / 2f, yPositionBullet, widthBullet, heightBullet);
        this.bulletSpeed = bulletSpeed;
    }

    public void updateBouncerBulletRight() {
        bulletRectangle.x += bulletSpeed * Gdx.graphics.getDeltaTime();
    }

    public void updateBouncerBulletLeft() {
        bulletRectangle.x -= bulletSpeed * Gdx.graphics.getDeltaTime();
    }

    public void drawBouncerBullet(SpriteBatch batch) {
        batch.draw(bulletTexture, bulletRectangle.x - bulletRectangle.width / 2f, bulletRectangle.y, bulletRectangle.width, bulletRectangle.height);
    }

    public void checkBouncerBulletBoundsRight() {
        if (bulletRectangle.x > Gdx.graphics.getWidth()) {
            removeBouncerBulletRight = true;
        }
    }

    public void checkBouncerBulletBoundsLeft() {
        if (bulletRectangle.x + bulletRectangle.width < 0) {
            removeBouncerBulletLeft = true;
        }
    }

    public boolean isRemoveBouncerBulletRight() {
        return removeBouncerBulletRight;
    }

    public boolean isRemoveBouncerBulletLeft() {
        return removeBouncerBulletLeft;
    }

    public Rectangle getBulletRectangle() {
        return bulletRectangle;
    }
}
