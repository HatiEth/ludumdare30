package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.PathNode;

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

    @Override
    public void create()
    {
        PathNode rejectZone = new PathNode(new Vector2(0, 150), 0, 0, 150, 960,
                400, true);
        rejectZone.isReject = true;
        pathEngine.addLeaf(rejectZone);
    }

}
