package com.ludumdare.codebase;

import com.ludumdare.codebase.gameobjects.DiebGO;
import com.ludumdare.codebase.gameobjects.HaraldGO;
import com.ludumdare.codebase.interactions.DialogInteractionEvent;
import com.ludumdare.codebase.scenes.HomeSceneGroup;
import com.ludumdare.codebase.scenes.RetirementCenterTasks;
import com.ludumdare.util.Camera2DControl;

/*
 * Holds persistent game objects for scenes and interaction storage
 */
public class GameData
{
    public static boolean isDevMode = true;

    public final float CH_LAYER = 0.1f;
    public final float UPDATE_FREQUENCY = 0.016f;

    public final Camera2DControl cameraControl;

    public final HaraldGO haraldGameObject;
    public final DiebGO diebGameObject;

    public DialogInteractionEvent activeDialog;

    public GameMode eventMode;

    public int DayCounter;

    public RetirementCenterTasks tasks;
    public HomeSceneGroup sceneGroup;

    public boolean hasGivenSeatToOma = false;
    public boolean toldPoliceManAboutBag = false;
    public boolean toldPolicemanAboutEinbruch = false;
    public boolean toldPolicemanAboutBeatup = false;

    public GameData()
    {
        DayCounter = 4;
        eventMode = GameMode.Exploration;

        cameraControl = new Camera2DControl(1920, 1080);
        cameraControl.setPosition(0, 0);

        haraldGameObject = new HaraldGO();
        haraldGameObject.setLayer(CH_LAYER);

        diebGameObject = new DiebGO();
        diebGameObject.setLayer(CH_LAYER);

        tasks = new RetirementCenterTasks();
    }
}
