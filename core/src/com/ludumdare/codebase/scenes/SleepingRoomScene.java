package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.Gdx;
import com.ludumdare.codebase.GameData;

public class SleepingRoomScene extends Scene
{
    public SleepingRoomScene(GameData gameData)
    {
        super(gameData);
    }

    @Override
    public void onEnter(Scene from)
    {
        if (from == null) // starting
        {
            Gdx.app.log("INFO", "Starting game");
            gameData.haraldGameObject.setPosition(0, 0);
            pathEngine.setTargetPosition(gameData.haraldGameObject
                    .getPosition());
        }
    }
}
