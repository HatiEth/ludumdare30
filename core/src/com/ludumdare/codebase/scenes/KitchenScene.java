package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.gameobjects.BackgroundGO;

public class KitchenScene extends Scene
{

    BackgroundGO background;

    public KitchenScene(GameData gameData)
    {
        super(gameData);
        background = new BackgroundGO("kueche.png");
        addObject(background);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        if (from instanceof SleepingRoomScene)
        {
            gameData.haraldGameObject.getPosition().set(-882, -354);
        }
    }
}
