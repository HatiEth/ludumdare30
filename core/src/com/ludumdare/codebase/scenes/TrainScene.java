package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.GameMode;
import com.ludumdare.codebase.InteractionEvent;
import com.ludumdare.codebase.InteractionNode;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.PathEngine;
import com.ludumdare.codebase.Renderer;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.DiebGO;
import com.ludumdare.codebase.gameobjects.GameObject;
import com.ludumdare.codebase.gameobjects.GameObject.Direction;

public class TrainScene extends Scene
{
    PathEngine diebPathEngine;

    DiebGO dieb;
    BackgroundGO effect;

    class Blubb extends InteractionEvent
    {
        public Blubb(Scene scene, GameData gameData)
        {
            super(scene, gameData);
        }

        float timer = 0.0f;
        float timerAction1 = 3f;
        float isActive;

        @Override
        public void fire(GameObject o)
        {
            dailyEvent = this;
        }

        public void update()
        {
            timer = timer + 0.016f;
            if (timer > timerAction1)
            {
                diebPathEngine.setMoveType(ObjectState.TALK);
                diebPathEngine.setTargetPosition(new Vector2(1200, 0));
                gameData.eventMode = GameMode.Exploration;
            }
        }
    };

    Blubb dailyEvent;

    public TrainScene(final GameData gameData)
    {
        super(gameData);

        effect = new BackgroundGO("effects/cinematic_effect.png");
        effect.setLayer(15.0f);

        diebPathEngine = new PathEngine();

        diebPathEngine.addLeaf(new InteractionNode(ObjectState.TAKE,
                Direction.Down, new Vector2(0, 0), 0, 0, 0, 16, 16, new Blubb(
                        this, gameData)));

        this.addObject(gameData.diebGameObject);
    }

    @Override
    protected void updateInternal()
    {

        switch (gameData.DayCounter)
        {
        case 1:
            handleDay4();
            break;
        }

        diebPathEngine.update();
        super.updateInternal();
    }

    private void handleDay4()
    {
        if (dailyEvent != null)
        {
            dailyEvent.update();
        }
    }

    @Override
    public void render(Renderer renderer)
    {
        if (gameData.eventMode == GameMode.Cinematic)
        {
            effect.render(renderer);
        }
        super.render(renderer);
    }

    @Override
    public void onEnter(Scene from)
    {
        diebPathEngine.setGameObject(gameData.diebGameObject);
        gameData.diebGameObject.getPosition().set(1200, 0);

        switch (gameData.DayCounter)
        {
        case 1:
            gameData.eventMode = GameMode.Cinematic;
            diebPathEngine.setTargetPosition(new Vector2(0, 0));
            break;

        }

        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.getPosition().set(-442, -220);
    }

    @Override
    public void create()
    {
        // TODO Auto-generated method stub

    }

}
