package com.street.bouncer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.street.bouncer.GameObjects.Background;
import com.street.bouncer.GameObjects.Bouncer;
import com.street.bouncer.GameManager.StreetBouncer;
import com.street.bouncer.GameObjects.BouncerBullet;
import com.street.bouncer.GameObjects.Monster1;
import com.street.bouncer.GameObjects.Monster2;
import com.street.bouncer.GameObjects.Monster3;
import com.street.bouncer.GameObjects.MonsterExplosions;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Random;

public class GameScreen implements Screen {

    StreetBouncer streetBouncer;
    private OrthographicCamera orthographicCamera;
    private Background background;
    private Bouncer bouncer;
    private SpriteBatch batch;
    private final float screenWidth = Gdx.graphics.getWidth();
    private final float screenHeight = Gdx.graphics.getHeight();
    private float initialBouncerPositionY = 247f;
    private final float bouncerWidth = 136f;
    private final float bouncerHeight = 170f;

    BitmapFont pointGameFont;
    FreeTypeFontGenerator freeTypeFontGeneratorGame = new FreeTypeFontGenerator(Gdx.files.internal("gamefont.otf"));
    FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameterPointGame = new FreeTypeFontGenerator.FreeTypeFontParameter();


    private LinkedList<BouncerBullet> bouncerBulletRightLinkedList;
    private LinkedList<BouncerBullet> bouncerBulletLeftLinkedList;
    private Texture bouncerBulletTextureRight;
    private Texture bouncerBulletTextureLeft;
    private final float bouncerBulletWidth = 45f;
    private final float bouncerBulletHeight = 17f;
    private final float bouncerBulletSpeed = 1200f;
    private float startTimeBouncerBulletShot = 0f;
    private final float timerBetweenEachBouncerBulletShot = 0.5f;

    private Random random;

    private final float monster1Width = 125f;
    private final float monster1Height = 200f;

    private final float monster2Width = 80f;
    private final float monster2Height = 49f;

    private final float monster3Width = 100f;
    private final float monster3Height = 150f;

    private LinkedList<Monster1> monster1RightLinkedList;
    private LinkedList<Monster1> monster1LeftLinkedList;
    private LinkedList<Monster2> monster2RightLinkedList;
    private LinkedList<Monster2> monster2LeftLinkedList;
    private LinkedList<Monster3> monster3RightLinkedList;
    private LinkedList<Monster3> monster3LeftLinkedList;

    private final float minTimeBetweenEachMonster1RightSpawns = 5.5f;
    private final float maxTimeBetweenEachMonster1RightSpawns = 7f;
    private final float minTimeBetweenEachMonster1LeftSpawns = 5.5f;
    private final float maxTimeBetweenEachMonster1LeftSpawns = 7f;

    private final float minTimeBetweenEachMonster2RightSpawns = 2.75f;
    private final float maxTimeBetweenEachMonster2RightSpawns = 4.5f;
    private final float minTimeBetweenEachMonster2LeftSpawns = 2.75f;
    private final float maxTimeBetweenEachMonster2LeftSpawns = 4.5f;

    private final float minTimeBetweenEachMonster3RightSpawns = 4.5f;
    private final float maxTimeBetweenEachMonster3RightSpawns = 6f;
    private final float minTimeBetweenEachMonster3LeftSpawns = 4.5f;
    private final float maxTimeBetweenEachMonster3LeftSpawns = 6f;

    private float timeBetweenEachMonster1RightSpawns;
    private float timeBetweenEachMonster1LeftSpawns;
    private float timeBetweenEachMonster2RightSpawns;
    private float timeBetweenEachMonster2LeftSpawns;
    private float timeBetweenEachMonster3RightSpawns;
    private float timeBetweenEachMonster3LeftSpawns;

    private LinkedList<MonsterExplosions> monsterExplosionsLinkedList;

    private Texture bouncerHealth5Texture;
    private Texture bouncerHealth4Texture;
    private Texture bouncerHealth3Texture;
    private Texture bouncerHealth2Texture;
    private Texture bouncerHealth1Texture;

    private Sprite bouncerHealth5Sprite;
    private Sprite bouncerHealth4Sprite;
    private Sprite bouncerHealth3Sprite;
    private Sprite bouncerHealth2Sprite;
    private Sprite bouncerHealth1Sprite;

    public GameScreen(StreetBouncer streetBouncer) {
        this.streetBouncer = streetBouncer;
        orthographicCamera = new OrthographicCamera(screenWidth, screenHeight);
        orthographicCamera.setToOrtho(false);
        batch = new SpriteBatch();
        background = new Background();
        bouncer = new Bouncer(screenWidth / 2f - bouncerWidth / 2f, initialBouncerPositionY, bouncerWidth, bouncerHeight, 650f);
        bouncerBulletTextureRight = new Texture("bouncerbulletright.png");
        bouncerBulletTextureLeft = new Texture("bouncerbulletleft.png");
        bouncerBulletRightLinkedList = new LinkedList<>();
        bouncerBulletLeftLinkedList = new LinkedList<>();

        random = new Random();

        makeFont();

        initMonsterLinkedLists();

        initTimeBetweenEachMonsterSpawns();

        monsterExplosionsLinkedList = new LinkedList<>();

        initBouncerHealth();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0 ,0,1);
        batch.begin();
        background.drawBackground(batch);
        bouncer.drawButtons(batch);
        bouncer.drawAndMakeMoveBouncer(batch);

        startTimerSpawnMonster(delta);

        startTimerBouncerBulletToShot(delta);

        addMonster1Right();
        addMonster1Left();
        addMonster2Right();
        addMonster2Left();
        addMonster3Right();
        addMonster3Left();

        drawMonster1Right(delta);
        drawMonster1Left(delta);
        drawMonster2Right(delta);
        drawMonster2Left(delta);
        drawMonster3Right(delta);
        drawMonster3Left(delta);

        checkCollisionMonster1Right();
        checkCollisionMonster1Left();
        checkCollisionMonster2Right();
        checkCollisionMonster2Left();
        checkCollisionMonster3Right();
        checkCollisionMonster3Left();

        drawAndUpdateMonsterExplosions(delta);

        drawBouncerHealth(batch);
        drawPointGameFont(batch);

        addBouncerBullet();

        drawAndCheckCollisionBouncerBulletRightShot(batch);
        drawAndCheckCollisionBouncerBulletLeftShot(batch);

        bouncer.checkBouncerBounds();

        gameOver();

        batch.end();
    }

    private void initBouncerHealth() {
        bouncerHealth5Texture = new Texture("bouncerhealth5.png");
        bouncerHealth4Texture = new Texture("bouncerhealth4.png");
        bouncerHealth3Texture = new Texture("bouncerhealth3.png");
        bouncerHealth2Texture = new Texture("bouncerhealth2.png");
        bouncerHealth1Texture = new Texture("bouncerhealth1.png");

        bouncerHealth5Sprite = new Sprite(bouncerHealth5Texture);
        bouncerHealth4Sprite = new Sprite(bouncerHealth4Texture);
        bouncerHealth3Sprite = new Sprite(bouncerHealth3Texture);
        bouncerHealth2Sprite = new Sprite(bouncerHealth2Texture);
        bouncerHealth1Sprite = new Sprite(bouncerHealth1Texture);

        bouncerHealth5Sprite.setPosition(screenWidth - 800f, screenHeight / 1.2f);
        bouncerHealth4Sprite.setPosition(screenWidth - 800f, screenHeight / 1.2f);
        bouncerHealth3Sprite.setPosition(screenWidth - 800f, screenHeight / 1.2f);
        bouncerHealth2Sprite.setPosition(screenWidth - 800f, screenHeight / 1.2f);
        bouncerHealth1Sprite.setPosition(screenWidth - 800f, screenHeight / 1.2f);
    }

    private void makeFont() {
        freeTypeFontParameterPointGame.size = 60;
        freeTypeFontParameterPointGame.color = Color.YELLOW;
        freeTypeFontParameterPointGame.borderColor = Color.BLACK;
        freeTypeFontParameterPointGame.borderWidth = 3;

        pointGameFont = freeTypeFontGeneratorGame.generateFont(freeTypeFontParameterPointGame);

    }

    private void drawPointGameFont(SpriteBatch batch) {
        pointGameFont.draw(batch, String.format(Locale.getDefault(), "%04d", streetBouncer.gamePoint), 100, screenHeight / 1.15f);
    }

    private void drawBouncerHealth(SpriteBatch batch) {
        switch (streetBouncer.bouncerHealth) {
            case 5:
                bouncerHealth5Sprite.draw(batch);
                break;
            case 4:
                bouncerHealth4Sprite.draw(batch);
                break;
            case 3:
                bouncerHealth3Sprite.draw(batch);
                break;
            case 2:
                bouncerHealth2Sprite.draw(batch);
                break;
            case 1:
                bouncerHealth1Sprite.draw(batch);
                break;
        }
    }

    private void gameOver() {
        if (streetBouncer.bouncerHealth < streetBouncer.bouncerMinHealth) {
            streetBouncer.setScreen(new GameOverScreen(streetBouncer));
        }
    }

    private void initMonsterLinkedLists() {
        monster1RightLinkedList = new LinkedList<>();
        monster1LeftLinkedList = new LinkedList<>();

        monster2RightLinkedList = new LinkedList<>();
        monster2LeftLinkedList = new LinkedList<>();

        monster3RightLinkedList = new LinkedList<>();
        monster3LeftLinkedList = new LinkedList<>();
    }

    private void initTimeBetweenEachMonsterSpawns() {
        timeBetweenEachMonster1RightSpawns = random.nextFloat() * (maxTimeBetweenEachMonster1RightSpawns - minTimeBetweenEachMonster1RightSpawns) + minTimeBetweenEachMonster1RightSpawns;
        timeBetweenEachMonster1LeftSpawns = random.nextFloat() * (maxTimeBetweenEachMonster1LeftSpawns - minTimeBetweenEachMonster1LeftSpawns) + minTimeBetweenEachMonster1LeftSpawns;

        timeBetweenEachMonster2RightSpawns = random.nextFloat() * (maxTimeBetweenEachMonster2RightSpawns - minTimeBetweenEachMonster2RightSpawns) + minTimeBetweenEachMonster2RightSpawns;
        timeBetweenEachMonster2LeftSpawns = random.nextFloat() * (maxTimeBetweenEachMonster2LeftSpawns - minTimeBetweenEachMonster2LeftSpawns) + minTimeBetweenEachMonster2LeftSpawns;

        timeBetweenEachMonster3RightSpawns = random.nextFloat() * (maxTimeBetweenEachMonster3RightSpawns - minTimeBetweenEachMonster3RightSpawns) + minTimeBetweenEachMonster3RightSpawns;
        timeBetweenEachMonster3LeftSpawns = random.nextFloat() * (maxTimeBetweenEachMonster3LeftSpawns - minTimeBetweenEachMonster3LeftSpawns) + minTimeBetweenEachMonster3LeftSpawns;
    }

    private void startTimerSpawnMonster(float delta) {
        timeBetweenEachMonster1RightSpawns -= delta;
        timeBetweenEachMonster1LeftSpawns -= delta;
        timeBetweenEachMonster2RightSpawns -= delta;
        timeBetweenEachMonster2LeftSpawns -= delta;
        timeBetweenEachMonster3RightSpawns -= delta;
        timeBetweenEachMonster3LeftSpawns -= delta;
    }

    private void startTimerBouncerBulletToShot(float delta) {
        startTimeBouncerBulletShot += delta;
    }

    private void addBouncerBullet() {
        if (startTimeBouncerBulletShot - timerBetweenEachBouncerBulletShot >= 0 && bouncer.getIsRightButtonPressed()) {
            bouncerBulletRightLinkedList.add(new BouncerBullet(bouncerBulletTextureRight, bouncer.getBouncerRectangle().x + bouncer.getBouncerRectangle().width * 0.98f, bouncer.getBouncerRectangle().y + bouncer.getBouncerRectangle().height * 0.77f, bouncerBulletWidth, bouncerBulletHeight, bouncerBulletSpeed));
            startTimeBouncerBulletShot = 0f;
        }
        if (startTimeBouncerBulletShot - timerBetweenEachBouncerBulletShot >= 0 && bouncer.getIsLeftButtonPressed()) {
            bouncerBulletLeftLinkedList.add(new BouncerBullet(bouncerBulletTextureLeft, bouncer.getBouncerRectangle().x, bouncer.getBouncerRectangle().y + bouncer.getBouncerRectangle().height * 0.77f, bouncerBulletWidth, bouncerBulletHeight, bouncerBulletSpeed));
            startTimeBouncerBulletShot = 0f;
        }
    }

    private void drawAndCheckCollisionBouncerBulletRightShot(SpriteBatch batch) {
        ListIterator<BouncerBullet> bouncerBulletListIterator = bouncerBulletRightLinkedList.listIterator();
        while (bouncerBulletListIterator.hasNext()) {
            BouncerBullet bouncerBullet = bouncerBulletListIterator.next();
                bouncerBullet.updateBouncerBulletRight();
                bouncerBullet.drawBouncerBullet(batch);
                bouncerBullet.checkBouncerBulletBoundsRight();
                if (bouncerBullet.isRemoveBouncerBulletRight()) {
                    bouncerBulletListIterator.remove();
                }
        }
    }

    private void drawAndCheckCollisionBouncerBulletLeftShot(SpriteBatch batch) {
        ListIterator<BouncerBullet> bouncerBulletListIterator = bouncerBulletLeftLinkedList.listIterator();
        while (bouncerBulletListIterator.hasNext()) {
            BouncerBullet bouncerBullet = bouncerBulletListIterator.next();
            bouncerBullet.updateBouncerBulletLeft();
            bouncerBullet.drawBouncerBullet(batch);
            bouncerBullet.checkBouncerBulletBoundsLeft();
            if (bouncerBullet.isRemoveBouncerBulletLeft()) {
                bouncerBulletListIterator.remove();
            }
        }
    }


    private void addMonster1Right() {
        if (timeBetweenEachMonster1RightSpawns <= 0) {
            timeBetweenEachMonster1RightSpawns = random.nextFloat() * (maxTimeBetweenEachMonster1RightSpawns - minTimeBetweenEachMonster1RightSpawns) + minTimeBetweenEachMonster1RightSpawns;
            monster1RightLinkedList.add(new Monster1(MathUtils.random(-200, -50), 259, monster1Width, monster1Height, 150f));
        }
    }

    private void addMonster1Left() {
        if (timeBetweenEachMonster1LeftSpawns <= 0) {
            timeBetweenEachMonster1LeftSpawns = random.nextFloat() * (maxTimeBetweenEachMonster1LeftSpawns - minTimeBetweenEachMonster1LeftSpawns) + minTimeBetweenEachMonster1LeftSpawns;
            monster1LeftLinkedList.add(new Monster1(MathUtils.random(screenWidth + 50, screenWidth + 200), 259, monster1Width, monster1Height, 150f));
        }
    }

    private void addMonster2Right() {
        if (timeBetweenEachMonster2RightSpawns <= 0) {
            timeBetweenEachMonster2RightSpawns = random.nextFloat() * (maxTimeBetweenEachMonster2RightSpawns - minTimeBetweenEachMonster2RightSpawns) + minTimeBetweenEachMonster2RightSpawns;
            monster2RightLinkedList.add(new Monster2(MathUtils.random(-200, -50), 302, monster2Width, monster2Height, 350f));
        }
    }

    private void addMonster2Left() {
        if (timeBetweenEachMonster2LeftSpawns <= 0) {
            timeBetweenEachMonster2LeftSpawns = random.nextFloat() * (maxTimeBetweenEachMonster2LeftSpawns - minTimeBetweenEachMonster2LeftSpawns) + minTimeBetweenEachMonster2LeftSpawns;
            monster2LeftLinkedList.add(new Monster2(MathUtils.random(screenWidth + 50, screenWidth + 200), 302, monster2Width, monster2Height, 350f));
        }
    }

    private void addMonster3Right() {
        if (timeBetweenEachMonster3RightSpawns <= 0) {
            timeBetweenEachMonster3RightSpawns = random.nextFloat() * (maxTimeBetweenEachMonster3RightSpawns - minTimeBetweenEachMonster3RightSpawns) + minTimeBetweenEachMonster3RightSpawns;
            monster3RightLinkedList.add(new Monster3(MathUtils.random(-200, -50), 240, monster3Width, monster3Height, 250f));
        }
    }

    private void addMonster3Left() {
        if (timeBetweenEachMonster3LeftSpawns <= 0) {
            timeBetweenEachMonster3LeftSpawns = random.nextFloat() * (maxTimeBetweenEachMonster3LeftSpawns - minTimeBetweenEachMonster3LeftSpawns) + minTimeBetweenEachMonster3LeftSpawns;
            monster3LeftLinkedList.add(new Monster3(MathUtils.random(screenWidth + 50, screenWidth + 200), 240, monster3Width, monster3Height, 250f));
        }
    }

    private void drawMonster1Right(float delta) {
        ListIterator<Monster1> monster1RightListIterator = monster1RightLinkedList.listIterator();
        while (monster1RightListIterator.hasNext()) {
            Monster1 monster1Right = monster1RightListIterator.next();
            monster1Right.startMonster1RightAnimation(delta);
            monster1Right.checkSpeedMonster1Right(delta);
            monster1Right.drawMonster1Right(batch);
        }
    }

    private void drawMonster1Left(float delta) {
        ListIterator<Monster1> monster1LeftListIterator = monster1LeftLinkedList.listIterator();
        while (monster1LeftListIterator.hasNext()) {
            Monster1 monster1Left = monster1LeftListIterator.next();
            monster1Left.startMonster1LeftAnimation(delta);
            monster1Left.updateMonster1Left(delta);
            monster1Left.drawMonster1Left(batch);
        }
    }

    private void drawMonster2Right(float delta) {
        ListIterator<Monster2> monster2RightListIterator = monster2RightLinkedList.listIterator();
        while (monster2RightListIterator.hasNext()) {
            Monster2 monster2Right = monster2RightListIterator.next();
            monster2Right.startMonster2RightAnimation(delta);
            monster2Right.updateMonster2Right(delta);
            monster2Right.drawMonster2Right(batch);
        }
    }

    private void drawMonster2Left(float delta) {
        ListIterator<Monster2> monster2LeftListIterator = monster2LeftLinkedList.listIterator();
        while (monster2LeftListIterator.hasNext()) {
            Monster2 monster2Left = monster2LeftListIterator.next();
            monster2Left.startMonster2LeftAnimation(delta);
            monster2Left.updateMonster2Left(delta);
            monster2Left.drawMonster2Left(batch);
        }
    }

    private void drawMonster3Right(float delta) {
        ListIterator<Monster3> monster3RightListIterator = monster3RightLinkedList.listIterator();
        while (monster3RightListIterator.hasNext()) {
            Monster3 monster3Right = monster3RightListIterator.next();
            monster3Right.startMonster3RightAnimation(delta);
            monster3Right.checkSpeedMonster3Right(delta);
            monster3Right.drawMonster3Right(batch);
        }
    }

    private void drawMonster3Left(float delta) {
        ListIterator<Monster3> monster3LeftListIterator = monster3LeftLinkedList.listIterator();
        while (monster3LeftListIterator.hasNext()) {
            Monster3 monster3Left = monster3LeftListIterator.next();
            monster3Left.startMonster3LeftAnimation(delta);
            monster3Left.checkSpeedMonster3Left(delta);
            monster3Left.drawMonster3Left(batch);
        }
    }

    private void drawAndUpdateMonsterExplosions(float delta){
        ListIterator<MonsterExplosions> monsterExplosionsListIterator = monsterExplosionsLinkedList.listIterator();
        while (monsterExplosionsListIterator.hasNext()) {
            MonsterExplosions monsterExplosions = monsterExplosionsListIterator.next();
            monsterExplosions.updateMonsterExplosionsAnimation(delta);
            if (monsterExplosions.isMonsterExplosionsAnimationFinished()) {
                monsterExplosionsListIterator.remove();
            }
            else {
                monsterExplosions.drawMonsterExplosionsAnimation(batch);
            }
        }
    }

    private void checkCollisionMonster1Right() {
        ListIterator<Monster1> monster1RightListIterator = monster1RightLinkedList.listIterator();
        while (monster1RightListIterator.hasNext()) {
            Monster1 monster1Right = monster1RightListIterator.next();
            ListIterator<BouncerBullet> bouncerBulletLeftListIterator = bouncerBulletLeftLinkedList.listIterator();
            while (bouncerBulletLeftListIterator.hasNext()) {
                BouncerBullet bouncerBulletLeft = bouncerBulletLeftListIterator.next();
                if (monster1Right.isMonster1Intersects(bouncerBulletLeft.getBulletRectangle())) {
                    monster1Right.minusHealthMonster1Right();
                    bouncerBulletLeftListIterator.remove();
                }
            }
            if (monster1Right.monster1RightListIteratorRemove()) {
                streetBouncer.gamePoint += 15;
                monster1RightListIterator.remove();
                monsterExplosionsLinkedList.add(new MonsterExplosions(monster1Right.getMonster1Rectangle(), 0.9f));
            }
            if (monster1Right.isMonster1Intersects(bouncer.getBouncerRectangle())) {
                monsterExplosionsLinkedList.add(new MonsterExplosions(monster1Right.getMonster1Rectangle(), 0.9f));
                monster1RightListIterator.remove();
                streetBouncer.bouncerHealth--;
            }
        }
    }

    private void checkCollisionMonster1Left() {
        ListIterator<Monster1> monster1LeftListIterator = monster1LeftLinkedList.listIterator();
        while (monster1LeftListIterator.hasNext()) {
            Monster1 monster1Left = monster1LeftListIterator.next();
            ListIterator<BouncerBullet> bouncerBulletRightListIterator = bouncerBulletRightLinkedList.listIterator();
            while (bouncerBulletRightListIterator.hasNext()) {
                BouncerBullet bouncerBulletRight = bouncerBulletRightListIterator.next();
                if (monster1Left.isMonster1Intersects(bouncerBulletRight.getBulletRectangle())) {
                    monster1Left.minusHealthMonster1Left();
                    bouncerBulletRightListIterator.remove();
                }
            }
                if (monster1Left.monster1LeftListIteratorRemove()) {
                    streetBouncer.gamePoint += 15;
                    monster1LeftListIterator.remove();
                    monsterExplosionsLinkedList.add(new MonsterExplosions(monster1Left.getMonster1Rectangle(), 0.9f));
                }
                if (monster1Left.isMonster1Intersects(bouncer.getBouncerRectangle())) {
                    monsterExplosionsLinkedList.add(new MonsterExplosions(monster1Left.getMonster1Rectangle(), 0.9f));
                    monster1LeftListIterator.remove();
                    streetBouncer.bouncerHealth--;
            }
        }
    }

    private void checkCollisionMonster2Right() {
        ListIterator<Monster2> monster2RightListIterator = monster2RightLinkedList.listIterator();
        while (monster2RightListIterator.hasNext()) {
            Monster2 monster2Right = monster2RightListIterator.next();
            ListIterator<BouncerBullet> bouncerBulletLeftListIterator = bouncerBulletLeftLinkedList.listIterator();
            while (bouncerBulletLeftListIterator.hasNext()) {
                BouncerBullet bouncerBulletLeft = bouncerBulletLeftListIterator.next();
                if (monster2Right.isMonster2Intersects(bouncerBulletLeft.getBulletRectangle())) {
                    streetBouncer.gamePoint += 5;
                    monsterExplosionsLinkedList.add(new MonsterExplosions(monster2Right.getMonster2Rectangle(), 0.6f));
                    monster2RightListIterator.remove();
                    bouncerBulletLeftListIterator.remove();
                }
            }
            if (monster2Right.isMonster2Intersects(bouncer.getBouncerRectangle())) {
                monsterExplosionsLinkedList.add(new MonsterExplosions(monster2Right.getMonster2Rectangle(), 0.6f));
                monster2RightListIterator.remove();
                streetBouncer.bouncerHealth--;
            }
        }
    }

    private void checkCollisionMonster2Left() {
        ListIterator<Monster2> monster2LeftListIterator = monster2LeftLinkedList.listIterator();
        while (monster2LeftListIterator.hasNext()) {
            Monster2 monster2Left = monster2LeftListIterator.next();
            ListIterator<BouncerBullet> bouncerBulletRightListIterator = bouncerBulletRightLinkedList.listIterator();
            while (bouncerBulletRightListIterator.hasNext()) {
                BouncerBullet bouncerBulletRight = bouncerBulletRightListIterator.next();
                if (monster2Left.isMonster2Intersects(bouncerBulletRight.getBulletRectangle())) {
                    streetBouncer.gamePoint += 5;
                    monsterExplosionsLinkedList.add(new MonsterExplosions(monster2Left.getMonster2Rectangle(), 0.6f));
                    monster2LeftListIterator.remove();
                    bouncerBulletRightListIterator.remove();
                }
            }
            if (monster2Left.isMonster2Intersects(bouncer.getBouncerRectangle())) {
                monsterExplosionsLinkedList.add(new MonsterExplosions(monster2Left.getMonster2Rectangle(), 0.6f));
                monster2LeftListIterator.remove();
                streetBouncer.bouncerHealth--;
            }
        }
    }

    private void checkCollisionMonster3Right() {
        ListIterator<Monster3> monster3RightListIterator = monster3RightLinkedList.listIterator();
        while (monster3RightListIterator.hasNext()) {
            Monster3 monster3Right = monster3RightListIterator.next();
            ListIterator<BouncerBullet> bouncerBulletLeftListIterator = bouncerBulletLeftLinkedList.listIterator();
            while (bouncerBulletLeftListIterator.hasNext()) {
                BouncerBullet bouncerBulletLeft = bouncerBulletLeftListIterator.next();
                if (monster3Right.isMonster3Intersects(bouncerBulletLeft.getBulletRectangle())) {
                    monster3Right.minusHealthMonster3Right();
                    bouncerBulletLeftListIterator.remove();
                }
            }
            if (monster3Right.monster3RightListIteratorRemove()) {
                streetBouncer.gamePoint += 10;
                monster3RightListIterator.remove();
                monsterExplosionsLinkedList.add(new MonsterExplosions(monster3Right.getMonster3Rectangle(), 0.75f));
            }
            if (monster3Right.isMonster3Intersects(bouncer.getBouncerRectangle())) {
                monsterExplosionsLinkedList.add(new MonsterExplosions(monster3Right.getMonster3Rectangle(), 0.75f));
                monster3RightListIterator.remove();
                streetBouncer.bouncerHealth--;
            }
        }
    }

    private void checkCollisionMonster3Left() {
        ListIterator<Monster3> monster3LeftListIterator = monster3LeftLinkedList.listIterator();
        while (monster3LeftListIterator.hasNext()) {
            Monster3 monster3Left = monster3LeftListIterator.next();
            ListIterator<BouncerBullet> bouncerBulletRightListIterator = bouncerBulletRightLinkedList.listIterator();
            while (bouncerBulletRightListIterator.hasNext()) {
                BouncerBullet bouncerBulletRight = bouncerBulletRightListIterator.next();
                if (monster3Left.isMonster3Intersects(bouncerBulletRight.getBulletRectangle())) {
                    monster3Left.minusHealthMonster3Left();
                    bouncerBulletRightListIterator.remove();
                }
            }
            if (monster3Left.monster3LeftListIteratorRemove()) {
                streetBouncer.gamePoint += 10;
                monster3LeftListIterator.remove();
                monsterExplosionsLinkedList.add(new MonsterExplosions(monster3Left.getMonster3Rectangle(), 0.75f));
            }
            if (monster3Left.isMonster3Intersects(bouncer.getBouncerRectangle())) {
                monsterExplosionsLinkedList.add(new MonsterExplosions(monster3Left.getMonster3Rectangle(), 0.75f));
                monster3LeftListIterator.remove();
                streetBouncer.bouncerHealth--;
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        pointGameFont.dispose();
        bouncerBulletTextureRight.dispose();
        bouncerBulletTextureLeft.dispose();
        bouncerHealth5Texture.dispose();
        bouncerHealth4Texture.dispose();
        bouncerHealth3Texture.dispose();
        bouncerHealth2Texture.dispose();
        bouncerHealth1Texture.dispose();
        streetBouncer.dispose();
    }

}
