package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;

public class TimedScene extends Scene
{
    final float requiredTimeInSeconds;
    float currentTime;

    public TimedScene(float timeInScene, GameData gamedata)
    {
        super(gamedata);
        requiredTimeInSeconds = timeInScene;
    }

    @Override
    public void onEnter(Scene from)
    {
        currentTime = 0;
    }

    @Override
    public void create()
    {

    }

    @Override
    public void update()
    {
        super.update();
        currentTime += gameData.UPDATE_FREQUENCY;

        if (currentTime >= requiredTimeInSeconds)
        {
            // exit TimedScene
            System.out.println("Exit scene");
        }
    }
}
