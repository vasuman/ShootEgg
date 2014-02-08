package com.bleatware.throwgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.bleatware.throwgame.screens.Screen;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 7:15 PM
 */
public class Game implements Runnable, SurfaceHolder.Callback {
    public static final float S_WIDTH = 800;
    public static final float S_HEIGHT = 450;
    private static final long LOCK_DELTA = 1000 / 60;
    private static final long MIN_SLEEP = 10;
    public SurfaceView view;
    private boolean running = true;
    private Screen screen;
    public static Input input;
    public static float scaleX, scaleY;
    private boolean ready = false;

    public Game(SurfaceView view) {
        this.view = view;
        view.getHolder().addCallback(this);
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        if(hasScreen()) {
            this.screen.destroy();
        }
        this.screen = screen;
        if(hasScreen()) {
            this.screen.create(this);
        }
    }

    public boolean hasScreen() {
        return screen != null;
    }

    @Override
    public void run() {
        long prevTime = System.currentTimeMillis();
        while(!ready) {
            try {
                prevTime = System.currentTimeMillis();
                Thread.sleep(LOCK_DELTA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(running) {
            long startTime = System.currentTimeMillis();
            if(hasScreen()) {
                screen.update((startTime - prevTime) / 1000.0f);
            }
            Canvas c = view.getHolder().lockCanvas();
            if(c == null) {
                return;
            }
            c.scale(view.getWidth() / S_WIDTH, view.getHeight() / S_HEIGHT);
            c.drawColor(Color.BLACK);
            if(hasScreen()) {
                screen.draw(c);
            }
            view.getHolder().unlockCanvasAndPost(c);
            long endTime = System.currentTimeMillis();
            try {
                long deltaTime = endTime - startTime;
                if(deltaTime < LOCK_DELTA) {
                    Thread.sleep(LOCK_DELTA - deltaTime);
                } else {
                    Thread.sleep(MIN_SLEEP);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            prevTime = startTime;
        }
    }

    public void pause() {
        if(hasScreen()) {
            screen.pause();
        }
    }

    public void unpause() {
        if(hasScreen()) {
            screen.resume();
        }
    }

    public void stop() {
        running = false;
        if(hasScreen()) {
            this.screen.destroy();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        ready = true;
        scaleX = view.getWidth() / S_WIDTH;
        scaleY = view.getHeight() / S_HEIGHT;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
