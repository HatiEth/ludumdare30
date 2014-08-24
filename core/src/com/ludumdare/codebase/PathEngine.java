package com.ludumdare.codebase;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ludumdare.codebase.gameobjects.GameObject;

/**
 * Controller for GameObject and PathNodes
 * 
 * @author Hati Eth
 *
 */
public class PathEngine
{
    Array<PathNode> pathNodes;
    GameObject gameObject;
    Vector2 targetPosition;

    public PathEngine()
    {
        pathNodes = new Array<PathNode>();

        gameObject = null;
        targetPosition = null;
    }

    public void setGameObject(GameObject o)
    {
        gameObject = o;
    }

    public void setTargetPosition(Vector2 targetPosition)
    {
        this.targetPosition = targetPosition;
    }

    public void update()
    {
        if (gameObject != null && targetPosition != null)
        {
            Vector2 p = gameObject.getPosition();

            p = p.lerp(targetPosition, 0.016f);
        }
    }

}
