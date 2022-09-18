package com.danorel.warplanner.agents;

import com.badlogic.gdx.graphics.Texture;

public class PlayerAgent extends BaseAgent {
    public PlayerAgent(String asset) {
        this(asset, 0, 0);
    }

    public PlayerAgent(String asset, float initialX, float initialY) {
        super(asset, initialX, initialY);
    }

    public PlayerAgent(Texture texture) {
        this(texture, 0, 0);
    }

    public PlayerAgent(Texture texture, float initialX, float initialY) {
        super(texture, initialX, initialY);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
