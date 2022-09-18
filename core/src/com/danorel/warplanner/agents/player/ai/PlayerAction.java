package com.danorel.warplanner.agents.player.ai;

public enum PlayerAction {
    Left("LEFT"),
    Up("UP"),
    Right("RIGHT"),
    Down("DOWN");

    private final String name;

    PlayerAction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PlayerAction{" +
                "name='" + name + '\'' +
                '}';
    }
}
