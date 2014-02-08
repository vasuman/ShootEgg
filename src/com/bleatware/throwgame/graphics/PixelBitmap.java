package com.bleatware.throwgame.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.bleatware.throwgame.math.Vector;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 2:22 AM
 */
public class PixelBitmap implements Drawable.Drawer {
    private Vector position = new Vector();
    private Bitmap bitmap;
    private Paint paint = new Paint();
    private float rotation = 0;
    public PixelBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);
    }
    public PixelBitmap(Bitmap bitmap, boolean anchor) {
        this(bitmap);
        position.set(bitmap.getWidth() / 2, bitmap.getHeight() / 2);
    }
    public void setPosition(Vector position) {
        this.position.set(position);
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void setRotation(float degrees) {
        rotation = degrees;
    }
    public void setRotation(Vector orient) {
        setRotation((float) (180 / Math.PI * Math.atan2(orient.y, orient.x)));
    }
    public void rotate(float degrees) {
        rotation += degrees;
    }
    @Override
    public void draw(Canvas c) {
        if(rotation != 0) {
            c.save();
            c.translate(position.x, position.y);
            c.rotate(rotation);
            c.drawBitmap(bitmap, -bitmap.getWidth() / 2, -bitmap.getHeight() / 2, paint);
            c.restore();
        } else {
            c.drawBitmap(bitmap, position.x - bitmap.getWidth() / 2, position.y - bitmap.getHeight() / 2, paint);
        }
    }
}
