package com.ludumdare.codebase.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.ludumdare.codebase.scenes.Scene;

public class DialogText extends TextObject
{

    public DialogText(String spriteFilePath, GameObject parent,
            Scene alignedTo, Vector2 offset, float lifeTime)
    {
        super(spriteFilePath, parent, alignedTo, offset, lifeTime);
    }

}
