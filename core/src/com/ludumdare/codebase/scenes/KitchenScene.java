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
        if (from instanceof SleepingRoomScene)
        {
            System.out.println("Entering from SleepingRoomScene");
            gameData.haraldGameObject.getPosition().set(0, 0);
        }
    }
}
