package com.bleatware.throwgame;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import com.bleatware.throwgame.screens.Level;
import com.bleatware.throwgame.screens.SplashScreen;

/**
 * ThrowGame
 * User: vasuman
 * Date: 2/7/14
 * Time: 8:43 PM
 */
public class GameActivity extends Activity {
    private Game game;
    private Thread gameThread;
    public static Accelerometer accelerometer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SurfaceView view = new SurfaceView(this);
        setContentView(view);
        Data.DISPLAY_DENSITY = getResources().getDisplayMetrics().densityDpi;
        Data.init(getResources());
        accelerometer = new Accelerometer();
        doRegister();
        game = new Game(view);
        game.setScreen(new SplashScreen());
        gameThread = new Thread(game);
        gameThread.start();
    }

    private void doRegister() {
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor acc = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(accelerometer, acc, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void unregister() {
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        manager.unregisterListener(accelerometer);
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