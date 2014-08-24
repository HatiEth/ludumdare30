package com.ludumdare.codebase.gameobjects;

import com.ludumdare.codebase.Renderer;

public class HaraldGO extends AnimatedSpriteObject
{

    HeadGO head;

    public HaraldGO()
    {
        super("walkcycletest.png", 0.5f, 5, 1);
        head = new HeadGO();
    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        super.update();
        head.update();
    }

    @Override
    public void render(Renderer renderer)
    {
        super.render(renderer);
        renderer.drawAnimatedSprite(
                head.animation.getKeyFrame(head.stateTime, true),
                this.position.x + head.position.x, this.position.y
                        + head.position.y, this.layer - 0.01f, this.direction);
    }
}
