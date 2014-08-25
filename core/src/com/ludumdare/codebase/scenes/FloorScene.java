package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.gameobjects.BackgroundGO;

public class FloorScene extends Scene
{

    BackgroundGO background;
    BackgroundGO foregroundLight;

    public FloorScene(GameData gameData)
    {
        super(gameData);
        background = new BackgroundGO("flur.png");
        foregroundLight = new BackgroundGO("flur_light.png");

        addObject(background);

        foregroundLight.setLayer(10.0f);
        addObject(foregroundLight);

    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        if (from instanceof KitchenScene)
        {
            gameData.haraldGameObject.getPosition().set(-882, -354);
        }
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
