package com.bleatware.throwgame.graphics;

import android.graphics.Canvas;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 3:02 AM
 */
public class CompositeDrawer implements Drawable.Drawer {
    private Drawable.Drawer[] drawers;
    public CompositeDrawer(Drawable.Drawer... drawers) {
        this.drawers = drawers;
    }
    @Override
    public void draw(Canvas c) {
        for(Drawable.Drawer d: drawers) {
            d.draw(c);
        }
    }
}
