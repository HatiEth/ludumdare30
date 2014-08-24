package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BetterAnimation extends Animation
{
    final private boolean isLooped;
    private Texture spriteSheet;

    private BetterAnimation(float frameDuration, TextureRegion[] keyFrames,
            boolean isLooped)
    {
        super(frameDuration, keyFrames);
        this.isLooped = isLooped;
    }

    public boolean isLooped()
    {
        return isLooped;
    }

    public static BetterAnimation createAnimation(String filePath,
            int frameColumns, int frameRows, float animationTime,
            boolean isLooped)
    {
        Texture spriteHandle = new Texture(Gdx.files.internal(filePath));
        spriteHandle.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        if ((spriteHandle.getWidth() % frameColumns != 0)
                || (spriteHandle.getHeight() % frameRows != 0))
        {
            Gdx.app.log("AnimationError",
                    "FrameColums or frameRows may not work!");
        }

        TextureRegion[][] tmp = TextureRegion.split(spriteHandle,
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

        return new BetterAnimation(animationTime, frames, true);
    }
}
