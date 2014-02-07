package com.bleatware.throwgame.physics;

import com.bleatware.throwgame.entities.GameEntity;
import com.bleatware.throwgame.math.Vector;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 8:18 PM
 */
public class HitBox {
    Vector position;
    float w, h;
    GameEntity owner;

    public HitBox(float w, float h) {
        position = new Vector();
        this.w = w;
        this.h = h;
    }

    public GameEntity getOwner() {
        return owner;
    }

    public void setOwner(GameEntity owner) {
        this.owner = owner;
    }

    public boolean collide(HitBox other) {
        if(Math.abs(position.x - other.position.x) < w + other.w) {
            return true;
        }
        if(Math.abs(position.y - other.position.y) < h + other.h) {
            return true;
        }
        return false;
    }

    public Vector getPosition() {
        return position;
    }
}
