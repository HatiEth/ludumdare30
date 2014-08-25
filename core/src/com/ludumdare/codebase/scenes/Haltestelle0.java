package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.gameobjects.BackgroundGO;

public class Haltestelle0 extends Scene
{

    BackgroundGO background;
    BackgroundGO pfosten1;
    BackgroundGO pfosten2;
    BackgroundGO pfosten3;
    BackgroundGO pfosten4;
    BackgroundGO sign;

    public Haltestelle0(GameData gameData)
    {
        super(gameData);

        background = new BackgroundGO("background/haltestelle01.png");
        addObject(background);

        sign = new BackgroundGO("background/bahn_schild.png");
        sign.setLayer(gameData.CH_LAYER + 0.5f);
        sign.setPosition(0, -16);
        addObject(sign);

        pfosten1 = new BackgroundGO("background/pfosten.png");
        pfosten1.setLayer(gameData.CH_LAYER);
        pfosten1.setPosition(-864, -322);
        addObject(pfosten1);

        pfosten2 = new BackgroundGO("background/pfosten.png");
        pfosten2.setLayer(gameData.CH_LAYER);
        pfosten2.setPosition(-622, -312);
        addObject(pfosten2);

        pfosten3 = new BackgroundGO("background/pfosten.png");
        pfosten3.setLayer(gameData.CH_LAYER);
        pfosten3.setPosition(630, -320);
        addObject(pfosten3);

        pfosten4 = new BackgroundGO("background/pfosten.png");
        pfosten4.setLayer(gameData.CH_LAYER);
        pfosten4.setPosition(924, -306);
        addObject(pfosten4);
        //

    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.getPosition().set(-882, -354);
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
