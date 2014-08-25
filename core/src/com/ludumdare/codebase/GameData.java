package com.ludumdare.codebase;

import com.ludumdare.codebase.gameobjects.DiebGO;
import com.ludumdare.codebase.gameobjects.HaraldGO;
import com.ludumdare.util.Camera2DControl;

/*
 * Holds persistent game objects for scenes and interaction storage
 */
public class GameData
{
    public static boolean isDevMode = true;

    public final float UPDATE_FREQUENCY = 0.016f;

    public final Camera2DControl cameraControl;

    public final HaraldGO haraldGameObject;
    public final DiebGO diebGameObject;

    public GameMode eventMode;

    public GameData()
    {
        eventMode = GameMode.Exploration;

        cameraControl = new Camera2DControl(1920, 1080);
        cameraControl.setPosition(0, 0);

        haraldGameObject = new HaraldGO();
        haraldGameObject.setLayer(0.1f);

        diebGameObject = new DiebGO();
        diebGameObject.setLayer(0.1f);
    }
}
