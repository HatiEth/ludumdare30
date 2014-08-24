package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.Renderer;

public class SceneGroup extends Scene
{
    protected Scene activeScene;
    Array<TransitZone> transitZones;

    public SceneGroup(GameData gameData)
    {
        super(gameData);
        transitZones = new Array<TransitZone>();
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

    public void addTransit(Scene from, Scene to, Rectangle transitArea)
    {
        this.transitZones.add(new TransitZone(from, to, transitArea));
    }

    public TransitZone getTransitAt(float px, float py)
    {
        for (TransitZone t : transitZones)
        {
            if (activeScene == t.from)
            {
                if (t.contains(px, py))
                {
                    return t;
                }
            }
        }
        return null;
    }

}
