package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.gameobjects.BackgroundGO;

public class FrontOfHouseScene extends Scene
{

    BackgroundGO background;

    public FrontOfHouseScene(GameData gameData)
    {
        super(gameData);
        background = new BackgroundGO("vormhaus.png");
        addObject(background);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.getPosition().set(-442, -220);
    }

}
