package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.Renderer;

public class HomeSceneGroup extends SceneGroup
{
    protected SleepingRoomScene sleepRoom;
    protected FloorScene floor;
    protected KitchenScene kitchen;

    final TransitZone sleepRoomKitchenTransit;
    // final TransitZone kitchenSleepRoomTransit;
    // final TransitZone kitchenFloorTransit;
    // final TransitZone floorSleepRoomTransit;

    ShapeRenderer sr;

    public HomeSceneGroup(GameData gameData)
    {
        super(gameData);
        sleepRoom = new SleepingRoomScene(gameData);
        kitchen = new KitchenScene(gameData);
        floor = new FloorScene(gameData);

        sleepRoomKitchenTransit = addTransit(sleepRoom, kitchen,
                sleepRoom.pathEngine.createNode(960 - 32, 0, 0), new Rectangle(
                        960 - 64, -540, 64, 1080));
        sleepRoomKitchenTransit.activate();

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

    }

    @Override
    public void render(Renderer renderer)
    {
        super.render(renderer);
        renderer.renderAll();

        debugDrawTransitZones(renderer);

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
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer,
                    int button)
            {
                activeScene.pathEngine.setTargetPosition(gameData.cameraControl
                        .screenToWorld(screenX, screenY));

                return false;
            }

            @Override
            public boolean scrolled(int amount)
            {
                // TODO Auto-generated method stub
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
                // TODO Auto-generated method stub
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
