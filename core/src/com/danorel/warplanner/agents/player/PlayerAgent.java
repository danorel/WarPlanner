package com.danorel.warplanner.agents.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.danorel.warplanner.agents.base.BaseAgent;
import com.danorel.warplanner.agents.AgentWatcher;
import com.danorel.warplanner.config.Preferences;

public class PlayerAgent extends BaseAgent implements AgentWatcher {

    public PlayerAgent(String asset, float initialX, float initialY) {
        super(asset, initialX, initialY);
    }

    @Override
    public void watch() {
        super.watch();
        this.watchKeyEvents();
    }

    @Override
    public void watchKeyEvents() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            float absoluteDiff = Preferences.FRAMES_PER_SECOND * Gdx.graphics.getDeltaTime();
            float numericalDiff = Gdx.input.isKeyPressed(Input.Keys.LEFT) ? -absoluteDiff : absoluteDiff;
            this.setX(this.getX() + numericalDiff);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            float absoluteDiff = Preferences.FRAMES_PER_SECOND * Gdx.graphics.getDeltaTime();
            float numericalDiff = Gdx.input.isKeyPressed(Input.Keys.DOWN) ? -absoluteDiff : absoluteDiff;
            this.setY(this.getY() + numericalDiff);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
