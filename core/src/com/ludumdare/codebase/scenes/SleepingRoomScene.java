package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.InteractionNode;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.GameObject;

public class SleepingRoomScene extends Scene
{
    BackgroundGO background;
    BackgroundGO foregroundLight;

    public SleepingRoomScene(GameData gameData)
    {
        super(gameData);

        background = new BackgroundGO("Schlafzimmer.png");
        foregroundLight = new BackgroundGO("Schlafzimmer_Light.png");
        addObject(background);

        foregroundLight.setLayer(10.0f);
        addObject(foregroundLight);

        addObject(gameData.diebGameObject);
        // pathEngine.addLeaf(n);

        addWindowInteraction();
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

    private void addWindowInteraction()
    {
        PathNode windowRight = new PathNode(new Vector2(-56, -416), 0);
        PathNode windowLeft = new PathNode(new Vector2(-560, -478), 0);

        InteractionNode window = new InteractionNode(ObjectState.IDLE,
                GameObject.Direction.Right, new Vector2(-405, -350), 0.0f,
                -415, 120, 114, 134);
        window.isRepeatable = true;

        window.setPrev(windowRight, 1);
        window.setPrev(windowLeft, 0);

        pathEngine.addLeaf(window);
    }
}
