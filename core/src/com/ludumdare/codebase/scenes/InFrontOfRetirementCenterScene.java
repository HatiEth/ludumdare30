package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.GameData;

public class InFrontOfRetirementCenterScene extends Scene
{

    public InFrontOfRetirementCenterScene(GameData gameData)
    {
        super(gameData);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.getPosition().set(-442, -220);
    }

    @Override
    public void create()
    {
        // TODO Auto-generated method stub

    }

}
