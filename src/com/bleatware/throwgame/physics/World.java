package com.bleatware.throwgame.physics;

import com.bleatware.throwgame.math.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 7:57 PM
 */
public class World {
    public void removeBody(Body body) {
        bodies.remove(body);
    }

    private static class Idx {
        int i, j;
        public Idx(int i, int j) {
            this.i = i;
            this.j = j;
        }
        public int getSeek() {
            return i * X_PART + j;
        }
    }
    public static final int X_PART = 10;
    public static final int Y_PART = 10;
    private List<Body>[] buckets;
    private List<Body> bodies;
    private float x, y, w, h;
    private Vector gravity = new Vector();
    private AABB aabb;

    public World(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        aabb = new AABB();
        buckets = new ArrayList[X_PART * Y_PART];
        for(int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Body>();
        }
        bodies = new ArrayList<Body>();
    }

    public void addBody(Body b) {
        bodies.add(b);
    }
    private void hashBody(Body b) {
        aabb.set(b);
        if(aabb.min.x < x || aabb.min.y < y || aabb.max.x >= x + w || aabb.max.y >= y + h) {
            b.owner.kill();
            return;
        }
        hashAABB(aabb);
    }

    private void hashAABB(AABB aabb) {
        Idx s = getHash(aabb.min);
        Idx e = getHash(aabb.max);
        for(int i = s.i; i <= e.i; i++) {
            for(int j =s.j; j <= e.j; j++) {
                buckets[i * X_PART + j].add(aabb.b);
            }
        }
    }

    public void setGravity(Vector gravity) {
        this.gravity = gravity;
    }

    private Idx getHash(Vector v) {
        return new Idx((int) ((v.y - y) / h * Y_PART), (int) ((v.x - x) / w * X_PART));
    }


    public void update(float delT) {
        for(List<Body> bucket: buckets) {
            bucket.clear();
        }
        for(Body b: bodies) {
            if(!b.fixed) {
                b.acceleration.set(gravity);
            }
            integrate(b.velocity, b.acceleration, delT);
            integrate(b.position, b.velocity, delT);
            hashBody(b);
        }
        for(List<Body> bucket: buckets) {
            for(int i = 0; i < bucket.size(); i++) {
                Body a = bucket.get(i);
                for(int j = i + 1; j < bucket.size(); j++) {
                    Body b = bucket.get(j);
                    if(a.collide(b)) {
                        a.owner.collision(b.owner);
                        b.owner.collision(a.owner);
                    }
                }
            }
        }
    }

    public Body getContactPoint(Vector point, float size) {
        Idx i = getHash(point);
        for(Body b: buckets[i.getSeek()]) {
            if(b.contains(point, size)) {
                return b;
            }
        }
        return null;
    }

    private void integrate(Vector a, Vector b, float delT) {
        a.add(b.x * delT, b.y * delT);
    }

}
