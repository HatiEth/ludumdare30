package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.math.MathUtils;
import com.ludumdare.codebase.ObjectState;

public class HeadGO extends AnimatedSpriteObject
{
    float startPositionX = 220;

    public HeadGO()
    {
        // super("pyramid_sheet.png", 0.1f, 6, 1);
        // BetterAnimation walkAnimation =
        // BetterAnimation.createAnimation("pyramid_sheet.png", , frameRows,
        // animationTime, isLooped)
        // this.addAnimation(ObjectState.IDLE, Direction.Left, animation)
        this.position.y = startPositionX;
        this.position.x = 10;

        stateTime = MathUtils.random() * 1024.0f;
    }

    @Override
    public void update()
    {
        // super.update();
        // this.position.y = startPositionX
        // + MathUtils.sinDeg(stateTime * 6.0f * 15.0f) * 12.0f;

    }
}
