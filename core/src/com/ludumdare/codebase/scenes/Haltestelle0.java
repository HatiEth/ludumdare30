package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.InteractionNode;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.TextObject;
import com.ludumdare.codebase.gameobjects.GameObject.Direction;
import com.ludumdare.codebase.interactions.DialogInteractionEvent;
import com.ludumdare.codebase.interactions.OnDialogStartEvent;

public class Haltestelle0 extends Scene
{

    BackgroundGO background;
    BackgroundGO pfosten1;
    BackgroundGO pfosten2;
    BackgroundGO pfosten3;
    BackgroundGO pfosten4;
    BackgroundGO sign;
    BackgroundGO train;

    GroupFightGO day3_event;
    InteractionNode day3_talk = null;

    public Haltestelle0(final GameData gameData)
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

        train = new BackgroundGO("background/Bahn.png");
        train.setLayer(15.0f);
        train.setPosition(0, -800);
        addObject(train);

        InteractionNode interaction = new InteractionNode(ObjectState.IDLE,
                Direction.Right, new Vector2(730, -250), 0.0f, 880, -167, 200,
                285, new DialogInteractionEvent(new OnDialogStartEvent()
                {
                    @Override
                    public void start()
                    {
                        gameData.toldPoliceManAboutBag = true;
                    }

                    @Override
                    public void end()
                    {
                        day3_talk.isDisabled = true;
                    }
                }, this, gameData, new TextObject(
                        "sprechblasen/1.Haltestelle/03.01_Yoshi.png",
                        gameData.haraldGameObject, this, new Vector2(100, 350),
                        5.0f), new TextObject(
                        "sprechblasen/1.Haltestelle/03.02_Ian.png",
                        gameData.haraldGameObject, this, new Vector2(100, 350),
                        5.0f), new TextObject(
                        "sprechblasen/1.Haltestelle/03.03_Saga.png",
                        gameData.haraldGameObject, this, new Vector2(100, 350),
                        5.0f), new TextObject(
                        "sprechblasen/1.Haltestelle/03.04_Lady.png",
                        gameData.haraldGameObject, this, new Vector2(100, 350),
                        5.0f), new TextObject(
                        "sprechblasen/1.Haltestelle/03.05_Saga.png",
                        gameData.haraldGameObject, this, new Vector2(100, 350),
                        5.0f), new TextObject(
                        "sprechblasen/1.Haltestelle/03.06_Yoshi.png",
                        gameData.haraldGameObject, this, new Vector2(100, 350),
                        5.0f), new TextObject(
                        "sprechblasen/1.Haltestelle/03.07_Ian.png",
                        gameData.haraldGameObject, this, new Vector2(100, 350),
                        5.0f)));
        interaction.isRepeatable = true;
        interaction.isDisabled = true;
        day3_talk = interaction;
        pathEngine.addLeaf(day3_talk);

        day3_event = new GroupFightGO();
        day3_event.setPosition(880, -240);
        day3_event.setLayer(gameData.CH_LAYER);

    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.getPosition().set(-882, -354);

        System.out.println(lastVisistedDay);
        switch (gameData.DayCounter)
        {
        case 3:
            day3_talk.isDisabled = false;
            addObject(day3_event);
            pathEngine.setTargetPosition(new Vector2(880, -167));
            break;
        }
    }

    @Override
    public void onLeave(Scene to)
    {
        switch (gameData.DayCounter)
        {
        case 3:
            remove(day3_event);
        }
        super.onLeave(to);
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
