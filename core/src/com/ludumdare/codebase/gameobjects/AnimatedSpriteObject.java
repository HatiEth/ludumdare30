package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.ludumdare.codebase.Renderer;

public class AnimatedSpriteObject extends SpriteObject
{

    float stateTime;
    Animation animation;

    public AnimatedSpriteObject(String spriteFilePath, int frameColumns,
            int frameRows)
    {
        super(spriteFilePath);

        if ((spriteHandle.getWidth() % frameColumns != 0)
                || (spriteHandle.getHeight() % frameRows != 0))
        {
            Gdx.app.log("AnimationError",
                    "FrameColums or frameRows may not work!");
        }

        TextureRegion[][] tmp = TextureRegion.split(this.spriteHandle,
                spriteHandle.getWidth() / frameColumns,
                spriteHandle.getHeight() / frameRows);
        TextureRegion[] frames = new TextureRegion[frameColumns * frameRows];
        int index = 0;
        for (int i = 0; i < frameRows; ++i)
        {
            for (int j = 0; j < frameColumns; ++j)
            {
                frames[index++] = tmp[i][j];
            }
        }

        animation = new Animation(0.25f, frames);

    }

    @Override
    public void update()
    {
        stateTime += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void render(Renderer renderer)
    {
        renderer.drawAnimatedSprite(animation.getKeyFrame(stateTime, true),
                position.x, position.y, layer);
    }
}
