package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;

public class RetirementCenterLeftScene extends Scene
{

    public RetirementCenterLeftScene(GameData gameData)
    {
        super(gameData);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.getPosition().set(-442, -220);
    }

}
