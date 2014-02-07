package com.bleatware.throwgame.math;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 8:18 PM
 */
public class Vector {
    public float x, y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Vector() {
        this(0, 0);
    }
    public Vector(Vector other) {
        this(other.x, other.y);
    }

    public Vector add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector add(Vector other) {
        return add(other.x, other.y);
    }

    public Vector sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector copy() {
        return new Vector(this);
    }

    public Vector factor(float v) {
        this.x *= v;
        this.y *= v;
        return this;
    }


    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector other) {
        set(other.x, other.y);
    }
}
