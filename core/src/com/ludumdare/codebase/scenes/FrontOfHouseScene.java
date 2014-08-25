package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.PathNode;
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

    @Override
    public void create()
    {
        PathNode rejectZone = new PathNode(new Vector2(0, 150), 0, 0, 170, 960,
                370, true);
        rejectZone.isReject = true;
        pathEngine.addLeaf(rejectZone);
    }
}
