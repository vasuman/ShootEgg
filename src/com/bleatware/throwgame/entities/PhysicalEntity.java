package com.bleatware.throwgame.entities;

import com.bleatware.throwgame.math.Vector;
import com.bleatware.throwgame.physics.HitBox;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 8:28 PM
 */
public abstract class PhysicalEntity extends GameEntity {
    protected HitBox box;

    protected PhysicalEntity(Vector position, float w, float h) {
        super();
        box = new HitBox(w, h);
        box.getPosition().set(position);
    }

    public Vector getPosition() {
        return box.getPosition();
    }

    public abstract void collision(GameEntity other);
}
