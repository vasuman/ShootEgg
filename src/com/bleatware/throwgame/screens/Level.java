package com.bleatware.throwgame.screens;

import android.graphics.Camera;
import android.graphics.Canvas;
import com.bleatware.throwgame.Counter;
import com.bleatware.throwgame.Data;
import com.bleatware.throwgame.Input;
import com.bleatware.throwgame.entities.Launcher;
import com.bleatware.throwgame.entities.Pan;
import com.bleatware.throwgame.graphics.Drawable;
import com.bleatware.throwgame.Game;
import com.bleatware.throwgame.entities.GameEntity;
import com.bleatware.throwgame.graphics.PixelBitmap;
import com.bleatware.throwgame.math.Vector;
import com.bleatware.throwgame.physics.World;

import java.util.ArrayList;
import java.util.List;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 7:37 PM
 */
public class Level implements Screen {
    private static final float Y_EXCEED_RATIO = 3f;
    private static final float SHAKE_TIME = 1;
    private static final float SHAKE_DISP = 5;
    private final List<GameEntity> entities = new ArrayList<GameEntity>();
    public World world;
    public Camera camera = new Camera();
    private Drawable.Drawer background;
    private Input input;
    public Stats stats;
    private Game g;
    private Counter shakeTimer = new Counter(SHAKE_TIME);
    private boolean shake = false;

    public void shake() {
        shake = true;
        shakeTimer.reset();
    }

    @Override
    public void create(Game g) {
        this.g = g;
        GameEntity.setLevel(this);
        world = new World(0, (1 - Y_EXCEED_RATIO) * Game.S_HEIGHT, Game.S_WIDTH, Game.S_HEIGHT * Y_EXCEED_RATIO);
        input = new Input(world);
        stats = new Stats(3);
        g.view.setOnTouchListener(input);
        world.setGravity(new Vector(0, 200));
        background = new PixelBitmap(Data.background, true);
        // DEBUG!!
        new Launcher();
        new Pan();
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
        if(stats.gameOver()) {
            g.setScreen(new StartScreen(stats.getScore()));
            return;
        }
        world.update(delT);
        input.process();
        for(int i = entities.size() - 1; i >= 0; i--) {
            GameEntity e = entities.get(i);
            if(e.isDead()) {
                entities.remove(i);
                e.destroy();
                continue;
            }
            e.update(delT);
        }
        if(shake && shakeTimer.update(delT)) {
            shake = false;
        }
    }

    @Override
    public void draw(Canvas c) {
        if(shake) {
            c.translate((float) Math.random() * SHAKE_DISP, (float) Math.random() * SHAKE_DISP);
        }
        background.draw(c);
        synchronized (entities) {
            for(GameEntity e:  entities) {
                if(e instanceof Drawable) {
                    Drawable.Drawer d = ((Drawable) e).getDrawer();
                    d.draw(c);
                }
            }
        }
        stats.getDrawer().draw(c);
    }

    public void addEntity(GameEntity gameEntity) {
        entities.add(gameEntity);
    }
}
