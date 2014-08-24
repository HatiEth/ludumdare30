package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.gameobjects.BackgroundGO;

public class Haltestelle0 extends Scene
{

    BackgroundGO background;

    public Haltestelle0(GameData gameData)
    {
        super(gameData);

        background = new BackgroundGO("haltestelle01.png");
        addObject(background);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.getPosition().set(-882, -354);
    }

}
