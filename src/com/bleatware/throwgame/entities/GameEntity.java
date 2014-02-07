package com.bleatware.throwgame.entities;

import com.bleatware.throwgame.Game;
import com.bleatware.throwgame.screens.Level;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 7:37 PM
 */
public abstract class GameEntity {
    protected static Level level;
    private boolean dead;

    public static void setLevel(Level level) {
        GameEntity.level = level;
    }
    protected GameEntity() {
        level.addEntity(this);
        dead = false;
    }

    public boolean isDead() {
        return dead;
    }

    public void kill() {
        dead = true;
    }

    public abstract void update(float delT);
}
