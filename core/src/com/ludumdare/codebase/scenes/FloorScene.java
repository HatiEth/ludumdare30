package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;

public class FloorScene extends Scene
{

    public FloorScene(GameData gameData)
    {
        super(gameData);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        if (from instanceof KitchenScene)
        {
            gameData.haraldGameObject.setPosition(0, 0);
        }
    }

}
