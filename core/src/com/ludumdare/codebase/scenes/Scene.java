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

    protected int dayCounter = 0;

    Array<GameObject> objects;
    Array<ActivityZone> activityZones;
    protected PathEngine pathEngine;
    protected final GameData gameData;

    public Scene(GameData gameData)
    {
        this.gameData = gameData;
        pathEngine = new PathEngine();
        objects = new Array<GameObject>(false, 128);
        activityZones = new Array<ActivityZone>(false, 128);
    }

    public void update()
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
        objects.add(o);
    }

    public void addObject(GameObject o, float x, float y)
    {
        objects.add(o);
        o.setPosition(x, y);
    }

    public abstract void onEnter(Scene from);
}
