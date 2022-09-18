package com.danorel.warplanner.agents.player;

import com.danorel.warplanner.agents.base.BaseFactory;

public class PlayerFactory implements BaseFactory<PlayerAgent> {
    @Override
    public PlayerAgent generate(String asset, float x, float y) {
        return new PlayerAgent(asset, x, y);
    }
}
