package com.bleatware.throwgame.entities;

import com.bleatware.throwgame.Drawable;
import com.bleatware.throwgame.Drawer;
import com.bleatware.throwgame.math.Vector;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 8:38 PM
 */
public class Egg extends PhysicalEntity implements Drawable {
    private Drawer drawer;

    protected Egg(Vector position, float size) {
        super(position, size, size);
    }

    @Override
    public void collision(GameEntity other) {

    }

    @Override
    public void update(float delT) {

    }

    @Override
    public Drawer getDrawer() {
        return drawer;
    }
}
