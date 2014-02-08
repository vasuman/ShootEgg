package com.bleatware.throwgame.screens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.bleatware.throwgame.graphics.Drawable;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 11:07 AM
 */
public class Stats implements Drawable {

    private Drawer drawer;
    private int score;
    private int health;
    public Stats(int health) {
        score = 0;
        this.health = health;
        final Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        drawer = new Drawer() {
            @Override
            public void draw(Canvas c) {
                c.drawText("Score: " + score + ", Health: " + Stats.this.health, 0, 20, paint);
            }
        };
    }
    public void score() {
        score += 1;
    }
    public void damage() {
        health -= 1;
    }

    public boolean gameOver() {
        return health <= 0;
    }
    @Override
    public Drawer getDrawer() {
        return drawer;
    }

    public int getScore() {
        return score;
    }
}
