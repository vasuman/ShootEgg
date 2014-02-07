package com.bleatware.throwgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.bleatware.throwgame.screens.Screen;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 7:15 PM
 */
public class Game extends Thread {
    private static final long LOCK_DELTA = 1000 / 60;
    private static final long MIN_SLEEP = 10;
    private SurfaceView view;
    private boolean running = false;
    private long prevTime;
    private Screen screen;

    public Game(SurfaceView view) {
        this.view = view;
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
            this.screen.create();
        }
    }

    public boolean hasScreen() {
        return screen != null;
    }

    @Override
    public synchronized void start() {
        if(hasScreen()) {
            screen.create();
        }
    }

    @Override
    public void run() {
        prevTime = System.currentTimeMillis();
        try {
            Thread.sleep(LOCK_DELTA);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(running) {
            long startTime = System.currentTimeMillis();
            if(hasScreen()) {
                screen.update((startTime - prevTime) / 1000.0f);
            }
            Canvas c = view.getHolder().lockCanvas();
            synchronized (view.getHolder()) {
                if(hasScreen()) {
                    screen.draw(c);
                }
            }
            long endTime = System.currentTimeMillis();
            try {
                long deltaTime = endTime - startTime;
                if(deltaTime < LOCK_DELTA) {
                    sleep(LOCK_DELTA - deltaTime);
                } else {
                    sleep(MIN_SLEEP);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    public void destroy() {
        running = false;
        if(hasScreen()) {
            this.screen.destroy();
        }
    }
}
