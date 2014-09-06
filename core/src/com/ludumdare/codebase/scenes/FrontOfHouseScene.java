package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.gameobjects.BackgroundGO;

public class FrontOfHouseScene extends Scene
{

    BackgroundGO background;

    private void addPfosten(float x, float y)
    {
        BackgroundGO pfosten = new BackgroundGO("background/pfosten.png");
        pfosten.setLayer(gameData.CH_LAYER);
        pfosten.setPosition(x, y);
        addObject(pfosten);
    }

    public FrontOfHouseScene(GameData gameData)
    {
        super(gameData);
        background = new BackgroundGO("background/vormhaus.png");
        addObject(background);

        addPfosten(-864, -310);
        addPfosten(-618, -322);
        addPfosten(-358, -300);
        addPfosten(-100, -311);
        addPfosten(144, -308);
        addPfosten(416, -314);
        addPfosten(724, -298);

    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        if (from instanceof Haltestelle0)
        {
            gameData.haraldGameObject.getPosition().set(960 - 32, -280);
        }
        else
        {
            gameData.haraldGameObject.getPosition().set(-442, -220);
        }
    }

    @Override
    public void create()
    {
        PathNode rejectZone = new PathNode(new Vector2(0, 150), 0, 0, 170, 960,
                370, true);
        rejectZone.isReject = true;
        pathEngine.addLeaf(rejectZone);

        rejectZone = new PathNode(new Vector2(0, -484), 0, 0, -484, 960, 100,
                true);
        rejectZone.isReject = true;
        pathEngine.addLeaf(rejectZone);
    }
}
