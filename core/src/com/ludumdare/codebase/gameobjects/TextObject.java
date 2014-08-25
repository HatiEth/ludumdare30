package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.Renderer;
import com.ludumdare.codebase.interactions.TextInteractionEvent;
import com.ludumdare.codebase.scenes.Scene;

public class TextObject extends SpriteObject
{
    float TEXT_LAYER = 15.0f;

    protected final float totallifeTime;
    protected float lifeTime;
    protected Scene alignedTo;
    protected final GameObject parent;

    protected TextInteractionEvent notifier;

    public TextObject(String spriteFilePath, GameObject parent,
            Scene alignedTo, Vector2 offset, float lifeTime)
    {
        super(spriteFilePath);
        totallifeTime = lifeTime;
        this.lifeTime = 0.0f;
        this.alignedTo = alignedTo;
        this.parent = parent;
        this.setPosition(offset);
    }

    public void setNotifier(TextInteractionEvent notifier)
    {
        this.notifier = notifier;
    }

    @Override
    public void update()
    {
        lifeTime = lifeTime - 0.016f;
        if (!isAlive())
        {
            alignedTo.remove(this);
            notifier.handleTextExpiredSequence();
        }
    }

    @Override
    public void render(Renderer renderer)
    {
        renderer.drawStaticSprite(spriteHandle,
                parent.getPosition().x + this.getPosition().x,
                parent.getPosition().y + this.getPosition().y, TEXT_LAYER,
                Direction.Right);
    }

    public TextObject reset()
    {
        lifeTime = totallifeTime;
        return this;
    }

    public boolean isAlive()
    {
        return lifeTime > 0;
    }

    public void die()
    {
        lifeTime = 0.0f;
    }
}
