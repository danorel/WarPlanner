package com.danorel.warplanner.agents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BaseAgent extends Sprite {
    public BaseAgent(String asset, float initialX, float initialY) {
        this(new Texture(Gdx.files.internal(asset)), initialX, initialY);
    }

    public BaseAgent(Texture texture, float initialX, float initialY) {
        super(texture);
        this.setX(initialX);
        this.setY(initialY);
    }

    public boolean overlaps(BaseAgent other) {
        return this.getBoundingRectangle().overlaps(other.getBoundingRectangle());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
