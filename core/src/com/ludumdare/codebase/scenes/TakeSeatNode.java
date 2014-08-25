package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.gameobjects.GameObject;
import com.ludumdare.codebase.gameobjects.GameObject.Direction;

public class TakeSeatNode extends PathNode
{

    Direction sittingDirection;

    public TakeSeatNode(Direction sittingDirection, Vector2 position,
            float layer)
    {
        super(position, layer);
        this.sittingDirection = sittingDirection;
    }

    @Override
    public void execute(GameObject o)
    {
        super.execute(o);
        o.enterState(ObjectState.TAKE_SEAT);
        o.setDirection(sittingDirection);
    }

}
