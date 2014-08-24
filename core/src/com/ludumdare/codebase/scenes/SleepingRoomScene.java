package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.Gdx;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.gameobjects.HaraldGO;

public class SleepingRoomScene extends Scene
{
    public SleepingRoomScene(GameData gameData)
    {
        super(gameData);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.setPosition(0, 0);
        if (from == null) // starting
        {
        }
    }
}
