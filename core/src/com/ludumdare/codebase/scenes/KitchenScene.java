package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.gameobjects.BackgroundGO;

public class KitchenScene extends Scene
{

    BackgroundGO background;

    public KitchenScene(GameData gameData)
    {
        super(gameData);
        background = new BackgroundGO("kueche.png");
        addObject(background);

        PathNode rejectZone = new PathNode(new Vector2(0, 150), 0, 0, 150, 960,
                400, true);
        rejectZone.isReject = true;

        pathEngine.addLeaf(rejectZone);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        if (from instanceof SleepingRoomScene)
        {
            gameData.haraldGameObject.getPosition().set(-882, -354);

        }
        if (from instanceof FloorScene)
        {
            gameData.haraldGameObject.getPosition().set(+882, -354);

        }
    }
}
