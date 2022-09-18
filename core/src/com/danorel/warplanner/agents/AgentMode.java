package com.danorel.warplanner.agents;

import com.badlogic.gdx.utils.Array;

import com.danorel.warplanner.agents.base.BaseAgent;

public interface AgentMode<Agent extends BaseAgent> {
    Agent automatic(Array<BaseAgent> targetAgents);
    void manual();
}
