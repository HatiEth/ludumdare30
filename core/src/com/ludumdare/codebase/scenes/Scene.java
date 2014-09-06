package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.utils.Array;
import com.ludumdare.codebase.ActivityZone;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.PathEngine;
import com.ludumdare.codebase.Renderer;
import com.ludumdare.codebase.gameobjects.GameObject;

/**
 * Describes single scene in the game. Only a single scene can be active at a
 * given time
 * 
 * @author Hati Eth
 *
 */
public abstract class Scene
{
    static final float UPDATE_FREQUENCY = 0.016f;

    protected int lastVisistedDay = -1;

    Array<GameObject> objects;
    Array<ActivityZone> activityZones;

    Array<GameObject> removalQueue;
    Array<GameObject> insertQueue;

    protected PathEngine pathEngine;
    protected GameData gameData;

    public Scene(GameData gameData)
    {
        this.gameData = gameData;
        pathEngine = new PathEngine();
        objects = new Array<GameObject>(false, 128);
        activityZones = new Array<ActivityZone>(false, 128);

        removalQueue = new Array<GameObject>();
        insertQueue = new Array<GameObject>();
    }

    public void update()
    {
        for (GameObject o : removalQueue)
        {
            objects.removeValue(o, true);
        }

        for (GameObject o : insertQueue)
        {
            objects.add(o);
        }

        insertQueue.clear();
        removalQueue.clear();

        updateInternal();
    }

    protected void updateInternal()
    {

        pathEngine.update();

        for (GameObject o : objects)
        {
            o.update();
        }
    }

    public void render(Renderer renderer)
    {
        for (GameObject o : objects)
        {
            o.render(renderer);
        }

    }

    public void addObject(GameObject o)
    {
        insertQueue.add(o);
    }

    public void addObject(GameObject o, float x, float y)
    {
        insertQueue.add(o);
        o.setPosition(x, y);
    }

    public void remove(GameObject o)
    {
        objects.removeValue(o, true);
    }

    public abstract void onEnter(Scene from);

    public void onLeave(Scene to)
    {
        lastVisistedDay = gameData.DayCounter;
    }

    public abstract void create();

}
