package com.ludumdare.codebase;

import com.ludumdare.codebase.gameobjects.GameObject;
import com.ludumdare.codebase.scenes.Scene;

public abstract class InteractionEvent
{
    protected final GameData data; // comfort
    protected final Scene scene; // scene it is happening in

    public InteractionEvent(Scene scene, GameData gameData)
    {
        this.data = gameData;
        this.scene = scene;
    }

    public abstract void fire(GameObject o);
}
