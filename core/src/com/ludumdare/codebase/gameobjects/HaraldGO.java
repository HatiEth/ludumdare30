package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.Renderer;

public class HaraldGO extends AnimatedSpriteObject
{

    HeadGO head;
    GameObject feeter;

    public HaraldGO()
    {
        // super("walkcycletest.png", 0.5f, 5, 1);
        super("sprite_sheet_protagonist.png", 1.0f, 10, 1);
        head = new HeadGO();
        final GameObject attached = this;
        feeter = new GameObject()
        {

            @Override
            public void update()
            {
                attached.position.x = feeter.position.x;
                attached.position.y = feeter.position.y + 142.5f;
            }

            @Override
            public void render(Renderer renderer)
            {

            }
        };
    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        feeter.update();
        super.update();
        head.update();
    }

    @Override
    public void render(Renderer renderer)
    {
        super.render(renderer);
        // renderer.drawAnimatedSprite(
        // head.animation.getKeyFrame(head.stateTime, true),
        // this.position.x + head.position.x, this.position.y
        // + head.position.y, this.layer - 0.01f, this.direction);
    }

    @Override
    public void setPosition(float x, float y)
    {
        feeter.position.x = x;
        feeter.position.y = y;
    }

    @Override
    public Vector2 getPosition()
    {
        return feeter.getPosition();
    }
}
