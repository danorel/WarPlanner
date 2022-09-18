package com.danorel.warplanner.agents.player.ai;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

import com.badlogic.gdx.utils.Array;

import com.danorel.warplanner.agents.base.BaseAgent;

import static com.danorel.warplanner.agents.player.ai.PlayerAction.values;

public class PlayerBestFirstSearchAgent extends BaseAgent {

    private float cost;
    private PlayerAction action;
    private PlayerBestFirstSearchAgent parent;

    public PlayerBestFirstSearchAgent(float initialX, float initialY) {
        this(initialX, initialY, 0, null, null);
    }

    public PlayerBestFirstSearchAgent(float initialX, float initialY, float cost, PlayerAction action, PlayerBestFirstSearchAgent parent) {
        this.setX(initialX);
        this.setY(initialY);
        this.cost = cost;
        this.action = action;
        this.parent = parent;
    }

    public PlayerBestFirstSearchAgent find(Array<BaseAgent> targetAgents) {
        PlayerBestFirstSearchAgent agent = search(targetAgents);
        if (agent == null) {
            return null;
        }
        while (agent.parent != null) {
            if (agent.parent.getX() == this.getX() && agent.parent.getY() == this.getY()) {
                return agent;
            }
        }
        return agent;
    }

    private PlayerBestFirstSearchAgent search(Array<BaseAgent> targetAgents) {
        MinMaxPriorityQueue<PlayerBestFirstSearchAgent> queue = new MinMaxPriorityQueue<>(Comparator.comparingDouble(PlayerBestFirstSearchAgent::evaluate));
        queue.add(this);
        HashSet<String> visited = new HashSet<>();
        visited.add(this.getX() + "x" + this.getY());
        while (!queue.isEmpty()) {
            PlayerBestFirstSearchAgent currentAgent = queue.poll();
            if (currentAgent.overlapsAny(targetAgents)) {
                return currentAgent;
            } else {
                for (PlayerAction action : values()) {
                    PlayerBestFirstSearchAgent nextAgent = currentAgent.transition(action);
                    if (nextAgent == null) {
                        continue;
                    }
                    String footprint = nextAgent.getX() + "x" + nextAgent.getY();
                    if (!visited.contains(footprint)) {
                        visited.add(footprint);
                        queue.add(nextAgent);
                        if (queue.size() > 1024) {
                            queue.removeLast();
                        }
                    }
                }
            }
        }
        return null;
    }

    private double evaluate() {
        return this.cost;
    }

    private PlayerBestFirstSearchAgent transition(PlayerAction action) {
        float x = this.getX();
        float y = this.getY();
        switch (action) {
            case Left: {
                if (x == 0) return null;
                return new PlayerBestFirstSearchAgent(x - 1, y, cost + 1, action, this);
            }
            case Up: {
                if (y == 480) return null;
                return new PlayerBestFirstSearchAgent(x, y + 1, cost + 1, action, this);
            }
            case Right: {
                if (y == 800) return null;
                return new PlayerBestFirstSearchAgent(x + 1, y, cost + 1, action, this);
            }
            case Down: {
                if (y == 0) return null;
                return new PlayerBestFirstSearchAgent(x, y - 1, cost + 1, action, this);
            }
            default: {
                throw new RuntimeException("Unknown action: " + action);
            }
        }
    }
}
