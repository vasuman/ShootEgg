package com.bleatware.throwgame.entities;

import com.bleatware.throwgame.Data;
import com.bleatware.throwgame.Input;
import com.bleatware.throwgame.graphics.Drawable;
import com.bleatware.throwgame.graphics.PixelBitmap;
import com.bleatware.throwgame.math.Vector;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 8:38 PM
 */
public class Egg extends PhysicalEntity implements Drawable, Input.Touchable {
    public static final float size = 15;
    private boolean touchFlag = false;
    private PixelBitmap drawer;

    public Egg(Vector position) {
        super(position, size, size);
        drawer = new PixelBitmap(Data.egg);
        drawer.setPosition(position);
    }

    @Override
    public void collision(PhysicalEntity other) {

    }

    @Override
    public void update(float delT) {
        if(touchFlag) {
            this.kill();
            new Splatter(getPosition());
            return;
        }
        drawer.setPosition(getPosition());
        Vector velocity = getVelocity();
        if(!velocity.isZero()) {
            drawer.setRotation(velocity);
            drawer.rotate(90);
        }
    }

    @Override
    public Drawer getDrawer() {
        return drawer;
    }


    @Override
    public void touched() {
        touchFlag = true;
    }
}
