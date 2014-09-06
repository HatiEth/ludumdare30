package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.Renderer;
import com.ludumdare.codebase.gameobjects.AnimatedSpriteObject;
import com.ludumdare.codebase.gameobjects.HeadGO;

public class AnimatedPersonGO extends AnimatedSpriteObject
{

    HeadGO head;

    public AnimatedPersonGO(float headoffsetx, float headoffsety,
            float headxSpeed, float headySpeed, float headxAmpl, float headyAmpl)
    {
        head = new HeadGO(headoffsetx, headoffsety, headxSpeed, headySpeed,
                headxAmpl, headyAmpl);
    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub
        super.update();
        head.update();
        head.setLayer(this.layer);
    }

    @Override
    public void render(Renderer renderer)
    {
        super.render(renderer);
        head.render(renderer);
    }

}
