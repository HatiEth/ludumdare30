package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.GameMode;
import com.ludumdare.codebase.Renderer;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.GameObject;

public class DayDisplayScene extends TimedScene
{
    BackgroundGO[] days;
    int dayIndex;
    GameObject timerExecuteObject;

    public DayDisplayScene(GameData gamedata)
    {
        super(2.0f, gamedata);
        dayIndex = 0;
        days = new BackgroundGO[7];
        for (int i = 0; i < 7; ++i)
        {
            days[i] = new BackgroundGO("day" + (i + 1) + ".png");
        }

        timerExecuteObject = new GameObject()
        {

            @Override
            public void update()
            {

            }

            @Override
            public void render(Renderer renderer)
            {

            }
        };

    }

    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public void onEnter(Scene from)
    {
        super.onEnter(from);
        pathEngine.setGameObject(timerExecuteObject);
        timerExecuteObject.setPosition(50, 0);
        gameData.eventMode = GameMode.Cinematic;
        // gameData.cameraControl.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onLeave(Scene to)
    {
        super.onLeave(to);
        dayIndex = dayIndex + 1;
        gameData.eventMode = GameMode.Exploration;
        // final float GRAY = 0.53333333333333333333333333333333f;
        // Color backgroundColor = new Color(GRAY, GRAY, GRAY, 1.0f);
        // gameData.cameraControl.setBackgroundColor(backgroundColor);
    }

    @Override
    public void render(Renderer renderer)
    {
        super.render(renderer);
        days[dayIndex].render(renderer);
    }

    @Override
    protected void timeout()
    {
        pathEngine.setTargetPosition(new Vector2());
    }
}
