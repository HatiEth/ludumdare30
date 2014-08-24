package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.InteractionNode;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.GameObject;
import com.ludumdare.codebase.gameobjects.HaraldGO;

public class SleepingRoomScene extends Scene
{
    BackgroundGO background;
    BackgroundGO foreground;

    public SleepingRoomScene(GameData gameData)
    {
        super(gameData);

        background = new BackgroundGO("Schlafzimmer.png");
        foreground = new BackgroundGO("Schlafzimmer_Light.png");
        addObject(background);

        foreground.setLayer(10.0f);
        addObject(foreground);

        pathEngine.addLeaf(new InteractionNode(ObjectState.IDLE,
                GameObject.Direction.Up, new Vector2(-405, -350), 0.0f, -415,
                120, 114, 134));
    }

    @Override
    public void update()
    {
        super.update();

    }

    @Override
    public void onEnter(Scene from)
    {
        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.setPosition(0, 0);
        if (from == null) // starting
        {
            gameData.haraldGameObject.setPosition(-502, -296);
        }
    }
}
