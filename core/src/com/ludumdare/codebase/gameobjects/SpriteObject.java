package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.ludumdare.codebase.Renderer;

public abstract class SpriteObject extends GameObject
{
    protected Texture spriteHandle;
    protected float layer;

    public SpriteObject(String spriteFilePath)
    {
        spriteHandle = new Texture(Gdx.files.internal(spriteFilePath));
        spriteHandle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    @Override
    public void render(Renderer renderer)
    {
        renderer.drawSprite(spriteHandle, position.x, position.y, layer);
    }

}
