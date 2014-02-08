package com.bleatware.throwgame;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 5:02 AM
 */
public class Counter {
    private float t;
    private float count;
    public Counter(float count) {
        this.count = count;
        t = 0;
    }
    public void reset() {
        t = count;
    }
    public void fire() {
        t = 0;
    }
    public void setCount(float count) {
        this.count = count;
    }
    public float getCount() {
        return count;
    }
    public boolean update(float delT) {
        if((t -= delT) < 0) {
            t = count;
            return true;
        }
        return false;
    }
}
