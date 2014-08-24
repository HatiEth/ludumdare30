package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.ActivityZone;
import com.ludumdare.codebase.PathNode;

public class TransitZone extends ActivityZone
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
