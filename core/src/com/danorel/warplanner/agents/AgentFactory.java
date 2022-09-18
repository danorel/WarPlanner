package com.danorel.warplanner.agents;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;

import com.danorel.warplanner.agents.base.BaseAgent;
import com.danorel.warplanner.agents.base.BaseFactory;
import com.danorel.warplanner.world.LocationGenerator;

public class AgentFactory<Agent extends BaseAgent> extends LocationGenerator {

    private final BaseFactory<Agent> agentFactory;

    public AgentFactory(BaseFactory<Agent> agentFactory, float from, float to) {
        super(from, to);
        this.agentFactory = agentFactory;
    }

    private Agent random(String asset) {
        Vector2 position = this.randomPosition();
        return this.agentFactory.generate(asset, position.x, position.y);
    }

    public Agent randomOne(String asset) {
        return this.random(asset);
    }

    public Array<Agent> randomMany(String asset, byte N) {
        Array<Agent> agents = new Array<>(N);
        for (int i = 0; i < N; ++i) {
            Agent agent = this.random(asset);
            agents.insert(i, agent);
        }
        return agents;
    }
}
