package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.Renderer;

public abstract class GameObject
{
    Vector2 position;
    protected float layer;

    float stateTime;
    ObjectState transitToState;
    public ObjectState objectState;

    public enum Direction
    {
        Up, Left, Down, Right, NoChange
    };

    protected Direction direction;

    public GameObject()
    {
        position = new Vector2();
        direction = Direction.Right;

        enterState(ObjectState.IDLE);
    }

    public abstract void update();

    public abstract void render(Renderer renderer);

    public void setPosition(float x, float y)
    {
        this.position.x = x;
        this.position.y = y;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setLayer(float layer)
    {
        this.layer = layer;
    }

    public float getLayer()
    {
        return layer;
    }

    public void setDirection(Direction direction)
    {
        if (direction != Direction.NoChange)
        {
            this.direction = direction;
        }
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void enterState(ObjectState state)
    {
        if (objectState == state) return;

        if (objectState == ObjectState.TAKE_SEAT
                && state != ObjectState.TAKE_SEAT)
        {
            transitToState = state;
            stateTime = MathUtils.clamp(stateTime, 0, 0.4f);
            objectState = ObjectState.UNSEAT;
            return;
        }

        objectState = state;
        stateTime = 0.0f;
    }

    public void move(float x, float y)
    {
        if (objectState == objectState.UNSEAT)
        {
            return;
        }

        this.position.add(x, y);
    }
}
