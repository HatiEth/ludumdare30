package com.ludumdare.codebase;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.gameobjects.GameObject;

public class InteractionNode extends PathNode
{
    private final ObjectState enterState;
    private final GameObject.Direction enterDirection;

    public InteractionNode(ObjectState enterState,
            GameObject.Direction direction, Vector2 position, float layer,
            float x, float y, float halfWidth, float halfHeight)
    {
        super(position, layer, x, y, halfWidth, halfHeight);
        this.enterState = enterState;
        this.enterDirection = direction;
    }

    @Override
    public void execute(GameObject o)
    {
        o.setDirection(enterDirection);
        o.enterState(enterState);
    }

}
