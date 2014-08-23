package com.ludumdare.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * 
 * @author Hati Eth
 *
 */
public class Camera2DControl
{
    private Camera camera;
    private FitViewport virtualResolution;
    private Color backgroundColor;
    private Color scissorColor;

    private boolean isDirty;

    private final float worldAspectRatio;
    private float projectiveZoomScale;

    public Camera2DControl(float virtualWidth, float virtualHeight)
    {
        worldAspectRatio = virtualWidth / virtualHeight;

        camera = new PerspectiveCamera();

        virtualResolution = new FitViewport(virtualWidth, virtualHeight, camera);

        final float GRAY = 0.53333333333333333333333333333333f;
        backgroundColor = new Color(GRAY, GRAY, GRAY, 1.0f);
        scissorColor = Color.BLACK;

        isDirty = true;

        camera.near = 0.1f;
        camera.far = 2000.0f;
    }

    public Camera getCamera()
    {
        return camera;
    }

    public FitViewport getVirtualResolution()
    {
        return virtualResolution;
    }

    boolean rotate = false;

    public void beginDraw()
    {
        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        Gdx.gl.glScissor(0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(scissorColor.r, scissorColor.g, scissorColor.b,
                scissorColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if (rotate)
        {
            camera.rotateAround(new Vector3(0, 0, 0), new Vector3(0, 1, 0),
                    1.0f);
            isDirty = true;
        }

        if (isDirty)
        {
            handleDirtyFlag();
        }

        Gdx.gl.glScissor(virtualResolution.getScreenX(),
                virtualResolution.getScreenY(),
                virtualResolution.getScreenWidth(),
                virtualResolution.getScreenHeight());
        // Gdx.gl.glClearColor(0, 0, 0, 1.0f);

        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g,
                backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    private void handleDirtyFlag()
    {
        isDirty = false;

        virtualResolution.update(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
    }

    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public void setScissorColor(Color scissorColor)
    {
        this.scissorColor = scissorColor;
    }

    public void resize(int width, int height)
    {
        virtualResolution.update(width, height);

        // projectiveZoomScale = (virtualResolution.getScreenWidth() /
        // virtualResolution
        // .getScreenHeight()) / worldAspectRatio;

        camera.position.z = virtualResolution.getWorldWidth() * 0.5f - 144;

        isDirty = true;
    }

    public void setZ(float z)
    {
        camera.position.z = z * 270.0f;

        isDirty = true;
    }

    public void pan(float x, float y)
    {
        camera.translate(x, y, 0);
        camera.lookAt(x, y, 0);

        isDirty = true;
    }

    public void setPosition(float x, float y)
    {
        camera.position.set(x, y, camera.position.z);
        camera.lookAt(x, y, 0);

        isDirty = true;
    }

    public Vector3 screenToWorld(float x, float y)
    {
        Vector3 v = new Vector3(x, y * -1, 0);
        v.sub(virtualResolution.getScreenX(), -virtualResolution.getScreenY(),
                0);

        float ratioX = virtualResolution.getWorldWidth()
                / virtualResolution.getScreenWidth();
        float ratioY = virtualResolution.getWorldHeight()
                / virtualResolution.getScreenHeight();

        v.x = v.x * ratioX;
        v.y = v.y * ratioY;

        v.sub(virtualResolution.getWorldWidth() * 0.5f,
                -virtualResolution.getWorldHeight() * 0.5f, 0);

        return v;
    }
}
