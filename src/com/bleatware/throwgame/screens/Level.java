package com.bleatware.throwgame.screens;

import android.graphics.Camera;
import android.graphics.Canvas;
import com.bleatware.throwgame.Drawable;
import com.bleatware.throwgame.Drawer;
import com.bleatware.throwgame.entities.GameEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 7:37 PM
 */
public class Level implements Screen {
    private List<GameEntity> entities;
    private Camera camera;
    private Camera fixedCamera;
    private List<Drawer> fixedDrawers;
    @Override
    public void create() {
        entities = new ArrayList<GameEntity>();
        fixedDrawers = new ArrayList<Drawer>();
        camera = new Camera();
        fixedCamera = new Camera();
    }

    public Camera getCamera() {
        return camera;
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
        for(int i = entities.size() - 1; i >= 0; i--) {
            GameEntity e = entities.get(i);
            if(e.isDead()) {
                entities.remove(i);
                continue;
            }
            e.update(delT);
        }
    }

    @Override
    public void draw(Canvas c) {
        camera.applyToCanvas(c);
        for(GameEntity e:  entities) {
            if(e instanceof Drawable) {
                Drawer d = ((Drawable) e).getDrawer();
                if(!d.isFixed()) {
                    fixedDrawers.add(d);
                    continue;
                }
                d.draw(c);
            }
        }
        fixedCamera.applyToCanvas(c);
        for(Drawer d: fixedDrawers) {
            d.draw(c);
        }
    }

    public void addEntity(GameEntity gameEntity) {
        entities.add(gameEntity);
    }
}
