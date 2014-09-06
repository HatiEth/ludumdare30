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

    InteractionNode policemanDialog = null;

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
        InteractionNode interaction = new InteractionNode(ObjectState.IDLE,
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
                        policemanDialog.isDisabled = true;
                    }
                }, this, gameData, new TextObject("texts/amy_pond_0.png",
                        gameData.haraldGameObject, this, new Vector2(0, 350),
                        5.0f), new TextObject("texts/amy_pond_1.png",
                        gameData.haraldGameObject, this, new Vector2(0, 350),
                        5.0f)));
        interaction.isRepeatable = true;
        interaction.isDisabled = true;
        policemanDialog = interaction;
        policeman.setPosition(-304, -67);

        pathEngine.addLeaf(interaction);

    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);

        switch (gameData.DayCounter)
        {
        case 4:
            if (lastVisistedDay == 3)
            {
                policemanDialog.isDisabled = false;
                addObject(policeman);
            }
            break;
        case 6:
            if (lastVisistedDay == 6) policemanDialog.isDisabled = false;
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
        case 4:
            remove(policeman);
            break;
        case 6:
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
