package com.ludumdare.codebase;

import com.badlogic.gdx.math.Rectangle;

@SuppressWarnings("serial")
public class ActivityZone extends Rectangle
{

    private boolean isActive;
    private final PathNode pathNode;
    private Rectangle rect;

    public ActivityZone(PathNode pathNode)
    {
        super();
        isActive = false;
        this.pathNode = pathNode;
    }

    public ActivityZone(PathNode pathNode, float x, float y, float width,
            float height)
    {
        super(x, y, width, height);
        this.pathNode = pathNode;
    }

    public ActivityZone(PathNode pathNode, Rectangle rect)
    {
        super(rect);
        this.pathNode = pathNode;
    }

    public void activate()
    {
        isActive = true;
    }

    public void deactivate()
    {
        isActive = false;
    }

    public boolean isActivate()
    {
        return isActive;
    }

}
