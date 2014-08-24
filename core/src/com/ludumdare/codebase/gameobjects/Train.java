package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.Gdx;

public class Train extends SpriteObject
{

    float startX, endX;

    public Train(float startX, float endX)
    {
        super("traindebug.png");
        this.position.x = startX;
        this.endX = endX;
    }

    @Override
    public void update()
    {
        position.x = position.x + (endX - position.x)
                * Gdx.graphics.getDeltaTime();
    }

}
