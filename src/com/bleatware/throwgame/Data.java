package com.bleatware.throwgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 1:49 AM
 */
public class Data {
    public static final int scale = 2;
    public static Bitmap egg;
    public static Bitmap cannon;
    public static Bitmap base;
    public static Bitmap background;
    public static Bitmap splatter;
    public static Bitmap pan;

    public static void init(Resources res) {
        background = getBitmap(res, R.drawable.background);
        egg = getBitmap(res, R.drawable.egg);
        base = getBitmap(res, R.drawable.base);
        splatter = getBitmap(res, R.drawable.splat);
        cannon = getBitmap(res, R.drawable.cannon);
        pan = getBitmap(res, R.drawable.plate);
    }

    private static Bitmap getBitmap(Resources res, int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(res, id);
        return Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / scale, bitmap.getHeight() / scale, false);
    }
}
