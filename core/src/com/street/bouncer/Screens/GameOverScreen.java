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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.street.bouncer.GameManager.StreetBouncer;
import com.street.bouncer.GameObjects.Background;

public class GameOverScreen implements Screen {

    StreetBouncer streetBouncer;
    Background background;
    SpriteBatch batch;
    private float screenWidth = Gdx.graphics.getWidth();
    private float screenHeight = Gdx.graphics.getHeight();
    private OrthographicCamera orthographicCamera;
    private Vector3 currentTouchPosition = new Vector3();
    BitmapFont startAgainGameFont;
    BitmapFont pointGameFont;
    FreeTypeFontGenerator freeTypeFontGeneratorGameAgain = new FreeTypeFontGenerator(Gdx.files.internal("gamefont.otf"));
    FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameterStartGameAgain = new FreeTypeFontGenerator.FreeTypeFontParameter();
    FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameterPointGame = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private Texture gameTitleTexture;
    private Sprite gameTitleSprite;
    private Texture exitButtonTexture;
    private Sprite exitButtonSprite;


    public GameOverScreen(StreetBouncer streetBouncer) {
        this.streetBouncer = streetBouncer;
        background = new Background();
        batch = new SpriteBatch();
        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthographicCamera.setToOrtho(false);
        initGameTitle();
        initExitButton();

        makeFont();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0 ,0,1);
        batch.begin();
        background.drawBackground(batch);
        drawGameTitle(batch);
        drawExitButton(batch);
        drawStartAgainGameFont(batch);
        drawFinalScoreFont(batch);
        inputListener();
        batch.end();
    }

    private void initGameTitle() {
        gameTitleTexture = new Texture("gametitle.png");
        gameTitleSprite = new Sprite(gameTitleTexture);

        gameTitleSprite.setSize(gameTitleTexture.getWidth() * 1.7f, gameTitleTexture.getHeight() * 1.7f);
        gameTitleSprite.setPosition(screenWidth / 2f - gameTitleTexture.getWidth() / 2f - 305, screenHeight / 1.35f);
    }

    private void initExitButton() {
        exitButtonTexture = new Texture("exitbutton.png");
        exitButtonSprite = new Sprite(exitButtonTexture);

        exitButtonSprite.setPosition(screenWidth / 2f - exitButtonTexture.getWidth() / 2f, screenHeight / 2f - exitButtonTexture.getHeight() / 2f);
    }

    private void makeFont() {
        freeTypeFontParameterStartGameAgain.size = 43;
        freeTypeFontParameterStartGameAgain.color = Color.BLUE;
        freeTypeFontParameterStartGameAgain.borderColor = Color.WHITE;
        freeTypeFontParameterStartGameAgain.borderWidth = 2;

        startAgainGameFont = freeTypeFontGeneratorGameAgain.generateFont(freeTypeFontParameterStartGameAgain);

        freeTypeFontParameterPointGame.size = 60;
        freeTypeFontParameterPointGame.color = Color.YELLOW;
        freeTypeFontParameterPointGame.borderColor = Color.BLACK;
        freeTypeFontParameterPointGame.borderWidth = 3;

        pointGameFont = freeTypeFontGeneratorGameAgain.generateFont(freeTypeFontParameterPointGame);
    }

    private void drawGameTitle(SpriteBatch batch) {
        gameTitleSprite.draw(batch);
    }

    private void drawExitButton(SpriteBatch batch) {
        exitButtonSprite.draw(batch);
    }

    private void drawStartAgainGameFont(SpriteBatch batch) {
        startAgainGameFont.draw(batch, "Tap the screen to start the game again", screenWidth / 2f - 490, screenHeight / 6f);
    }

    private void drawFinalScoreFont(SpriteBatch batch) {
        pointGameFont.draw(batch, "Your score: " + streetBouncer.gamePoint, screenWidth / 2f - 300, screenHeight / 3.3f);
    }

    private void inputListener() {
        if (Gdx.input.justTouched()) {
            currentTouchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            orthographicCamera.unproject(currentTouchPosition);
            float touchX = currentTouchPosition.x;
            float touchY = currentTouchPosition.y;
            if ((touchX >= exitButtonSprite.getX()) && touchX <= (exitButtonSprite.getX() + exitButtonSprite.getWidth()) && (touchY >= exitButtonSprite.getY())
                    && touchY <= (exitButtonSprite.getY() + exitButtonSprite.getHeight())){
                Gdx.app.exit();
            }
            else {
                streetBouncer.setScreen(new GameScreen(streetBouncer));
                streetBouncer.bouncerHealth = 5;
                streetBouncer.gamePoint = 0;
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
        streetBouncer.dispose();
        pointGameFont.dispose();
        gameTitleTexture.dispose();
        exitButtonTexture.dispose();
    }
}
