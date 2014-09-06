package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.Renderer;
import com.ludumdare.codebase.gameobjects.BackgroundGO;

public class DayDisplayScene extends TimedScene
{
    BackgroundGO[] days;
    int dayIndex;

    public DayDisplayScene(GameData gamedata)
    {
        super(5.0f, gamedata);
        dayIndex = 0;
        days = new BackgroundGO[7];
        for (int i = 0; i < 7; ++i)
        {
            days[i] = new BackgroundGO("day" + (i + 1) + ".png");
        }
    }

    @Override
    public void render(Renderer renderer)
    {
        super.render(renderer);
        days[dayIndex].render(renderer);
    }

}
