package com.wdiscute.starcatcher.minigame.modifiers;

public abstract class AbstractTimedModifier extends AbstractModifier{
    int length;

    public AbstractTimedModifier(int length){
        this.length = length;
    }

    @Override
    public void tick() {
        if (tickCount >= length) {
            removed = true;
        }
        super.tick();
    }
}
