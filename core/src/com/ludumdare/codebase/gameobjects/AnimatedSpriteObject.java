package com.ludumdare.codebase.gameobjects;

import java.util.EnumMap;

import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.Renderer;

public abstract class AnimatedSpriteObject extends GameObject
{
    EnumMap<ObjectState, EnumMap<GameObject.Direction, BetterAnimation>> animationMap;

    public AnimatedSpriteObject()
    {
        animationMap = new EnumMap<ObjectState, EnumMap<Direction, BetterAnimation>>(
                ObjectState.class);

        animationMap.put(ObjectState.IDLE,
                new EnumMap<GameObject.Direction, BetterAnimation>(
                        GameObject.Direction.class));
        animationMap.put(ObjectState.MOVE,
                new EnumMap<GameObject.Direction, BetterAnimation>(
                        GameObject.Direction.class));
        animationMap.put(ObjectState.TAKE_SEAT,
                new EnumMap<GameObject.Direction, BetterAnimation>(
                        GameObject.Direction.class));
        animationMap.put(ObjectState.UNSEAT,
                new EnumMap<GameObject.Direction, BetterAnimation>(
                        GameObject.Direction.class));
        animationMap.put(ObjectState.TAKE,
                new EnumMap<GameObject.Direction, BetterAnimation>(
                        GameObject.Direction.class));
        animationMap.put(ObjectState.TALK,
                new EnumMap<GameObject.Direction, BetterAnimation>(
                        GameObject.Direction.class));

    }

    public void addAnimation(ObjectState state, GameObject.Direction dir,
            BetterAnimation animation)
    {
        animationMap.get(state).put(dir, animation);
        // spriteHandle = new Texture(Gdx.files.internal(spriteFilePath));
        // spriteHandle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    public boolean hasAnimation()
    {
        return animationMap.get(objectState).containsKey(this.direction);
    }

    @Override
    public void update()
    {
        if (objectState == ObjectState.UNSEAT)
        {
            stateTime -= 0.016f;
            if (stateTime <= 0)
            {
                enterState(transitToState);
            }
            return;
        }

        if (objectState != ObjectState.IDLE)
        {
            stateTime += 0.016f;
        }

    }

    @Override
    public void render(Renderer renderer)
    {
        if (hasAnimation())
        {
            renderer.drawAnimatedSprite(
                    animationMap.get(objectState).get(direction)
                            .getCurrentFrame(stateTime), position.x,
                    position.y, layer, this.direction);
        }
        else
        {
            if (GameData.isDevMode) System.out
                    .println("No animation found for " + objectState.name()
                            + " o " + direction.name());
        }
    }

}
