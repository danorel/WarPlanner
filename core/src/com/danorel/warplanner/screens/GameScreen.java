package com.danorel.warplanner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import com.danorel.warplanner.Main;

public class GameScreen implements Screen {
    final Main game;

    private static final int FRAMES_PER_SECOND = 60;

    private OrthographicCamera camera;
    private Music rain;
    private Sprite player1;
    private Sprite player2;
    private Vector3 cursor;

    public GameScreen(final Main game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        cursor = new Vector3();

        Texture player1Texture = new Texture(Gdx.files.internal("battle-machine-player-1.jpg"));
        player1 = new Sprite(player1Texture, 0, 0, 50, 50);

        Texture player2Texture = new Texture(Gdx.files.internal("battle-machine-player-2.jpg"));
        player2 = new Sprite(player2Texture,  0, 0, 50, 50);
        player2.setX(60);

        rain = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rain.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        game.batch.begin();
        game.font.draw(game.batch, "Player 1: " + player1.getX() + ", " + player1.getY(), 25, 455);
        game.font.draw(game.batch, "Player 2: " + player2.getX() + ", " + player2.getY(), 25, 430);
        game.batch.draw(player1, player1.getX(), player1.getY());
        game.batch.draw(player2, player2.getX(), player2.getY());
        game.batch.end();

        if (Gdx.input.isTouched()) {
            cursor.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(cursor);
            player1.setX(cursor.x - player1.getWidth() / 2);
            player1.setY(cursor.y - player1.getWidth() / 2);
            player2.setX(800 - cursor.x - player2.getWidth() / 2);
            player2.setY(480 - cursor.y - player2.getWidth() / 2);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            float absoluteDiff = FRAMES_PER_SECOND * Gdx.graphics.getDeltaTime();
            float numericalDiff = Gdx.input.isKeyPressed(Input.Keys.LEFT) ? -absoluteDiff : absoluteDiff;
            player1.setX(player1.getX() + numericalDiff);
            player2.setX(player2.getX() - numericalDiff);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            float absoluteDiff = FRAMES_PER_SECOND * Gdx.graphics.getDeltaTime();
            float numericalDiff = Gdx.input.isKeyPressed(Input.Keys.DOWN) ? -absoluteDiff : absoluteDiff;
            player1.setY(player1.getY() + numericalDiff);
            player2.setY(player2.getY() - numericalDiff);
        }

        if(player1.getX() < 0) player1.setX(0);
        if(player1.getX() > 800 - player1.getWidth()) player1.setX(800 - player1.getWidth());
        if(player1.getY() < 0) player1.setY(0);
        if(player1.getY() > 480 - player1.getHeight()) player1.setY(480 - player1.getHeight());

        if(player2.getX() < 0) player2.setX(0);
        if(player2.getX() > 800 - player2.getWidth()) player2.setX(800 - player2.getWidth());
        if(player2.getY() < 0) player2.setY(0);
        if(player2.getY() > 480 - player2.getHeight()) player2.setY(480 - player2.getHeight());
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
        rain.dispose();
    }
}
