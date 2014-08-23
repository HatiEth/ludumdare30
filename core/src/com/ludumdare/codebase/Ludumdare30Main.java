package com.ludumdare.codebase;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
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
    SpriteMesh sprite;

    ShaderProgram spriteShader;

    Renderer renderer;
    Vector2 position;

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

        // spriteShader = new ShaderProgram(Gdx.files.internal("sprite.vert"),
        // Gdx.files.internal("sprite.frag"));
        // ShaderProgram.pedantic = false;
        // String spriteShaderLog = spriteShader.getLog();
        // if (!spriteShader.isCompiled())
        // {
        // throw new GdxRuntimeException(spriteShaderLog);
        // }
        // if (spriteShaderLog != null && spriteShaderLog.length() > 0)
        // {
        // System.out.println(spriteShaderLog);
        // }
    }

    float degree = 0.0f;
    Vector2 positon2 = new Vector2();

    @Override
    public void render()
    {

        degree = degree + 1.0f;

        renderer.drawSprite(img, positon2.x, positon2.y, 30);
        renderer.drawSprite(img, position.x, position.y, -100);

        positon2.x = 50 + MathUtils.cosDeg(45 + degree) * 100.0f;
        positon2.y = 50 + MathUtils.sinDeg(45 + degree) * 100.0f;

        position.x = MathUtils.cosDeg(degree) * 50;
        position.y = MathUtils.sinDeg(degree) * 50;

        renderer.renderAll();
        renderer.clearList();

        // Gdx.gl.glDepthMask(false);
        // Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);
        //
        // Gdx.gl.glEnable(GL20.GL_BLEND);
        // Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        //
        // spriteShader.begin();
        // spriteShader.setUniformMatrix("u_projection",
        // cameraControl.getCamera().combined);
        //
        // spriteShader.setUniform2fv("u_scale", new float[]
        // {
        // 600,
        // 600
        // }, 0, 2);
        //
        // img.bind();
        // spriteShader.setUniformi("u_texture0", 0);
        //
        // sprite.render(spriteShader, GL20.GL_TRIANGLE_STRIP, 0, 4);
        //
        // spriteShader.end();
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
