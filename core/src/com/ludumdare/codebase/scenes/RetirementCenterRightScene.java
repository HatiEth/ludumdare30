package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.GameMode;
import com.ludumdare.codebase.gameobjects.BackgroundGO;

public class RetirementCenterRightScene extends Scene
{

    BackgroundGO background;

    public RetirementCenterRightScene(GameData gameData)
    {
        super(gameData);

        background = new BackgroundGO(
                "background/Altenheim_Aufenthaltsraum.png");

        addObject(background);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.getPosition().set(-842, -366);
    }

    boolean tasksCompleteRegistered = false;

    @Override
    protected void updateInternal()
    {
        if (!tasksCompleteRegistered)
        {
            if (gameData.tasks.complete())
            {
                tasksCompleteRegistered = true;
                gameData.eventMode = GameMode.Cinematic;
                pathEngine.setTargetPosition(new Vector2(-960 + 32, -330));
                // add event handling here
            }
            else
            {
                super.updateInternal();
            }
        }
        else
        {
            pathEngine.update();
            gameData.haraldGameObject.update();
        }
    }

    @Override
    public void create()
    {

    }

}
