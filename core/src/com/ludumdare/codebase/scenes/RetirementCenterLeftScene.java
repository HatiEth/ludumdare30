package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.GameMode;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.gameobjects.BackgroundGO;

public class RetirementCenterLeftScene extends Scene
{

    BackgroundGO background;

    public RetirementCenterLeftScene(GameData gameData)
    {
        super(gameData);
        background = new BackgroundGO("background/Altenheim_Eingang.png");
        addObject(background);
    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        tasksCompleteRegistered = false;
        if (from instanceof RetirementCenterRightScene)
        {
            gameData.haraldGameObject.getPosition().set(960 - 32, -280);
        }
        else
        {
            gameData.haraldGameObject.getPosition().set(-782, -329);
        }
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
                pathEngine.setTargetPosition(new Vector2(-1200, -400));
                // add event handling here
                gameData.DayCounter = gameData.DayCounter + 1;
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

        PathNode rejectZone = new PathNode(new Vector2(0, 150), 0, 0, 150, 960,
                400, true);
        rejectZone.isReject = true;
        pathEngine.addLeaf(rejectZone);
    }

}
