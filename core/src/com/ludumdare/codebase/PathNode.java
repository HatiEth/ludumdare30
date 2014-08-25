package com.ludumdare.codebase;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ludumdare.codebase.gameobjects.GameObject;

/*
 * Describes a single node having a single precedessor and ancessor
 */
public class PathNode
{
    PathNode next = null;
    Array<PathNode> prevs;

    ActivityZone clickableArea;
    public boolean isRepeatable;
    public boolean isReject;
    public boolean isDynamic;

    Vector2 worldPosition;
    float layer;

    public PathNode(Vector2 position, float layer, float x, float y,
            float halfWidth, float halfHeight, boolean isRepeatable)
    {
        this.worldPosition = position;
        this.layer = layer;
        clickableArea = new ActivityZone(x - halfWidth, y - halfHeight,
                2 * halfWidth, 2 * halfHeight);

        prevs = new Array<PathNode>();

        this.isRepeatable = isRepeatable;
        this.isReject = false;

    }

    public PathNode(Vector2 position, float layer, float x, float y,
            float halfWidth, float halfHeight)
    {
        this.worldPosition = position;
        this.layer = layer;
        clickableArea = new ActivityZone(x - halfWidth, y - halfHeight,
                2 * halfWidth, 2 * halfHeight);

        prevs = new Array<PathNode>();

        this.isRepeatable = false;
        this.isReject = false;
    }

    public PathNode(Vector2 position, float layer, boolean isRepeatable)
    {
        this.worldPosition = position;
        this.layer = layer;
        prevs = new Array<PathNode>();

        this.isRepeatable = isRepeatable;
        this.isReject = false;
    }

    public PathNode(Vector2 position, float layer)
    {
        this.worldPosition = position;
        this.layer = layer;
        prevs = new Array<PathNode>();

        isRepeatable = false;
        this.isReject = false;
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

    public void addPrev(PathNode prev)
    {
        this.prevs.add(prev);
        prev.setNext(this);
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
