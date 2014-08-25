package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.Renderer;

public class HomeSceneGroup extends SceneGroup
{
    protected SleepingRoomScene sleepRoom;
    protected KitchenScene kitchen;
    protected FloorScene floor;
    protected FrontOfHouseScene inFrontOfHouse;
    protected Haltestelle0 haltestelle0;
    protected TrainScene trainScene;
    protected Haltestelle1 haltestelle1;
    protected InFrontOfRetirementCenterScene frontOfRetirementCenterScene;
    protected RetirementCenterLeftScene retirementCenterLeft;
    protected RetirementCenterRightScene retirementCenterRight;

    ShapeRenderer sr;

    public HomeSceneGroup(GameData gameData)
    {
        super(gameData);
        sleepRoom = new SleepingRoomScene(gameData);
        kitchen = new KitchenScene(gameData);
        floor = new FloorScene(gameData);
        inFrontOfHouse = new FrontOfHouseScene(gameData);
        haltestelle0 = new Haltestelle0(gameData);
        trainScene = new TrainScene(gameData);
        haltestelle1 = new Haltestelle1(gameData);
        frontOfRetirementCenterScene = new InFrontOfRetirementCenterScene(
                gameData);
        retirementCenterLeft = new RetirementCenterLeftScene(gameData);

        // sleepRoomKitchenTransit = addTransit(sleepRoom, kitchen,
        // new TransitNode(sleepRoom.pathEngine, 960 - 32, 0),
        // new Rectangle(960 - 64, -540, 64, 1080));
        sleepRoom.pathEngine.addLeaf(new TransitNode(this, sleepRoom, kitchen,
                new Vector2(960 - 32, -350), 0.0f, 960 - 32, -380, 64, 164));

        kitchen.pathEngine.addLeaf(new TransitNode(this, kitchen, sleepRoom,
                new Vector2(-960 + 32, -350), 0.0f, -960 + 32, -380, 64, 164));
        kitchen.pathEngine.addLeaf(new TransitNode(this, kitchen, floor,
                new Vector2(960 - 32, -350), 0.0f, 960 - 32, -380, 64, 164));

        floor.pathEngine.addLeaf(new TransitNode(this, floor, kitchen,
                new Vector2(-960 + 32, -350), 0.0f, -960 + 32, -380, 64, 164));
        floor.pathEngine.addLeaf(new TransitNode(this, floor, inFrontOfHouse,
                new Vector2(960 - 32, -340), 0.0f, 960 - 32, -330, 64, 80));

        inFrontOfHouse.pathEngine.addLeaf(new TransitNode(this, inFrontOfHouse,
                floor, new Vector2(-960 + 32, -340), 0.0f, -960 + 32, -330, 64,
                80));
        inFrontOfHouse.pathEngine.addLeaf(new TransitNode(this, inFrontOfHouse,
                haltestelle0, new Vector2(960 - 32, -280), 0.0f, 960 - 32,
                -330, 64, 164));

        haltestelle0.pathEngine.addLeaf(new TransitNode(this, haltestelle0,
                inFrontOfHouse, new Vector2(-960 + 32, -280), 0.0f, -960 + 32,
                -330, 64, 164));
        haltestelle0.pathEngine.addLeaf(new TransitNode(this, haltestelle0,
                trainScene, new Vector2(0, 0), 0, 0, 0, 64, 64));

        trainScene.pathEngine.addLeaf(new TransitNode(this, trainScene,
                haltestelle1, new Vector2(0, 0), 0.0f, 0, 0, 64, 64));

        haltestelle1.pathEngine.addLeaf(new TransitNode(this, haltestelle1,
                frontOfRetirementCenterScene, new Vector2(0, 0), 0.0f, 0, 0,
                64, 64));

        // sleepRoomKitchenTransit.activate();

        // kitchenSleepRoomTransit = addTransit(kitchen, sleepRoom, new
        // Rectangle(
        // -960, -540, 64, 1080));
        // kitchenSleepRoomTransit.activate();
        //
        // kitchenFloorTransit = addTransit(kitchen, floor, new Rectangle(
        // 960 - 64, -540, 64, 1080));
        // kitchenFloorTransit.activate();
        //
        // floorSleepRoomTransit = addTransit(floor, sleepRoom, new Rectangle(
        // 960 - 64, -540, 64, 1080));
        // floorSleepRoomTransit.activate();

        setActiveScene(sleepRoom);
        sr = new ShapeRenderer();

        sleepRoom.addObject(gameData.haraldGameObject);
        kitchen.addObject(gameData.haraldGameObject);
        floor.addObject(gameData.haraldGameObject);
        inFrontOfHouse.addObject(gameData.haraldGameObject);
        haltestelle0.addObject(gameData.haraldGameObject);
        haltestelle1.addObject(gameData.haraldGameObject);
    }

    @Override
    public void render(Renderer renderer)
    {
        super.render(renderer);
        renderer.renderAll();

        activeScene.pathEngine.debugDraw(renderer);
    }

    @Override
    public void update()
    {
        super.update();

        Gdx.input.setInputProcessor(new InputProcessor()
        {

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer,
                    int button)
            {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer)
            {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer,
                    int button)
            {

                switch (gameData.eventMode)
                {
                case Exploration:
                    System.out.println(gameData.cameraControl.screenToWorld(
                            screenX, screenY));
                    activeScene.pathEngine
                            .setTargetPosition(gameData.cameraControl
                                    .screenToWorld(screenX, screenY));
                    break;
                default:
                    break;
                }
                return false;
            }

            @Override
            public boolean scrolled(int amount)
            {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY)
            {
                return false;
            }

            @Override
            public boolean keyUp(int keycode)
            {
                return false;
            }

            @Override
            public boolean keyTyped(char character)
            {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean keyDown(int keycode)
            {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    @Override
    public void onEnter(Scene from)
    {

    }
}
