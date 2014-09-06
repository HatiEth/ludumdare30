package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.math.MathUtils;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.Renderer;

public class HeadGO extends SpriteObject
{
    float offsetX = 0;
    float offsetY = 0;
    float timedMovement = 0.0f;
    float xSpeed;
    float ySpeed;
    float xAmpl;
    float yAmpl;

    public HeadGO(float offsetx, float offsety, float xSpeed, float ySpeed,
            float xAmpl, float yAmpl)
    {
        super("Charaktere/dreieck.png");
        // super("pyramid_sheet.png", 0.1f, 6, 1);
        // BetterAnimation walkAnimation =
        // BetterAnimation.createAnimation("pyramid_sheet.png", , frameRows,
        // animationTime, isLooped)
        // this.addAnimation(ObjectState.IDLE, Direction.Left, animation)
        this.offsetX = offsetx;
        this.offsetY = offsety;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.xAmpl = xAmpl;
        this.yAmpl = yAmpl;
    }

    @Override
    public void update()
    {
        timedMovement = timedMovement + 1.0f;

        // super.update();
        // this.position.y = startPositionX
        // + MathUtils.sinDeg(stateTime * 6.0f * 15.0f) * 12.0f;

    }

    @Override
    public void render(Renderer renderer)
    {
        renderer.drawStaticSprite(spriteHandle,
                position.x + MathUtils.cosDeg(timedMovement * xSpeed) * xAmpl,
                position.y + offsetY + MathUtils.sinDeg(timedMovement * ySpeed)
                        * yAmpl, layer, direction);
    }
}
