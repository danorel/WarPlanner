package com.danorel.warplanner.agents.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

import com.danorel.warplanner.agents.AgentMode;
import com.danorel.warplanner.agents.base.BaseAgent;
import com.danorel.warplanner.agents.AgentWatcher;
import com.danorel.warplanner.agents.player.ai.PlayerBestFirstSearchAgent;
import com.danorel.warplanner.config.Preferences;

public class PlayerAgent extends BaseAgent implements AgentMode<PlayerAgent>, AgentWatcher {

    public PlayerAgent(PlayerAgent other) {
        this(other, other.getX(), other.getY());
    }

    public PlayerAgent(PlayerAgent other, float initialX, float initialY) {
        super(other.getTexture(), initialX, initialY);
    }

    public PlayerAgent(String asset, float initialX, float initialY) {
        super(asset, initialX, initialY);
    }

    @Override
    public PlayerAgent automatic(Array<BaseAgent> targetAgents) {
        PlayerBestFirstSearchAgent bfs = new PlayerBestFirstSearchAgent(this.getX(), this.getY());
        PlayerBestFirstSearchAgent agent = bfs.find(targetAgents);
        if (agent == null) {
            return null;
        }
        return new PlayerAgent(this, agent.getX(), agent.getY());
    }

    @Override
    public void manual() {
        this.watch();
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
