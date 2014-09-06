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
import com.ludumdare.codebase.interactions.TextInteractionEvent;

public class Haltestelle1 extends Scene
{

    BackgroundGO background;
    BackgroundGO sign;
    PolizistGO policeman;

    InteractionNode policemanDialogDay3 = null;
    InteractionNode policemanDialogDay4 = null;

    public Haltestelle1(final GameData gameData)
    {
        super(gameData);

        sign = new BackgroundGO("background/bahn_schild.png");
        sign.setLayer(gameData.CH_LAYER + 0.5f);
        sign.setPosition(614, -16);
        addObject(sign);

        background = new BackgroundGO("background/haltestelle02.png");
        addObject(background);

        policeman = new PolizistGO();
        InteractionNode interactionDay3 = new InteractionNode(ObjectState.IDLE,
                Direction.Left, new Vector2(-214, -292), 0.0f, -304, -67, 67,
                285 * 0.5f, new DialogInteractionEvent(new OnDialogStartEvent()
                {
                    @Override
                    public void start()
                    {
                        gameData.toldPolicemanAboutEinbruch = true;
                    }

                    @Override
                    public void end()
                    {
                        policemanDialogDay3.isDisabled = true;
                    }
                }, this, gameData, new TextObject(
                        "sprechblasen/2.Haltestelle/03.01_Polizist.png",
                        gameData.haraldGameObject, this, new Vector2(0, 520),
                        5.0f), new TextObject(
                        "sprechblasen/2.Haltestelle/03.02_Polizist.png",
                        gameData.haraldGameObject, this, new Vector2(0, 520),
                        5.0f)));
        interactionDay3.isRepeatable = true;
        interactionDay3.isDisabled = true;
        policemanDialogDay3 = interactionDay3;

        // ----------------------------------
        InteractionNode interactionDay4 = new InteractionNode(ObjectState.IDLE,
                Direction.Left, new Vector2(-214, -292), 0.0f, -304, -67, 67,
                285 * 0.5f, new DialogInteractionEvent(new OnDialogStartEvent()
                {
                    @Override
                    public void start()
                    {
                        gameData.toldPoliceManAboutBag = true;
                    }

                    @Override
                    public void end()
                    {
                        policemanDialogDay4.isDisabled = true;
                    }
                }, this, gameData, new TextObject(
                        "sprechblasen/2.Haltestelle/04.01_Polizist.png",
                        gameData.haraldGameObject, this, new Vector2(0, 520),
                        5.0f), new TextObject(
                        "sprechblasen/2.Haltestelle/04.02_Polizist.png",
                        gameData.haraldGameObject, this, new Vector2(0, 520),
                        5.0f)));
        interactionDay4.isRepeatable = true;
        interactionDay4.isDisabled = true;
        policemanDialogDay4 = interactionDay4;

        policeman.setPosition(-304, -67);

        pathEngine.addLeaf(interactionDay3);
        pathEngine.addLeaf(interactionDay4);

    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);

        switch (gameData.DayCounter)
        {
        case 3:
            policemanDialogDay3.isDisabled = false;
            addObject(policeman);
            break;
        case 4:
            policemanDialogDay4.isDisabled = false;
            addObject(policeman);
            break;
        }

        if (from instanceof InFrontOfRetirementCenterScene)
        {
            gameData.haraldGameObject.getPosition().set(860, -320);
        }
        else
        {
            gameData.haraldGameObject.getPosition().set(440, -322);
            gameData.haraldGameObject.setDirection(Direction.Left);
        }
    }

    @Override
    public void onLeave(Scene to)
    {
        switch (gameData.DayCounter)
        {
        case 3:
            remove(policeman);
            break;
        case 4:
            remove(policeman);
            break;
        }
        super.onLeave(to);
    }

    @Override
    public void create()
    {
        PathNode rejectZone = new PathNode(new Vector2(0, 150), 0, 0, 184, 964,
                380, true);
        rejectZone.isReject = true;
        pathEngine.addLeaf(rejectZone);

        rejectZone = new PathNode(new Vector2(0, -484), 0, 0, -484, 960, 100,
                true);
        rejectZone.isReject = true;
        pathEngine.addLeaf(rejectZone);
    }

}
