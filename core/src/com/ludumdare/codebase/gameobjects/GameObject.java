package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.Renderer;

public abstract class GameObject
{
    Vector2 position;
    protected float layer;

    float stateTime;
    public ObjectState objectState;

    public enum Direction
    {
        Up, Left, Down, Right
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
        this.direction = direction;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void enterState(ObjectState state)
    {
        objectState = state;
        stateTime = 0.0f;
    }

}
