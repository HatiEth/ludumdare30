package com.ludumdare.codebase;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.gameobjects.GameObject;

/*
 * Describes a single node having a single precedessor and ancessor
 */
public class PathNode
{
    PathNode next = null;
    PathNode prev = null;

    Vector2 worldPosition;
    float layer;

    public PathNode(Vector2 position, float layer)
    {
        this.worldPosition = position;
        this.layer = layer;
    }

    public void setNext(PathNode next)
    {
        this.next = next;
    }

    public void setPrev(PathNode prev)
    {
        this.prev = prev;
    }

    public PathNode getPrev()
    {
        return prev;
    }

    public PathNode getNext()
    {
        return next;
    }

    public void execute(GameObject o)
    {

    }
}
