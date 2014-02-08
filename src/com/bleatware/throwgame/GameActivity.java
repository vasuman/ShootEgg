package com.bleatware.throwgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import com.bleatware.throwgame.screens.Level;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 8:43 PM
 */
public class GameActivity extends Activity {
    private Game game;
    private Thread gameThread;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SurfaceView view = new SurfaceView(this);
        setContentView(view);
        Data.init(getResources());
        game = new Game(view);
        game.setScreen(new Level());
        gameThread = new Thread(game);
        gameThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Data.init(getResources());
        game.unpause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            game.stop();
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}