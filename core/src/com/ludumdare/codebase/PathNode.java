package com.ludumdare.codebase;

import com.badlogic.gdx.math.Vector2;

/*
 * Describes a single node having a single precedessor and ancessor
 */
public class PathNode
{
    PathNode next = null;
    PathNode prev = null;

    Vector2 worldPosition;

    public PathNode()
    {
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
}
