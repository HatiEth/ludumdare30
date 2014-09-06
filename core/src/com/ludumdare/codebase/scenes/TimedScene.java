package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;

public abstract class TimedScene extends Scene
{
    final float requiredTimeInSeconds;
    float currentTime;
    boolean dirtyFlag;

    public TimedScene(float timeInScene, GameData gamedata)
    {
        super(gamedata);
        requiredTimeInSeconds = timeInScene;
        dirtyFlag = true;
    }

    @Override
    public void onEnter(Scene from)
    {
        currentTime = 0;
        dirtyFlag = true;
    }

    @Override
    public void create()
    {

    }

    @Override
    public void update()
    {
        super.update();
        currentTime = currentTime + gameData.UPDATE_FREQUENCY;

        if (currentTime >= requiredTimeInSeconds && dirtyFlag)
        {
            // exit TimedScene
            System.out.println("Exit scene");
            timeout();
            dirtyFlag = false;

        }
    }

    abstract protected void timeout();
}
