package com.danorel.warplanner.world;

import com.badlogic.gdx.math.Vector2;

public class LocationGenerator {
    private float from;
    private float to;

    public LocationGenerator(float from, float to) {
        this.from = from;
        this.to = to;
    }

    public float randomCoordinate() {
        return Math.round(from + Math.random() * (to - from));
    }

    public Vector2 randomPosition() {
        float x = this.randomCoordinate();
        float y = this.randomCoordinate();
        return new Vector2(x, y);
    }
}
