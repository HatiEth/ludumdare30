package com.ludumdare.codebase;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.ludumdare.codebase.scenes.HomeSceneGroup;
import com.ludumdare.codebase.scenes.Scene;
import com.ludumdare.codebase.scenes.SceneGroup;
import com.ludumdare.codebase.scenes.TrainStation;
import com.ludumdare.util.Camera2DControl;

/**
 * 
 * @author Hati Eth
 *
 */
public class Ludumdare30Main extends ApplicationAdapter
{

    SpriteBatch batch;
    Texture img;

    ShapeRenderer sr;

    Camera2DControl cameraControl;

    Renderer renderer;
    Vector2 position;

    Scene activeScene;

    SceneGroup homeGroup;

    Vector2 mousePosition = new Vector2();
    Ray mouseRay;

    @Override
    public void create()
    {
        position = new Vector2();
        position.x = 50;

        batch = new SpriteBatch();
        img = new Texture("noTextureFound.png");
        img.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        cameraControl = new Camera2DControl(1920, 1080);
        cameraControl.setPosition(0, 0);

        if (renderer == null)
        {
            renderer = new Renderer(cameraControl);
        }

        sr = new ShapeRenderer();

        activeScene = new TrainStation();

        Gdx.input.setInputProcessor(new InputAdapter()
        {
            @Override
            public boolean mouseMoved(int screenX, int screenY)
            {
                Vector3 v = cameraControl.screenToWorld(screenX, screenY);
                mousePosition.x = v.x;
                mousePosition.y = v.y;

                System.out.println(screenX + ", " + screenY);
                return true;
            }
        });

        homeGroup = new HomeSceneGroup();
    }

    float degree = 0.0f;
    Vector2 positon2 = new Vector2();

    @Override
    public void render()
    {
        // degree = degree + 1.0f;
        //
        // renderer.drawSprite(img, positon2.x, positon2.y, 30);
        // renderer.drawSprite(img, position.x, position.y, -100);
        //
        // positon2.x = 50 + MathUtils.cosDeg(45 + degree) * 100.0f;
        // positon2.y = 50 + MathUtils.sinDeg(45 + degree) * 100.0f;
        //
        // position.x = MathUtils.cosDeg(degree) * 50;
        // position.y = MathUtils.sinDeg(degree) * 50;
        //
        // renderer.renderAll();
        // renderer.clearList();

        // activeScene.update();
        // activeScene.render(renderer);

        homeGroup.update();
        homeGroup.render(renderer);

        // renderer.drawSprite(img, 0, 0, 100.0f);
        // renderer.drawSprite(img, mousePosition.x, mousePosition.y, 0.1f);

        renderer.renderAll();
        renderer.clearList();
    }

    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);
        cameraControl.resize(width, height);
    }

    @Override
    public void pause()
    {
        super.pause();
    }

    @Override
    public void resume()
    {
        super.resume();
    }

    @Override
    public void dispose()
    {
        super.dispose();
        img.dispose();
    }

}
