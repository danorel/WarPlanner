package com.danorel.warplanner.agents.base;

public interface BaseFactory<Agent> {
    Agent generate(String asset, float x, float y);
}
