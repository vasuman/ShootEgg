package com.bleatware.throwgame.graphics;

import android.graphics.Canvas;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 7:39 PM
 */
public interface Drawable {
    public Drawer getDrawer();

    /**
     * ThrowGame
     * User: vasuman
     * Date: 2/7/14
     * Time: 7:37 PM
     */
    interface Drawer {
        public abstract void draw(Canvas c);
    }
}
