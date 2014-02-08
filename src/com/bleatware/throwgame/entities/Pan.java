package com.bleatware.throwgame.entities;

import com.bleatware.throwgame.Data;
import com.bleatware.throwgame.Game;
import com.bleatware.throwgame.graphics.Drawable;
import com.bleatware.throwgame.graphics.PixelBitmap;
import com.bleatware.throwgame.math.Vector;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 8:23 AM
 */
public class Pan extends PhysicalEntity implements Drawable {
    public static final float VEL_X = 50;
    public static final float P_W = 60;
    public static final float P_H = 10;
    private static final float X_BOUND_MAX = 700;
    private static final float X_BOUND_MIN = 100;
    private PixelBitmap drawer;

    public Pan() {
        super(new Vector(P_W, Game.S_HEIGHT - P_H), P_W, P_H);
        drawer = new PixelBitmap(Data.pan);
        drawer.setPosition(getPosition());
        body.setFixed(true);
    }

    @Override
    public void collision(PhysicalEntity other) {
        other.kill();
        if(other instanceof Splatter) {
            level.score++;
            // SCORE ++;
        }

    }

    @Override
    public void update(float delT) {
        if(getPosition().x < X_BOUND_MIN && getVelocity().x <= 0) {
            setVelocity(new Vector(VEL_X, 0));
        } else if (getPosition().x > X_BOUND_MAX && getVelocity().x >= 0) {
            setVelocity(new Vector(-VEL_X, 0));
        }
        drawer.setPosition(getPosition());
    }


    @Override
    public Drawer getDrawer() {
        return drawer;
    }
}
