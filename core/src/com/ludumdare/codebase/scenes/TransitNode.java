package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.PathNode;
import com.ludumdare.codebase.gameobjects.GameObject;

public class TransitNode extends PathNode
{
    final SceneGroup containedGroup;
    final Scene from;
    final Scene to;

    public TransitNode(SceneGroup group, Scene from, Scene to,
            Vector2 position, float layer, float x, float y, float halfWidth,
            float halfHeight)
    {
        super(position, layer, x, y, halfWidth, halfHeight);
        this.containedGroup = group;
        this.from = from;
        this.to = to;
        this.isRepeatable = true;
    }

    @Override
    public void execute(GameObject o)
    {
        o.enterState(ObjectState.IDLE);
        containedGroup.enterScene(to, from);
    }

}
