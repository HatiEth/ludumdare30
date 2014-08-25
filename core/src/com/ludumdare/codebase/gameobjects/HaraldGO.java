package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.Renderer;

public class HaraldGO extends AnimatedSpriteObject
{

    HeadGO head;
    GameObject feeter;

    public HaraldGO()
    {
        BetterAnimation walkAnimation = BetterAnimation.createAnimation(
                "Charaktere/Protagonist/sprite_sheet_protagonist.png", 10, 1,
                0.15f, true);

        this.addAnimation(ObjectState.IDLE, Direction.Down, walkAnimation);
        this.addAnimation(ObjectState.IDLE, Direction.Up, walkAnimation);
        this.addAnimation(ObjectState.IDLE, Direction.Left, walkAnimation);
        this.addAnimation(ObjectState.IDLE, Direction.Right, walkAnimation);

        this.addAnimation(ObjectState.MOVE, Direction.Right, walkAnimation);
        this.addAnimation(ObjectState.MOVE, Direction.Left, walkAnimation);
        this.addAnimation(ObjectState.MOVE, Direction.Up, walkAnimation);
        this.addAnimation(ObjectState.MOVE, Direction.Down, walkAnimation);

        BetterAnimation sitAnimation = BetterAnimation.createAnimation(
                "Charaktere/Protagonist/sprite_sheet_sit down.png", 5, 1,
                0.11f, false);

        this.addAnimation(ObjectState.TAKE_SEAT, Direction.Right, sitAnimation);
        this.addAnimation(ObjectState.TAKE_SEAT, Direction.Left, sitAnimation);

        this.addAnimation(ObjectState.UNSEAT, Direction.Right, sitAnimation);
        this.addAnimation(ObjectState.UNSEAT, Direction.Left, sitAnimation);

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

            @Override
            public void setDirection(Direction direction)
            {
                super.setDirection(direction);
                attached.setDirection(direction);
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

    @Override
    public void move(float x, float y)
    {
        feeter.move(x, y);
    }

    @Override
    public void enterState(ObjectState state)
    {
        super.enterState(state);
        if (feeter != null) feeter.enterState(state);
    }
}
