package com.ludumdare.codebase;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.gameobjects.GameObject;
import com.ludumdare.codebase.gameobjects.GameObject.Direction;

public class InteractionNode extends PathNode
{
    private final ObjectState enterState;
    private final GameObject.Direction enterDirection;
    private final InteractionEvent interactionEvent;

    public InteractionNode(ObjectState enterState,
            GameObject.Direction direction, Vector2 position, float layer,
            float x, float y, float halfWidth, float halfHeight)
    {
        super(position, layer, x, y, halfWidth, halfHeight);
        this.enterState = enterState;
        this.enterDirection = direction;
        this.interactionEvent = null;
    }

    public InteractionNode(ObjectState enterState,
            GameObject.Direction direction, Vector2 position, float layer,
            float x, float y, float halfWidth, float halfHeight,
            InteractionEvent event)
    {
        super(position, layer, x, y, halfWidth, halfHeight);
        this.enterState = enterState;
        this.enterDirection = direction;
        this.interactionEvent = event;
    }

    @Override
    public void execute(GameObject o)
    {
        if (enterDirection != Direction.NoChange)
        {
            o.setDirection(enterDirection);
        }
        o.enterState(enterState);

        if (interactionEvent != null)
        {
            interactionEvent.fire(o);
        }
    }
}
