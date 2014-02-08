package com.bleatware.throwgame.entities;

import com.bleatware.throwgame.Counter;
import com.bleatware.throwgame.Data;
import com.bleatware.throwgame.Game;
import com.bleatware.throwgame.graphics.CompositeDrawer;
import com.bleatware.throwgame.graphics.Drawable;
import com.bleatware.throwgame.graphics.PixelBitmap;
import com.bleatware.throwgame.math.Vector;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 3:04 AM
 */
public class Launcher extends GameEntity implements Drawable{

    public static final Vector position = new Vector(25, Game.S_HEIGHT - 20);
    private CompositeDrawer drawer;
    private PixelBitmap cannonImage;
    private float eggSpeed = 450f;
    private float rotation = 0;
    private Counter counter = new Counter(2f);
    public Launcher() {
        cannonImage = new PixelBitmap(Data.cannon);
        cannonImage.setPosition(position);
        PixelBitmap baseImage = new PixelBitmap(Data.base);
        baseImage.setPosition(position);
        drawer = new CompositeDrawer(cannonImage, baseImage);
    }
    @Override
    public Drawer getDrawer() {
        return drawer;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void update(float delT) {
        if(counter.update(delT)) {
            rotation = 20 + (float) Math.random() * 60;
            cannonImage.setRotation(-rotation);
            Egg egg = new Egg(position);
            egg.body.setVelocity(new Vector(eggSpeed, 0).rotate(rotation));
        }
    }
}
