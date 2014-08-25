package com.ludumdare.codebase.gameobjects;

import com.ludumdare.codebase.ObjectState;

public class DiebGO extends AnimatedSpriteObject
{

    public DiebGO()
    {
        BetterAnimation walkAnimation = BetterAnimation.createAnimation(
                "Charaktere/Dieb/dieb.png", 1, 1, 0.15f, true);

        this.addAnimation(ObjectState.IDLE, Direction.Down, walkAnimation);
        this.addAnimation(ObjectState.IDLE, Direction.Up, walkAnimation);
        this.addAnimation(ObjectState.IDLE, Direction.Left, walkAnimation);
        this.addAnimation(ObjectState.IDLE, Direction.Right, walkAnimation);

    }

}
