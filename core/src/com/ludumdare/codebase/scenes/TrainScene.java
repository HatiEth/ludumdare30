package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.GameMode;
import com.ludumdare.codebase.InteractionEvent;
import com.ludumdare.codebase.InteractionNode;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.PathEngine;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.Renderer;
import com.ludumdare.codebase.gameobjects.BackgroundGO;
import com.ludumdare.codebase.gameobjects.GameObject;
import com.ludumdare.codebase.gameobjects.StolenManGO;
import com.ludumdare.codebase.gameobjects.GameObject.Direction;

public class TrainScene extends Scene
{
    PathEngine diebPathEngine;

    BackgroundGO background;
    BackgroundGO effect;
    TascheGO tasche;
    StolenManGO stolenMan;
    RuepelGO ruepel;

    class Blubb extends InteractionEvent
    {
        public Blubb(Scene scene, GameData gameData)
        {
            super(scene, gameData);
        }

        float timer = 0.0f;
        float timerAction0 = 1.3f;
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
            if (timer > timerAction0)
            {
                remove(tasche);
            }
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

        background = new BackgroundGO("background/Bahn.png");
        addObject(background);

        effect = new BackgroundGO("effects/cinematic_effect.png");
        effect.setLayer(15.0f);

        diebPathEngine = new PathEngine();

        diebPathEngine.addLeaf(new InteractionNode(ObjectState.TAKE,
                Direction.Down, new Vector2(564, -134), 0, 0, 0, 16, 16,
                new Blubb(this, gameData)));

        this.addObject(gameData.diebGameObject);

        tasche = new TascheGO();
        tasche.setPosition(-78 + 560, -230);

        stolenMan = new StolenManGO();
        stolenMan.setLayer(gameData.CH_LAYER);

        ruepel = new RuepelGO();
        ruepel.setLayer(gameData.CH_LAYER);
        ruepel.setDirection(Direction.Right);
        ruepel.enterState(ObjectState.TAKE_SEAT);
        ruepel.setPosition(-800, -90);
        addObject(ruepel);

    }

    float drivingTime = 1.0f;
    float shake = 3.0f;

    @Override
    protected void updateInternal()
    {
        drivingTime = drivingTime - 0.016f;

        if (drivingTime < 0)
        {
            gameData.sceneGroup.activateAllTransits();
        }
        else
        {
            gameData.cameraControl.setPosition(
                    MathUtils.cosDeg(shake * 0.5f) * 2.0f,
                    MathUtils.sinDeg(shake) * 5.0f);
            shake = shake + 3.0f;
        }

        switch (gameData.DayCounter)
        {
        case 2:
            handleDay2();
            break;
        case 4:
            handleDay4();
            break;
        case 8:
            handleDay8();
            break;
        }

        diebPathEngine.update();
        super.updateInternal();
    }

    private void handleDay2()
    {

    }

    private void handleDay8()
    {
        gameData.diebGameObject.setStateTime(0.0f);
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
        drivingTime = 6.0f;
        diebPathEngine.setGameObject(gameData.diebGameObject);
        gameData.diebGameObject.getPosition().set(1200, 0);

        gameData.sceneGroup.deactivateAllTransits();

        switch (gameData.DayCounter)
        {
        case 4:
            gameData.eventMode = GameMode.Cinematic;
            diebPathEngine.setTargetPosition(new Vector2(0, 0));
            addObject(tasche);

            stolenMan.setPosition(414, -130);
            stolenMan.setDirection(Direction.Right);
            addObject(stolenMan);
            break;
        case 8:
            gameData.diebGameObject.getPosition().set(500, -200);
            gameData.diebGameObject.setDirection(Direction.Right);
            gameData.diebGameObject.enterState(ObjectState.IDLE);

        }

        pathEngine.setGameObject(gameData.haraldGameObject);
        gameData.haraldGameObject.getPosition().set(-470, -225);
        gameData.haraldGameObject.setDirection(Direction.Right);
        gameData.haraldGameObject.enterState(ObjectState.TAKE_SEAT);
    }

    @Override
    public void onLeave(Scene to)
    {
        gameData.cameraControl.setPosition(0, 0);
    }

    @Override
    public void create()
    {
        PathNode rejectZone = new PathNode(new Vector2(0, 150), 0, 0, 170, 960,
                390, true);
        rejectZone.isReject = true;
        pathEngine.addLeaf(rejectZone);
    }

}
