package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.Renderer;

public abstract class GameObject
{
    Vector2 position;

    public GameObject()
    {
        position = new Vector2();
    }

    public abstract void update();

    public abstract void render(Renderer renderer);
}
