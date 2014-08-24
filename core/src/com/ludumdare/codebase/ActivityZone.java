package com.ludumdare.codebase;

import com.badlogic.gdx.math.Rectangle;

@SuppressWarnings("serial")
public class ActivityZone extends Rectangle
{

    public ActivityZone()
    {
        super();
    }

    public ActivityZone(float x, float y, float width, float height)
    {
        super(x, y, width, height);
    }

    public ActivityZone(Rectangle rect)
    {
        super(rect);
    }

}
