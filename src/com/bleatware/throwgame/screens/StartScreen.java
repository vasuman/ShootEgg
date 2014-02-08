package com.bleatware.throwgame.screens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import com.bleatware.throwgame.Game;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 11:17 AM
 */
public class StartScreen implements Screen {
    private int score;
    private Paint paint = new Paint();

    @Override
    public void create(final Game g) {
        paint.setTextSize(20);
        paint.setColor(Color.WHITE);
        g.view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                g.setScreen(new Level());
                return true;
            }
        });
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

    }

    @Override
    public void draw(Canvas c) {
        if(score != 0) {
            c.drawText("Score is: " + score, 280, Game.S_HEIGHT / 2, paint);
        }
        c.drawText("Tap to start", 300, Game.S_HEIGHT / 2 + 20, paint);
    }

    public StartScreen(int score) {
        this.score = score;
    }
}
