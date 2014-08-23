package com.ludumdare.codebase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pool;
import com.ludumdare.util.Camera2DControl;

public class Renderer
{
    SpriteMesh spriteMesh;
    ShaderProgram spriteShader;

    ShapeRenderer shapeRenderer = new ShapeRenderer();

    final Camera2DControl cameraControl;

    Pool<RenderInfo> renderPool;
    Array<RenderInfo> renderList;
    boolean isDirty;

    public Renderer(Camera2DControl cameraControl)
    {
        this.cameraControl = cameraControl;
        spriteMesh = new SpriteMesh();

        spriteShader = new ShaderProgram(Gdx.files.internal("sprite.vert"),
                Gdx.files.internal("sprite.frag"));

        String spriteShaderLog = spriteShader.getLog();
        if (!spriteShader.isCompiled())
        {
            throw new GdxRuntimeException(spriteShaderLog);
        }
        if (spriteShaderLog != null && spriteShaderLog.length() > 0)
        {
            System.out.println(spriteShaderLog);
        }

        renderPool = new Pool<RenderInfo>(256, 1024)
        {
            @Override
            protected RenderInfo newObject()
            {
                return new RenderInfo();
            }
        };

        renderList = new Array<RenderInfo>(true, 1024);
        isDirty = false;

    }

    public void clearList()
    {
        renderPool.freeAll(renderList);
        renderList.clear();
    }

    public void drawSprite(Texture texture, float x, float y, float layer)
    {
        RenderInfo info = renderPool.obtain();
        info.x = x;
        info.y = y;
        info.layer = layer;
        info.texture = texture;
        info.width = texture.getWidth();
        info.height = texture.getHeight();
        info.meshtype = spriteMesh;

        renderList.add(info);

        isDirty = true;
    }

    public void renderAll()
    {
        if (isDirty)
        {
            renderList.sort();
        }

        cameraControl.beginDraw();

        shapeRenderer.setProjectionMatrix(cameraControl.getCamera().combined);

        Gdx.gl.glDepthMask(false);
        Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        spriteShader.begin();
        spriteShader.setUniformMatrix("u_projection",
                cameraControl.getCamera().combined);

        int boundTexture = -1;

        for (RenderInfo i : renderList)
        {
            if (i.texture.getTextureObjectHandle() != boundTexture)
            {
                i.texture.bind(0);
                spriteShader.setUniform2fv("u_scale", new float[]
                {
                        i.width,
                        i.height
                }, 0, 2);
                spriteShader.setUniformi("u_texture0", 0);
            }

            spriteShader.setUniformf("i_position", i.x, i.y, i.layer);

            i.meshtype.render(spriteShader, GL20.GL_TRIANGLE_STRIP, 0, 4);
        }
        spriteShader.end();

        clearList();
    }

    public ShapeRenderer getShapeRenderer()
    {

        return shapeRenderer;
    }

}
