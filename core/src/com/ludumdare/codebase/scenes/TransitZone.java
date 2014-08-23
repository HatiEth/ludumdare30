package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TransitZone extends Rectangle
{
    Scene from;
    Scene to;

    public TransitZone(Scene from, Scene to, Rectangle zone)
    {
        super(zone);

        this.from = from;
        this.to = to;
    }

    public boolean checkTransit(Vector2 p)
    {
        return this.contains(p);
    }
}
