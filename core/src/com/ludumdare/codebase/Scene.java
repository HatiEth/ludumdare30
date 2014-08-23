package com.ludumdare.codebase;

import com.badlogic.gdx.utils.Array;
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

    Array<GameObject> objects;

    public Scene()
    {
        objects = new Array<GameObject>(false, 128);
    }

    public void update()
    {
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
}
