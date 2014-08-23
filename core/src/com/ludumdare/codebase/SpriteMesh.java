package com.ludumdare.codebase;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;

public class SpriteMesh extends Mesh
{
    public SpriteMesh()
    {
        super(
                true,
                4,
                4,
                new VertexAttribute(Usage.Position, 3, "a_position"),
                new VertexAttribute(Usage.TextureCoordinates, 2, "a_texCoords"),
                new VertexAttribute(Usage.ColorPacked, 4, "a_color"));

        this.setVertices(new float[]
        {
                -.50f,
                -.50f,
                0.0f,
                0.0f,
                1.0f,
                Color.toFloatBits(255, 0, 0, 255),

                +.50f,
                -.50f,
                0.0f,
                1.0f,
                1.0f,
                Color.toFloatBits(255, 0, 0, 255),

                -.50f,
                +.50f,
                0.0f,
                0.0f,
                0.0f,
                Color.toFloatBits(255, 0, 0, 255),

                +.50f,
                +.50f,
                0.0f,
                1.0f,
                0.0f,
                Color.toFloatBits(255, 0, 0, 255)
        });
        // this.setVertices(new float[]
        // {
        // -32.0f,
        // -32.0f,
        // 0.0f,
        // Color.toFloatBits(255, 0, 0, 255),
        //
        // +32.0f,
        // -32.0f,
        // 0.0f,
        // Color.toFloatBits(255, 0, 0, 255),
        //
        // -32.0f,
        // +32.0f,
        // 0.0f,
        // Color.toFloatBits(255, 0, 0, 255),
        //
        // +32.0f,
        // +32.0f,
        // 0.0f,
        // Color.toFloatBits(255, 0, 0, 255),
        // });

        this.setIndices(new short[]
        {
                0,
                1,
                2,
                3
        });
    }

}
