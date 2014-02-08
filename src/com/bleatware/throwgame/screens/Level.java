package com.bleatware.throwgame.screens;

import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.bleatware.throwgame.Data;
import com.bleatware.throwgame.Input;
import com.bleatware.throwgame.entities.Launcher;
import com.bleatware.throwgame.entities.Pan;
import com.bleatware.throwgame.graphics.Drawable;
import com.bleatware.throwgame.Game;
import com.bleatware.throwgame.entities.Egg;
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
    private final List<GameEntity> entities = new ArrayList<GameEntity>();
    public World world;
    public Camera camera = new Camera();
    private Drawable.Drawer background;
    private Input input;
    public int score;
    private Paint paint = new Paint();

    @Override
    public void create(Game g) {
        GameEntity.setLevel(this);
        world = new World(0, (1 - Y_EXCEED_RATIO) * Game.S_HEIGHT, Game.S_WIDTH, Game.S_HEIGHT * Y_EXCEED_RATIO);
        input = new Input(world);
        g.view.setOnTouchListener(input);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
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
        world.update(delT);
        for(int i = entities.size() - 1; i >= 0; i--) {
            GameEntity e = entities.get(i);
            if(e.isDead()) {
                entities.remove(i);
                e.destroy();
                continue;
            }
            e.update(delT);
        }
    }

    @Override
    public void draw(Canvas c) {
        background.draw(c);
        synchronized (entities) {
            for(GameEntity e:  entities) {
                if(e instanceof Drawable) {
                    Drawable.Drawer d = ((Drawable) e).getDrawer();
                    d.draw(c);
                }
            }
        }
        c.drawText("Score: " + score, 100, 100, paint);
    }

    public void addEntity(GameEntity gameEntity) {
        entities.add(gameEntity);
    }
}
