package com.street.bouncer.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class MonsterExplosions {

    private Animation<TextureRegion> monsterExplosionsAnimation;
    private Texture monsterExplosionsTexture;
    private float monsterExplosionsAnimationTimer;
    private Rectangle monsterExplosionsRectangle;

    public MonsterExplosions(Rectangle monsterExplosionsRectangle, float allTimeMonsterExplosionsAnimation) {
        monsterExplosionsTexture = new Texture("monsterexplosions.png");
        this.monsterExplosionsRectangle = monsterExplosionsRectangle;

        TextureRegion[][] monsterExplosionsTextureRegion2D = TextureRegion.split(monsterExplosionsTexture, 64, 64);

        TextureRegion[] monsterExplosionsTextureRegion1D = new TextureRegion[16];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                monsterExplosionsTextureRegion1D[index] = monsterExplosionsTextureRegion2D[i][j];
                index++;
            }
        }

        monsterExplosionsAnimation = new Animation<>(allTimeMonsterExplosionsAnimation / 16, monsterExplosionsTextureRegion1D);
        monsterExplosionsAnimationTimer = 0;
    }

    public void updateMonsterExplosionsAnimation(float delta) {
        monsterExplosionsAnimationTimer += delta;
    }

    public void drawMonsterExplosionsAnimation(SpriteBatch batch) {
        batch.draw(monsterExplosionsAnimation.getKeyFrame(monsterExplosionsAnimationTimer), monsterExplosionsRectangle.x, monsterExplosionsRectangle.y, monsterExplosionsRectangle.width, monsterExplosionsRectangle.height);
    }

    public boolean isMonsterExplosionsAnimationFinished() {
        return monsterExplosionsAnimation.isAnimationFinished(monsterExplosionsAnimationTimer);
    }
}
