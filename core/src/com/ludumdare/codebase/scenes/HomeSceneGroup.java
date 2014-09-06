package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
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
    CreditsScene credits;

    ShapeRenderer sr;

    Array<TransitNode> allTransitNodes;

    public HomeSceneGroup(GameData gameData)
    {
        super(gameData);

        allTransitNodes = new Array<TransitNode>();
        gameData.sceneGroup = this;

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
        retirementCenterRight = new RetirementCenterRightScene(gameData);
        credits = new CreditsScene(gameData);

        TransitNode tn;

        // / Sleep room
        sleepRoom.pathEngine.addLeaf(tn = new TransitNode(this, sleepRoom,
                kitchen, new Vector2(960 - 32, -350), 0.0f, 960 - 32, -380, 64,
                258));
        allTransitNodes.add(tn);

        // Kitchen
        kitchen.pathEngine.addLeaf(tn = new TransitNode(this, kitchen,
                sleepRoom, new Vector2(-960 + 32, -350), 0.0f, -960 + 32, -380,
                64, 258));
        allTransitNodes.add(tn);
        kitchen.pathEngine.addLeaf(tn = new TransitNode(this, kitchen, floor,
                new Vector2(960 - 32, -350), 0.0f, 960 - 32, -380, 64, 258));
        allTransitNodes.add(tn);

        // Floor
        floor.pathEngine.addLeaf(tn = new TransitNode(this, floor, kitchen,
                new Vector2(-960 + 32, -350), 0.0f, -960 + 32, -380, 64, 258));
        allTransitNodes.add(tn);
        floor.pathEngine.addLeaf(tn = new TransitNode(this, floor,
                inFrontOfHouse, new Vector2(960 - 32, -340), 0.0f, 960 - 32,
                -330, 64, 160));
        allTransitNodes.add(tn);

        // In from of haralds house
        inFrontOfHouse.pathEngine.addLeaf(tn = new TransitNode(this,
                inFrontOfHouse, floor, new Vector2(-456, -224), 0.0f, -460, 42,
                100, 240));
        allTransitNodes.add(tn);
        inFrontOfHouse.pathEngine.addLeaf(tn = new TransitNode(this,
                inFrontOfHouse, haltestelle0, new Vector2(960 - 32, -280),
                0.0f, 960 - 32, -330, 64, 500));
        allTransitNodes.add(tn);

        // Haltestelle haralds seite
        haltestelle0.pathEngine.addLeaf(tn = new TransitNode(this,
                haltestelle0, inFrontOfHouse, new Vector2(-960 + 32, -280),
                0.0f, -960 + 32, -330, 64, 164));
        allTransitNodes.add(tn);

        haltestelle0.pathEngine.addLeaf(tn = new TransitNode(this,
                haltestelle0, trainScene, new Vector2(0, -484), 0, 0, -484,
                960, 100));
        allTransitNodes.add(tn);

        // zug szene
        trainScene.pathEngine.addLeaf(tn = new TransitNode(this, trainScene,
                haltestelle1, new Vector2(0, -256), 0.0f, 0, 0, 256, 256));
        allTransitNodes.add(tn);

        // haltestelle center seite
        haltestelle1.pathEngine.addLeaf(tn = new TransitNode(this,
                haltestelle1, frontOfRetirementCenterScene, new Vector2(
                        960 - 32, -280), 0.0f, 960 - 32, -330, 64, 500));
        allTransitNodes.add(tn);

        // in front of the retirement center
        frontOfRetirementCenterScene.pathEngine.addLeaf(tn = new TransitNode(
                this, frontOfRetirementCenterScene, haltestelle1, new Vector2(
                        -960 + 32, -280), 0.0f, -960 + 32, -330, 64, 164));
        allTransitNodes.add(tn);
        frontOfRetirementCenterScene.pathEngine.addLeaf(tn = new TransitNode(
                this, frontOfRetirementCenterScene, retirementCenterLeft,
                new Vector2(282, -246), 0, 272, -20, 212, 208));
        allTransitNodes.add(tn);
        retirementCenterLeft.pathEngine.addLeaf(tn = new TransitNode(this,
                retirementCenterLeft, retirementCenterRight, new Vector2(
                        960 - 32, -280), 0.0f, 960 - 32, -330, 64, 500));
        allTransitNodes.add(tn);

        // retirement center parts
        retirementCenterLeft.pathEngine.addLeaf(tn = new TransitNode(this,
                retirementCenterLeft, retirementCenterRight, new Vector2(
                        960 - 32, -280), 0.0f, 960 - 32, -330, 64, 500));
        allTransitNodes.add(tn);
        retirementCenterRight.pathEngine.addLeaf(tn = new TransitNode(this,
                retirementCenterRight, retirementCenterLeft, new Vector2(
                        -960 + 32, -280), 0.0f, -960 + 32, -330, 64, 500));
        allTransitNodes.add(tn);
        retirementCenterLeft.pathEngine.addLeaf(tn = new TransitNode(this,
                retirementCenterLeft, sleepRoom, new Vector2(-1200, -400), 0,
                -1200, -400, 64, 64));
        allTransitNodes.add(tn);

        // sleepRoomKitchenTransit.activate();

        sleepRoom.create();
        kitchen.create();
        floor.create();
        inFrontOfHouse.create();
        haltestelle0.create();
        trainScene.create();
        haltestelle1.create();
        frontOfRetirementCenterScene.create();
        retirementCenterLeft.create();
        retirementCenterRight.create();
        credits.create();
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
        trainScene.addObject(gameData.haraldGameObject);
        haltestelle1.addObject(gameData.haraldGameObject);
        frontOfRetirementCenterScene.addObject(gameData.haraldGameObject);
        retirementCenterLeft.addObject(gameData.haraldGameObject);
        retirementCenterRight.addObject(gameData.haraldGameObject);
        credits.addObject(gameData.haraldGameObject);
    }

    @Override
    public void render(Renderer renderer)
    {
        super.render(renderer);
        renderer.renderAll();
        if (gameData.isDevMode)
        {
            activeScene.pathEngine.debugDraw(renderer);
        }
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
                case Talk:
                    gameData.activeDialog.fire(gameData.haraldGameObject);
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

    @Override
    public void create()
    {
        // TODO Auto-generated method stub

    }

    public void deactivateAllTransits()
    {
        for (TransitNode n : allTransitNodes)
        {
            n.isDisabled = true;
        }
    }

    public void activateAllTransits()
    {
        for (TransitNode n : allTransitNodes)
        {
            n.isDisabled = false;
        }
    }
}
