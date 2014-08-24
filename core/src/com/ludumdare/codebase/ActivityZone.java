package com.ludumdare.codebase;

import com.badlogic.gdx.math.Rectangle;

@SuppressWarnings("serial")
public class ActivityZone extends Rectangle
{

    private boolean isActive;

    public ActivityZone()
    {
        super();
        isActive = false;
    }

    public ActivityZone(float x, float y, float width, float height)
    {
        super(x, y, width, height);
    }

    public ActivityZone(Rectangle rect)
    {
        super(rect);
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
