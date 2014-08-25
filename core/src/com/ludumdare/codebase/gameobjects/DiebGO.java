package com.ludumdare.codebase.gameobjects;

import com.ludumdare.codebase.ObjectState;

public class DiebGO extends AnimatedSpriteObject
{

    public DiebGO()
    {
        BetterAnimation walkAnimation = BetterAnimation.createAnimation(
                "Charaktere/Dieb//walk_dieb_rechts_ohne_tasche.png", 10, 1,
                0.09f, true);
        addAnimation(ObjectState.MOVE, Direction.Right, walkAnimation);
        addAnimation(ObjectState.MOVE, Direction.Left, walkAnimation);
        addAnimation(ObjectState.MOVE, Direction.Up, walkAnimation);
        addAnimation(ObjectState.MOVE, Direction.Down, walkAnimation);

        BetterAnimation idleDown = BetterAnimation.createAnimation(
                "Charaktere/Dieb/dieb.png", 1, 1, 1.0f, false);

        this.addAnimation(ObjectState.IDLE, Direction.Down, idleDown);
        this.addAnimation(ObjectState.IDLE, Direction.Up, walkAnimation);
        this.addAnimation(ObjectState.IDLE, Direction.Left, walkAnimation);
        this.addAnimation(ObjectState.IDLE, Direction.Right, walkAnimation);

        BetterAnimation stealTasche = BetterAnimation.createAnimation(
                "Charaktere/Dieb/diebstahl.png", 7, 1, .30f, false);
        this.addAnimation(ObjectState.TAKE, Direction.Down, stealTasche);

        BetterAnimation walkBagAnimation = BetterAnimation.createAnimation(
                "Charaktere/Dieb/walk_dieb_rechts.png", 10, 1, 0.09f, true);
        addAnimation(ObjectState.TALK, Direction.Left, walkBagAnimation);
        addAnimation(ObjectState.TALK, Direction.Up, walkBagAnimation);
        addAnimation(ObjectState.TALK, Direction.Right, walkBagAnimation);
        addAnimation(ObjectState.TALK, Direction.Down, walkBagAnimation);

    }
}
