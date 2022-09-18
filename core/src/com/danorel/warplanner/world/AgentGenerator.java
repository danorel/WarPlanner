package com.danorel.warplanner.world;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;

import com.danorel.warplanner.agents.BaseAgent;

public class AgentGenerator extends GeneratorUtils {
    public AgentGenerator(float from, float to) {
        super(from, to);
    }

    private BaseAgent random(String asset) {
        Vector2 position = this.randomPosition();
        return new BaseAgent(asset, position.x, position.y);
    }

    public BaseAgent randomOne(String asset) {
        return this.random(asset);
    }

    public Array<BaseAgent> randomMany(String asset, byte N) {
        Array<BaseAgent> agents = new Array<>(N);
        for (int i = 0; i < N; ++i) {
            BaseAgent agent = this.random(asset);
            agents.insert(i, agent);
        }
        return agents;
    }
}
