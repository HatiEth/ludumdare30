package com.ludumdare.codebase.gameobjects;

import java.util.EnumMap;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.MathUtils;
import com.ludumdare.codebase.ObjectState;
import com.ludumdare.codebase.Renderer;

public class AnimatedSpriteObject extends GameObject
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
        animationMap.put(ObjectState.SITTING,
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
        if (objectState == objectState.SITTING)
        {
            stateTime = 5.0f;
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
                            .getKeyFrame(stateTime, true), position.x,
                    position.y, layer, this.direction);
        }
        else
        {
            System.out.println("No animation found for " + objectState.name()
                    + " o " + direction.name());
        }
    }
}
