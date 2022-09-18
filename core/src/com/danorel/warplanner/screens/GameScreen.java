package com.danorel.warplanner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import com.danorel.warplanner.Main;
import com.danorel.warplanner.agents.base.BaseAgent;
import com.danorel.warplanner.agents.crystal.CrystalAgent;
import com.danorel.warplanner.agents.crystal.CrystalFactory;
import com.danorel.warplanner.agents.player.PlayerAgent;
import com.danorel.warplanner.agents.player.PlayerFactory;
import com.danorel.warplanner.config.Preferences;
import com.danorel.warplanner.agents.AgentFactory;

public class GameScreen implements Screen {
    final Main game;

    private OrthographicCamera camera;
    private Sound pick;
    private Music rain;
    private PlayerAgent kitty;
    private Array<CrystalAgent> crystals;
    private long time;

    private int score = 0;

    public GameScreen(final Main game) {
        this.game = game;

        time = TimeUtils.millis();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Preferences.VIEWPORT_WIDTH, Preferences.VIEWPORT_HEIGHT);

        AgentFactory<PlayerAgent> playerGenerator = new AgentFactory<>(new PlayerFactory(), 240, 360);
        kitty = playerGenerator.randomOne("curiousKitty_Idle00.png");

        AgentFactory<CrystalAgent> crystalsGenerator = new AgentFactory<>(new CrystalFactory(), 50, 750);
        crystals = crystalsGenerator.randomMany("pink_crystal_0000.png", (byte) 5);

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

        kitty.watch();
        for (CrystalAgent crystal : crystals) {
            crystal.watch();
        }

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
