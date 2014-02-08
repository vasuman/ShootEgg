package com.bleatware.throwgame.screens;

import android.graphics.Canvas;
import com.bleatware.throwgame.Counter;
import com.bleatware.throwgame.Data;
import com.bleatware.throwgame.Game;
import com.bleatware.throwgame.graphics.Drawable;
import com.bleatware.throwgame.graphics.PixelBitmap;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 2:18 PM
 */
public class SplashScreen implements Screen {
    private Drawable.Drawer drawer;
    private Counter timer = new Counter(3);
    private Game g;
    @Override
    public void create(Game g) {
        this.g = g;
        drawer = new PixelBitmap(Data.splash, true);
        timer.reset();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void update(float delT) {
        if(timer.update(delT)) {
            g.setScreen(new StartScreen(-1));
        }
    }

    @Override
    public void draw(Canvas c) {
        drawer.draw(c);
    }
}
