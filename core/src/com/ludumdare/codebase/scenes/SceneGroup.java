package com.ludumdare.codebase.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ludumdare.codebase.GameData;
import com.ludumdare.codebase.PathNode;
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

        // TransitZone zoneIn = getTransitAt(
        // gameData.haraldGameObject.getPosition().x,
        // gameData.haraldGameObject.getPosition().y);
        //
        // if (zoneIn != null && zoneIn.isActivate())
        // {
        // enterScene(zoneIn.to, zoneIn.from);
        // }
    }

    public void enterScene(Scene scene, Scene from)
    {
        if (from != null)
        {
            System.out.println("Entering " + scene.getClass().getSimpleName()
                    + " from " + from.getClass().getSimpleName());
        }
        else
        {
            System.out.println("Entering " + scene.getClass().getSimpleName());
        }
        activeScene = scene;
        activeScene.pathEngine.reset();
        activeScene.onEnter(from);
    }

    @Override
    public void render(Renderer renderer)
    {
        activeScene.render(renderer);
    }

}
