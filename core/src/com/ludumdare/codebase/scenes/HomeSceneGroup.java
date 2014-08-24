package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.Renderer;
import com.ludumdare.codebase.gameobjects.HaraldGO;

public class HomeSceneGroup extends SceneGroup
{
    protected SleepingRoomScene sleepRoom;
    protected FloorScene floor;
    protected KitchenScene kitchen;

    ShapeRenderer sr;

    public HomeSceneGroup(GameData gameData)
    {
        super(gameData);
        sleepRoom = new SleepingRoomScene(gameData);
        kitchen = new KitchenScene(gameData);
        floor = new FloorScene(gameData);

        setActiveScene(sleepRoom);

        addTransit(sleepRoom, kitchen, new Rectangle(960 - 64, -540, 64, 1080));

        addTransit(kitchen, sleepRoom, new Rectangle(-960, -540, 64, 1080));
        addTransit(kitchen, floor, new Rectangle(960 - 64, -540, 64, 1080));

        addTransit(floor, sleepRoom, new Rectangle(960 - 64, -540, 64, 1080));

        sr = new ShapeRenderer();

        gameData.haraldGameObject.setPosition(0, 0);
        sleepRoom.pathEngine.setGameObject(gameData.haraldGameObject);
        sleepRoom.addObject(gameData.haraldGameObject);

    }

    @Override
    public void render(Renderer renderer)
    {
        super.render(renderer);
        renderer.renderAll();

        ShapeRenderer sr = renderer.getShapeRenderer();
        sr.begin(ShapeType.Filled);
        for (TransitZone z : transitZones)
        {
            if (activeScene == z.from)
            {
                sr.rect(z.x, z.y, z.width, z.height, Color.BLUE, Color.BLUE,
                        Color.BLUE, Color.BLUE);
            }
        }

        TransitZone t = getTransitAt(Gdx.input.getX(), Gdx.input.getY());
        if (t != null)
        {
            sr.rect(t.x, t.y, t.width, t.height, Color.GREEN, Color.GREEN,
                    Color.GREEN, Color.GREEN);
        }
        sr.end();
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
}
