package com.ludumdare.codebase;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.gameobjects.GameObject;

/*
 * Describes a single node having a single precedessor and ancessor
 */
public class PathNode
{
    PathNode next = null;
    PathNode[] prev = null;

    ActivityZone clickableArea;
    public boolean isRepeatable;

    Vector2 worldPosition;
    float layer;

    public PathNode(Vector2 position, float layer, float x, float y,
            float halfWidth, float halfHeight, boolean isRepeatable)
    {
        this.worldPosition = position;
        this.layer = layer;
        clickableArea = new ActivityZone(x - halfWidth, y - halfHeight,
                2 * halfWidth, 2 * halfHeight);

        prev = new PathNode[2];

        this.isRepeatable = isRepeatable;
    }

    public PathNode(Vector2 position, float layer, float x, float y,
            float halfWidth, float halfHeight)
    {
        this.worldPosition = position;
        this.layer = layer;
        clickableArea = new ActivityZone(x - halfWidth, y - halfHeight,
                2 * halfWidth, 2 * halfHeight);

        prev = new PathNode[2];

        this.isRepeatable = false;
    }

    public PathNode(Vector2 position, float layer, boolean isRepeatable)
    {
        this.worldPosition = position;
        this.layer = layer;
        prev = new PathNode[2];

        this.isRepeatable = isRepeatable;
    }

    public PathNode(Vector2 position, float layer)
    {
        this.worldPosition = position;
        this.layer = layer;
        prev = new PathNode[2];

        isRepeatable = false;
    }

    public boolean isSelected(float x, float y)
    {
        return clickableArea != null && clickableArea.contains(x, y);
    }

    public boolean isSelected(Vector2 p)
    {
        return clickableArea != null && clickableArea.contains(p);
    }

    public void setNext(PathNode next)
    {
        this.next = next;
    }

    public void setPrev(PathNode prev, int index)
    {
        this.prev[index] = prev;
        prev.setNext(this);
    }

    public PathNode getPrev(int index)
    {
        return prev[index];
    }

    public PathNode getNext()
    {
        return next;
    }

    public void execute(GameObject o)
    {
        System.out.println("Firing pathnode " + this);
        o.enterState(ObjectState.IDLE);
    }
}
