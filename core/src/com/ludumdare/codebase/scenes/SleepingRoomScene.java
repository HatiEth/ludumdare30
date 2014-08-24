package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.Gdx;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.HaraldGO;

public class SleepingRoomScene extends Scene
{
    BackgroundGO background;

    public SleepingRoomScene(GameData gameData)
    {
        super(gameData);

        background = new BackgroundGO("Schlafzimmer.png");
        addObject(background);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.setPosition(0, 0);
        if (from == null) // starting
        {
            gameData.haraldGameObject.setPosition(-502, -296);
        }
    }
}
