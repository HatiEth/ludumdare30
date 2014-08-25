package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.gameobjects.BackgroundGO;

public class Haltestelle1 extends Scene
{

    BackgroundGO background;
    BackgroundGO sign;

    public Haltestelle1(GameData gameData)
    {
        super(gameData);

        sign = new BackgroundGO("background/bahn_schild.png");
        sign.setLayer(gameData.CH_LAYER + 0.5f);
        sign.setPosition(614, -16);
        addObject(sign);

        background = new BackgroundGO("background/haltestelle02.png");
        addObject(background);

        PathNode rejectZone = new PathNode(new Vector2(0, 150), 0, 0, 170, 960,
                370, true);
        rejectZone.isReject = true;
        pathEngine.addLeaf(rejectZone);

        rejectZone = new PathNode(new Vector2(0, -484), 0, 0, -484, 960, 100,
                true);
        rejectZone.isReject = true;
        pathEngine.addLeaf(rejectZone);
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

        rejectZone = new PathNode(new Vector2(0, -484), 0, 0, -484, 960, 100,
                true);
        rejectZone.isReject = true;
        pathEngine.addLeaf(rejectZone);
    }

}
