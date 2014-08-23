package com.ludumdare.codebase;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ludumdare.util.Camera2DControl;

public class Ludumdare30Main extends ApplicationAdapter
{

    SpriteBatch batch;
    Texture img;

    ShapeRenderer sr;
    PerspectiveCamera camera;
    FitViewport virtualResolution;

    Camera2DControl cameraControl;

    @Override
    public void create()
    {

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        cameraControl = new Camera2DControl(1920, 1080);
        cameraControl.setZ(100);
        cameraControl.setPosition(0, 0);

        virtualResolution = new FitViewport(1920, 1080, camera);
        sr = new ShapeRenderer();

    }

    @Override
    public void render()
    {
        // // batch.setProjectionMatrix(camera.combined);
        // // batch.begin();
        // // batch.draw(img, 0, 0);
        // //
        // // batch.end();
        // //
        // //
        //

        cameraControl.beginDraw();
        sr.setProjectionMatrix(cameraControl.getCamera().combined);
        sr.begin(ShapeType.Filled);

        sr.rect(-16, -16, 32, 32, Color.RED, Color.BLUE, Color.GREEN,
                Color.BLACK);

        sr.rect(48 + -16, 48 + -16, 32, 32, Color.RED, Color.BLUE, Color.GREEN,
                Color.BLACK);
        sr.end();
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
