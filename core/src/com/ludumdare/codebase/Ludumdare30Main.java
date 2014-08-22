package com.ludumdare.codebase;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Ludumdare30Main extends ApplicationAdapter
{

    SpriteBatch batch;
    Texture img;

    ShapeRenderer sr;
    PerspectiveCamera camera;
    FitViewport virtualResolution;

    @Override
    public void create()
    {

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        camera = new PerspectiveCamera();

        camera.position.set(0, 0, 500.0f); // caera looks towards -z
        // (63.9 ... -100)
        camera.lookAt(0, 0, 0);
        camera.near = 0.1f;
        camera.far = 1000.0f;
        camera.update();

        virtualResolution = new FitViewport(1920, 1080, camera);
        sr = new ShapeRenderer();

    }

    @Override
    public void render()
    {
        final float GRAY_BG = 121.0f / 255.0f;

        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        Gdx.gl.glScissor(0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        virtualResolution.update(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());

        Gdx.gl.glScissor(virtualResolution.getScreenX(),
                virtualResolution.getScreenY(),
                virtualResolution.getScreenWidth(),
                virtualResolution.getScreenHeight());
        // Gdx.gl.glClearColor(0, 0, 0, 1.0f);

        Gdx.gl.glClearColor(GRAY_BG, GRAY_BG, GRAY_BG, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //
        // virtualResolution.apply(true);
        // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        //
        // // batch.setProjectionMatrix(camera.combined);
        // // batch.begin();
        // // batch.draw(img, 0, 0);
        // //
        // // batch.end();
        // //
        // //
        //
        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeType.Filled);

        sr.rect(-16, -16, 32, 32, Color.RED, Color.BLUE, Color.GREEN,
                Color.BLACK);
        sr.end();
    }

    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);
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
