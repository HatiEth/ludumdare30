package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.Renderer;

public abstract class SceneGroup extends Scene
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
        enterScene(activeScene, null);
    }

    @Override
    public void update()
    {
        activeScene.update();

        TransitZone zoneIn = getTransitAt(
                gameData.haraldGameObject.getPosition().x,
                gameData.haraldGameObject.getPosition().y);

        if (zoneIn != null && zoneIn.isActivate())
        {
            enterScene(zoneIn.to, zoneIn.from);
        }
    }

    public void enterScene(Scene scene, Scene from)
    {
        activeScene = scene;
        activeScene.onEnter(from);
    }

    @Override
    public void render(Renderer renderer)
    {
        activeScene.render(renderer);
    }

    public TransitZone addTransit(Scene from, Scene to, Rectangle transitArea)
    {
        TransitZone zone = new TransitZone(from, to, transitArea);
        this.transitZones.add(zone);
        return zone;
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

    public void debugDrawTransitZones(Renderer renderer)
    {
        ShapeRenderer sr = renderer.getShapeRenderer();
        TransitZone t = getTransitAt(Gdx.input.getX(), Gdx.input.getY());
        sr.begin(ShapeType.Filled);
        for (TransitZone z : transitZones)
        {
            if (activeScene == z.from)
            {
                if (z.isActivate())
                {
                    if (z == t)
                    {
                        sr.rect(z.x, z.y, z.width, z.height, Color.GREEN,
                                Color.GREEN, Color.GREEN, Color.GREEN);
                    }
                    else
                    {
                        sr.rect(z.x, z.y, z.width, z.height, Color.BLUE,
                                Color.BLUE, Color.BLUE, Color.BLUE);
                    }
                }
                else
                {
                    sr.rect(z.x, z.y, z.width, z.height, Color.RED, Color.RED,
                            Color.RED, Color.RED);
                }
            }
        }
        sr.end();

    }

}
