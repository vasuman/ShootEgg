package com.bleatware.throwgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/8/14
 * Time: 1:49 AM
 */
public class Data {
    public static final int scale = 2;
    public static int DISPLAY_DENSITY;
    public static Bitmap egg;
    public static Bitmap cannon;
    public static Bitmap base;
    public static Bitmap background;
    public static Bitmap splatter;
    public static Bitmap pan;
    public static Bitmap splash;

    public static void init(Resources res) {
        background = getBitmap(res, R.drawable.background);
        egg = getBitmap(res, R.drawable.egg);
        base = getBitmap(res, R.drawable.base);
        splatter = getBitmap(res, R.drawable.splat);
        cannon = getBitmap(res, R.drawable.cannon);
        pan = getBitmap(res, R.drawable.plate);
        splash = getBitmap(res, R.drawable.splash);
    }

    private static Bitmap getBitmap(Resources res, int id) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = DISPLAY_DENSITY;
        options.inTargetDensity = DISPLAY_DENSITY;
        return BitmapFactory.decodeResource(res, id, options);
    }
}
