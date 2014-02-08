package com.bleatware.throwgame.entities;

import com.bleatware.throwgame.Data;
import com.bleatware.throwgame.Game;
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
    private PixelBitmap drawer;
    private boolean splat = false;

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
        this.kill();
        Splatter splatter = new Splatter(getPosition());
        splatter.setVelocity(getVelocity().factor(0.2f, 1));
    }

    @Override
    public void destroy() {
        super.destroy();
        if(getPosition().x + body.w >= Game.S_WIDTH) {
            level.stats.damage();
            level.shake();
        }
    }
}
