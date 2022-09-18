package com.danorel.warplanner.agents.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import com.danorel.warplanner.agents.AgentWatcher;

import java.util.Arrays;

public class BaseAgent extends Sprite implements AgentWatcher {
    public BaseAgent() {}

    public BaseAgent(String asset, float initialX, float initialY) {
        this(new Texture(Gdx.files.internal(asset)), initialX, initialY);
    }

    public BaseAgent(Texture texture, float initialX, float initialY) {
        super(texture);
        this.setX(initialX);
        this.setY(initialY);
    }

    public boolean overlapsAny(Array<BaseAgent> others) {
        for (BaseAgent other : others) {
            if (this.getBoundingRectangle().overlaps(other.getBoundingRectangle())) {
                return true;
            }
        }
        return false;
    }

    public boolean overlaps(BaseAgent other) {
        return this.getBoundingRectangle().overlaps(other.getBoundingRectangle());
    }

    @Override
    public void watch() {
        this.watchMoves();
        this.watchKeyEvents();
    }

    @Override
    public void watchMoves() {
        if(this.getX() < 0) this.setX(0);
        if(this.getX() > 800 - this.getWidth()) this.setX(800 - this.getWidth());
        if(this.getY() < 0) this.setY(0);
        if(this.getY() > 480 - this.getHeight()) this.setY(480 - this.getHeight());
    }

    @Override
    public void watchKeyEvents() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
