package com.bleatware.throwgame.entities;

import com.bleatware.throwgame.Data;
import com.bleatware.throwgame.graphics.Drawable;
import com.bleatware.throwgame.graphics.PixelBitmap;
import com.bleatware.throwgame.math.Vector;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 5:48 AM
 */
public class Splatter extends PhysicalEntity implements Drawable{
    public static final float S_W = 25;
    public static final float S_H = 10;
    private PixelBitmap drawer;
    public Splatter(Vector position) {
        super(position, S_W, S_H);
        drawer = new PixelBitmap(Data.splatter);
    }

    @Override
    public void collision(PhysicalEntity other) {

    }

    @Override
    public void update(float delT) {
        drawer.setPosition(getPosition());
    }

    @Override
    public Drawer getDrawer() {
        return drawer;
    }
}
