package com.bleatware.throwgame.entities;

import com.bleatware.throwgame.math.Vector;
import com.bleatware.throwgame.physics.Body;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 8:28 PM
 */
public abstract class PhysicalEntity extends GameEntity {
    @Override
    public void destroy() {
        level.world.removeBody(body);
    }

    public Body body;

    protected PhysicalEntity(Vector position, float w, float h) {
        super();
        body = new Body(w, h);
        body.getPosition().set(position);
        body.setOwner(this);
        level.world.addBody(body);
    }

    public Vector getPosition() {
        return body.getPosition();
    }

    public void setVelocity(Vector velocity) {
        body.velocity.set(velocity);
    }

    public void setVelocity(float x, float y) {
        body.velocity.set(x, y);
    }
    public Vector getVelocity() {
        return body.velocity;
    }
    public abstract void collision(PhysicalEntity other);
}
