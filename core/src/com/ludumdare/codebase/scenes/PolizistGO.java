package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.Renderer;
import com.ludumdare.codebase.gameobjects.HeadGO;
import com.ludumdare.codebase.gameobjects.SpriteObject;

public class PolizistGO extends SpriteObject
{

    HeadGO head;

    public PolizistGO()
    {
        super("Charaktere/Polizist/Polizist.png");
        head = new HeadGO(30, 170, 0.5f, 2.0f, 3.0f, 4.0f);
    }

    @Override
    public void update()
    {
        head.setPosition(this.getPosition());
        head.setLayer(this.getLayer() + 0.1f);
        head.update();
    }

    @Override
    public void render(Renderer renderer)
    {
        super.render(renderer);
        head.render(renderer);
    }

}
