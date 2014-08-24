package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;

public class KitchenScene extends Scene
{

    public KitchenScene(GameData gameData)
    {
        super(gameData);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        if (from instanceof SleepingRoomScene)
        {
            gameData.haraldGameObject.getPosition().set(0, 0);
        }
    }
}
