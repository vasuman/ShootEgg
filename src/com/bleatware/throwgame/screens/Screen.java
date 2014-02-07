package com.bleatware.throwgame.screens;

import android.graphics.Canvas;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 7:16 PM
 */
public interface Screen {
    public void create();
    public void destroy();
    public void pause();
    public void resume();
    public void update(float delT);
    public void draw(Canvas c);
}
