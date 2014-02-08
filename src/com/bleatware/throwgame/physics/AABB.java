package com.bleatware.throwgame.physics;

import com.bleatware.throwgame.math.Vector;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 9:57 PM
 */
public class AABB {
    Vector min = new Vector(), max = new Vector();
    Body b;

    public void set(Body b) {
        this.b = b;
        min.set(b.position.x - b.w / 2, b.position.y - b.h / 2);
        max.set(b.position.x + b.w / 2, b.position.y + b.h / 2);
    }
}
