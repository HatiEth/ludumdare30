package com.ludumdare.codebase.gameobjects;

import com.ludumdare.codebase.ObjectState;

public class SitzplatzOmaGO extends AnimatedSpriteObject
{

    public SitzplatzOmaGO()
    {
        BetterAnimation sit = BetterAnimation.createAnimation(
                "Charaktere/Hildegard/hildediewilde.png", 1, 1, 1.0f, false);
        addAnimation(ObjectState.TAKE_SEAT, Direction.Left, sit);
        addAnimation(ObjectState.TAKE_SEAT, Direction.Right, sit);

        BetterAnimation walk = BetterAnimation.createAnimation(
                "Charaktere/Hildegard/hildediewilde.png", 1, 1, 1.0f, false);
        addAnimation(ObjectState.MOVE, Direction.Left, walk);
        addAnimation(ObjectState.MOVE, Direction.Right, walk);
        addAnimation(ObjectState.MOVE, Direction.Up, walk);
        addAnimation(ObjectState.MOVE, Direction.Down, walk);
    }

}
