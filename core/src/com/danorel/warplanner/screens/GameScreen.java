package com.danorel.warplanner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.danorel.warplanner.Main;
import com.danorel.warplanner.agents.BaseAgent;
import com.danorel.warplanner.agents.PlayerAgent;
import com.danorel.warplanner.config.Preferences;
import com.danorel.warplanner.world.AgentGenerator;

import java.util.Iterator;

public class GameScreen implements Screen {
    final Main game;

    private AgentGenerator crystalsGenerator;
    private AgentGenerator playerGenerator;

    private OrthographicCamera camera;
    private Sound pick;
    private Music rain;
    private BaseAgent kitty;
    private Array<BaseAgent> crystals;
    private Vector3 cursor;
    private long time;

    private int score = 0;

    public GameScreen(final Main game) {
        this.game = game;

        time = TimeUtils.millis();

        crystalsGenerator = new AgentGenerator(50, 750);
        playerGenerator = new AgentGenerator(240, 360);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Preferences.VIEWPORT_WIDTH, Preferences.VIEWPORT_HEIGHT);

        cursor = new Vector3();

        kitty = playerGenerator.randomOne("curiousKitty_Idle00.png");
        crystals = crystalsGenerator.randomMany("pink_crystal_0000.png", (byte) 5);

        for (Iterator<BaseAgent> iter = crystals.iterator(); iter.hasNext(); ) {
            BaseAgent crystal = iter.next();
            if(crystal.getX() < 0) crystal.setX(0);
            if(crystal.getX() > 800 - crystal.getWidth()) crystal.setX(800 - crystal.getWidth());
            if(crystal.getY() < 0) crystal.setY(0);
            if(crystal.getY() > 480 - crystal.getHeight()) crystal.setY(480 - crystal.getHeight());
        }

        rain = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rain.play();

        pick = Gdx.audio.newSound(Gdx.files.internal("pick.mp3"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        if (crystals.size == 0) {
            game.setScreen(new OutroScreen(game));
            dispose();
        }

        game.batch.begin();
        game.font.draw(game.batch, "Kitty: " + kitty.getX() + ", " + kitty.getY(), 25, 455);
        game.font.draw(game.batch, "Score: " + score, 25, 430);
        game.font.draw(game.batch, "Time played: " + TimeUtils.timeSinceMillis(time) / 1000 + "s", 25, 405);
        game.batch.draw(kitty, kitty.getX(), kitty.getY());
        for (BaseAgent crystal: crystals) {
            game.batch.draw(crystal, crystal.getX(), crystal.getY());
        }
        game.batch.end();

        if (Gdx.input.isTouched()) {
            cursor.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(cursor);
            kitty.setX(cursor.x - kitty.getWidth() / 2);
            kitty.setY(cursor.y - kitty.getWidth() / 2);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            float absoluteDiff = 2 * Preferences.FRAMES_PER_SECOND * Gdx.graphics.getDeltaTime();
            float numericalDiff = Gdx.input.isKeyPressed(Input.Keys.LEFT) ? -absoluteDiff : absoluteDiff;
            kitty.setX(kitty.getX() + numericalDiff);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            float absoluteDiff = 2 * Preferences.FRAMES_PER_SECOND * Gdx.graphics.getDeltaTime();
            float numericalDiff = Gdx.input.isKeyPressed(Input.Keys.DOWN) ? -absoluteDiff : absoluteDiff;
            kitty.setY(kitty.getY() + numericalDiff);
        }

        if(kitty.getX() < 0) kitty.setX(0);
        if(kitty.getX() > 800 - kitty.getWidth()) kitty.setX(800 - kitty.getWidth());
        if(kitty.getY() < 0) kitty.setY(0);
        if(kitty.getY() > 480 - kitty.getHeight()) kitty.setY(480 - kitty.getHeight());

        for (byte i = 0; i < crystals.size; ++i) {
            BaseAgent crystal = crystals.get(i);
            if (kitty.overlaps(crystal)) {
                pick.play();
                crystals.removeIndex(i);
                ++score;
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
        rain.dispose();
        pick.dispose();
    }
}
