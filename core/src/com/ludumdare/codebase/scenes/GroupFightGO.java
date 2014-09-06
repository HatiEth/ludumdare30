package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.gameobjects.AnimatedSpriteObject;
import com.ludumdare.codebase.gameobjects.BetterAnimation;

public class GroupFightGO extends AnimatedSpriteObject
{

    public GroupFightGO()
    {
        BetterAnimation fightAnimation = BetterAnimation.createAnimation(
                "Charaktere/Gangsta/pruegelei_ruepel.png", 7, 1, 0.14f, true);
        addAnimation(ObjectState.MOVE, Direction.Right, fightAnimation);

        BetterAnimation staying = BetterAnimation.createAnimation(
                "Charaktere/Gangsta/alte_freunde.png", 1, 1, 0.1f, false);
        addAnimation(ObjectState.IDLE, Direction.Right, staying);
    }
}
