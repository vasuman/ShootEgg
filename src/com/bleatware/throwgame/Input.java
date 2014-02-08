package com.bleatware.throwgame;

import android.view.MotionEvent;
import android.view.View;
import com.bleatware.throwgame.entities.PhysicalEntity;
import com.bleatware.throwgame.math.Vector;
import com.bleatware.throwgame.physics.Body;
import com.bleatware.throwgame.physics.World;

import java.util.ArrayList;
import java.util.List;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 4:11 AM
 */
public class Input implements View.OnTouchListener {
    private static final float TOUCH_HIT = 100;
    public static final int LEFT_MOVE_BUTTON = 1;
    public static final int RIGHT_MOVE_BUTTON = 2;

    private List<MotionEvent> eventQueue = new ArrayList<MotionEvent>();

    public static interface Touchable {
        public void touched();
    }

    private World world;
    public Input(World world) {
        this.world = world;
    }

    public void process() {
        for(MotionEvent event: eventQueue) {
            float x = event.getX() / Game.scaleX;
            float y = event.getY() / Game.scaleY;
            Body b = world.getContactPoint(new Vector(x, y), TOUCH_HIT);
            if(b != null) {
                PhysicalEntity e = b.getOwner();
                if(e instanceof Touchable) {
                    ((Touchable) e).touched();
                }
            }
        }
        eventQueue.clear();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            eventQueue.add(event);
            return true;
        }
        return false;
    }

    public static class InputRegion {
    }
}
