package com.danorel.warplanner.agents.crystal;

import com.danorel.warplanner.agents.base.BaseFactory;

public class CrystalFactory implements BaseFactory<CrystalAgent> {
    @Override
    public CrystalAgent generate(String asset, float x, float y) {
        return new CrystalAgent(asset, x, y);
    }
}
