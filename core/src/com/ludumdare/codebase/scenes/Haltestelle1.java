package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;

public class Haltestelle1 extends Scene
{

    public Haltestelle1(GameData gameData)
    {
        super(gameData);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.getPosition().set(-442, -220);
    }

}
