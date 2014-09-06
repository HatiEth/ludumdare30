package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.InteractionNode;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.TextObject;
import com.ludumdare.codebase.gameobjects.GameObject.Direction;
import com.ludumdare.codebase.interactions.DialogInteractionEvent;
import com.ludumdare.codebase.interactions.OnDialogStartEvent;

public class InFrontOfRetirementCenterScene extends Scene
{
    BackgroundGO background;

    GroupFightGO groupFight;
    YoshiGO drugBoy;
    InteractionNode drugBoyDialog = null;

    public InFrontOfRetirementCenterScene(final GameData gameData)
    {
        super(gameData);

        background = new BackgroundGO("background/altenheim.png");
        addObject(background);

        groupFight = new GroupFightGO();

        drugBoy = new YoshiGO();

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
                        drugBoyDialog.isDisabled = true;
                    }
                }, this, gameData, new TextObject("texts/amy_pond_0.png",
                        gameData.haraldGameObject, this, new Vector2(0, 350),
                        5.0f), new TextObject("texts/amy_pond_1.png",
                        gameData.haraldGameObject, this, new Vector2(0, 350),
                        5.0f)));
        interaction.isRepeatable = true;
        drugBoyDialog = interaction;
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.getPosition().set(-912, -254);

        switch (gameData.DayCounter)
        {
        case 6:
            if (lastVisistedDay == 4) // == 4 because skip 5
            {
                addObject(drugBoy);
            }
        case 7:
            if (lastVisistedDay == 6) addObject(groupFight);
            break;
        }
    }

    @Override
    public void create()
    {

    }

    @Override
    public void onLeave(Scene to)
    {

        switch (gameData.DayCounter)
        {
        case 6:
            remove(drugBoy);
        case 7:
            remove(groupFight);
            break;
        }
        super.onLeave(to);
    }

}
