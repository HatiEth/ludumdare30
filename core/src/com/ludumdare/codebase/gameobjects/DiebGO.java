package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.math.MathUtils;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.Renderer;

public class DiebGO extends AnimatedSpriteObject
{

    HeadGO head;

    public DiebGO()
    {
        head = new HeadGO(0, 140, 2.5f, 0.25f, 2.5f, 1.5f);

        BetterAnimation walkAnimation = BetterAnimation.createAnimation(
                "Charaktere/Dieb/walk_dieb_rechts_ohne_tasche.png", 10, 1,
                0.09f, true);
        addAnimation(ObjectState.MOVE, Direction.Right, walkAnimation);
        addAnimation(ObjectState.MOVE, Direction.Left, walkAnimation);
        addAnimation(ObjectState.MOVE, Direction.Up, walkAnimation);
        addAnimation(ObjectState.MOVE, Direction.Down, walkAnimation);

        BetterAnimation idleAnimation = BetterAnimation.createAnimation(
                "Charaktere/Dieb/walk_dieb_rechts.png", 10, 1, 0.01f, false);

        this.addAnimation(ObjectState.IDLE, Direction.Down, idleAnimation);
        this.addAnimation(ObjectState.IDLE, Direction.Up, idleAnimation);
        this.addAnimation(ObjectState.IDLE, Direction.Left, idleAnimation);
        this.addAnimation(ObjectState.IDLE, Direction.Right, idleAnimation);

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

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        super.update();
        head.setPosition(getPosition().x, getPosition().y);
        head.setLayer(getLayer());

        if (objectState == ObjectState.TAKE)
        {
            head.setLayer(getLayer() + 0.1f);
            if (stateTime >= 0.15f * 8)
            {
                head.position.y = this.position.y - 30
                        + MathUtils.cosDeg((0.3f * 8 - stateTime) * 50.0f)
                        * 50.0f;
                head.position.x = this.position.x
                        - MathUtils.sinDeg((0.3f * 8 - stateTime) * 50.0f)
                        * 50.0f;
            }
            else
            {
                head.position.y = this.position.y - 30
                        + MathUtils.cosDeg(stateTime * 50.0f) * 50.0f;
                head.position.x = this.position.x
                        - MathUtils.sinDeg(stateTime * 50.0f) * 50.0f;
            }
        }
    }

    @Override
    public void render(Renderer renderer)
    {
        // TODO Auto-generated method stub
        super.render(renderer);

        head.render(renderer);
    }

}
