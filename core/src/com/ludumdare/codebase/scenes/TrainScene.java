package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;

public class TrainScene extends Scene
{

    public TrainScene(GameData gameData)
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
