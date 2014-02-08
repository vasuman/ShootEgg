package com.bleatware.throwgame.physics;

import com.bleatware.throwgame.entities.GameEntity;
import com.bleatware.throwgame.entities.PhysicalEntity;
import com.bleatware.throwgame.math.Vector;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 8:18 PM
 */
public class Body {
    public Vector position = new Vector();
    public Vector velocity = new Vector();
    public Vector acceleration = new Vector();
    public float w;
    float h;
    PhysicalEntity owner;

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    boolean fixed;

    public Body(float w, float h) {
        this.w = w;
        this.h = h;
    }


    public void setOwner(PhysicalEntity owner) {
        this.owner = owner;
    }

    public boolean collide(Body other) {
        if(Math.abs(position.x - other.position.x) < w / 2 + other.w / 2) {
            if(Math.abs(position.y - other.position.y) < h / 2 + other.h / 2) {
                return true;
            }
        }
        return false;
    }

    public Vector getPosition() {
        return position;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public boolean contains(Vector point, float size) {
        Vector dist = point.copy().sub(position);
        return Math.abs(dist.x) < w + size && Math.abs(dist.y) < h + size;
    }

    public PhysicalEntity getOwner() {
        return owner;
    }
}
