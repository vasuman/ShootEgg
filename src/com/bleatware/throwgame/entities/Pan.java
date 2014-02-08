package com.bleatware.throwgame.entities;

import com.bleatware.throwgame.Counter;
import com.bleatware.throwgame.Data;
import com.bleatware.throwgame.Game;
import com.bleatware.throwgame.GameActivity;
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
    public static final float VEL_X = 50f;
    public static final float P_W = 60;
    public static final float P_H = 10;
    private static final float X_BOUND_MAX = 700;
    private static final float X_BOUND_MIN = 100;
    private static final float MOVE_INTERVAL = 5.5f;
    private static final float ALLOWED_DISP = 10;
    private static final float MIN_VEL = 120;
    private PixelBitmap drawer;
    private float moveTo;
    private Counter moveCounter = new Counter(MOVE_INTERVAL);
    public Pan() {
        super(new Vector(Game.S_WIDTH / 2, Game.S_HEIGHT - P_H), P_W, P_H);
        drawer = new PixelBitmap(Data.pan);
        drawer.setPosition(getPosition());
        body.setFixed(true);
        moveCounter.fire();
    }

    @Override
    public void collision(PhysicalEntity other) {
        other.kill();
        if(other instanceof Splatter) {
            level.stats.score();
            // SCORE ++;
        }

    }

    @Override
    public void update(float delT) {
        /*
        if(moveCounter.update(delT)) {
            moveTo = (float) (Math.random() * (X_BOUND_MAX - X_BOUND_MIN) + X_BOUND_MIN);
        }
        float dispX = moveTo - getPosition().x;
        if(Math.abs(dispX) > ALLOWED_DISP) {
            setVelocity(new Vector(Math.min(dispX, MIN_VEL) * VEL_X, 0));
        } else {
            setVelocity(new Vector());
        }
        */
        drawer.setPosition(getPosition());
        float posX = getPosition().x;
        boolean hitLow = posX < X_BOUND_MIN;
        boolean hitHigh = posX > X_BOUND_MAX;
        float accX = GameActivity.accelerometer.getValY();
        if(hitLow) {
            if(accX < 0) {
                setVelocity(0, 0);
                return;
            }
        }
        if(hitHigh) {
            if(accX > 0) {
                setVelocity(0, 0);
                return;
            }
        }
        setVelocity(accX * VEL_X, 0);
    }


    @Override
    public Drawer getDrawer() {
        return drawer;
    }
}
