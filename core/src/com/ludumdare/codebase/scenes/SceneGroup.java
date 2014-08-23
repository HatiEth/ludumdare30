package com.ludumdare.codebase.scenes;

import com.ludumdare.codebase.Renderer;

public class SceneGroup extends Scene
{
    protected Scene activeScene;

    public SceneGroup()
    {
    }

    public void setActiveScene(Scene activeScene)
    {
        this.activeScene = activeScene;
    }

    @Override
    public void update()
    {
        activeScene.update();
    }

    @Override
    public void render(Renderer renderer)
    {
        activeScene.render(renderer);
    }

}
